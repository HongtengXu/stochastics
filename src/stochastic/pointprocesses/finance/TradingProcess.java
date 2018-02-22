package stochastic.pointprocesses.finance;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel.MapMode;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fastmath.DoubleRowMatrix;
import fastmath.Pair;
import fastmath.Vector;
import stochastic.annotations.Units;
import util.DateUtils;

public class TradingProcess implements Iterable<ArchivableEvent>, Iterator<ArchivableEvent>, Comparable<TradingProcess>
{
  public static double openTime = 9.5;

  @Units(time = TimeUnit.HOURS)
  public final static double closeTime = 16;

  @Units(time = TimeUnit.HOURS)
  public static final double tradingDuration = closeTime - openTime;

  private double bucketLength = 0.5;

  private int eventCount;

  private double openTimeMillis = DateUtils.convertTimeUnits(openTime, TimeUnit.HOURS, TimeUnit.MILLISECONDS);

  @Override
  public String toString()
  {
    return String.format("MarkedPointProcess[mppFile=%s, eventCount=%s]", mppFile, eventCount);
  }

  private RandomAccessFile indexFile;
  private Vector points;
  String symbol;
  int i = 0;
  private File mppFile;
  private Integer year;
  private Integer month;
  private Integer day;
  private Date date;
  private ByteBuffer buffer;

  public TradingProcess(Pair<RandomAccessFile, RandomAccessFile> pair, File mppFile, String symbol) throws IOException
  {
    this.mppFile = mppFile;
    RandomAccessFile raf = pair.left;
    buffer = raf.getChannel().map(MapMode.READ_ONLY, 0, raf.length()).order(ByteOrder.nativeOrder());
    indexFile = pair.right;
    points = new Vector(buffer);
    this.symbol = symbol;
    eventCount = (int) (indexFile.length() / (Integer.BYTES * 2 + Byte.BYTES));
    String[] tokens = mppFile.getName().split("-");
    year = Integer.valueOf(tokens[1]);
    month = Integer.valueOf(tokens[2]);
    day = Integer.valueOf(tokens[3].split("\\.")[0]);
    date = new GregorianCalendar(year, month, day).getTime();
  }

  private ArchivableEvent getNextEvent(String symbol, Vector points, RandomAccessFile indices) throws IOException
  {
    i++;
    try
    {
      ArchivableEvent.EventType type = ArchivableEvent.EventType.values()[indices.readByte()];
      int pos = indices.readInt();
      int len = indices.readInt();

      Vector point = points.slice(pos, pos + len);

      ArchivableEvent ae = null;
      switch (type)
      {
      case TwoSidedQuote:
        ae = new TwoSidedQuote(point, symbol);
        break;
      case TradeTick:
        ae = new TradeTick(point, symbol);
        break;
      default:
        throw new IllegalArgumentException("unhandled type " + type);
      }
      return ae;
    }
    catch (EOFException e)
    {
      err.println("EOF at record " + i + " but recordCount is " + eventCount);
      return null;
    }
  }

  public <E extends ArchivableEvent> Stream<E> eventStream(Class<E> eventClass)
  {
    return stream().filter(eventClass::isInstance).map(eventClass::cast);
  }

  public Stream<TradeTick> tradeStream()
  {
    return eventStream(TradeTick.class).filter(trade -> trade.getPrice() > 0);
  }

  public Stream<TwoSidedQuote> quoteStream()
  {
    return eventStream(TwoSidedQuote.class);
  }

  public Stream<ArchivableEvent> tradeAndQuoteStream()
  {
    return stream().filter(event -> event instanceof TradeTick || event instanceof TwoSidedQuote);
  }

  @Override
  public boolean hasNext()
  {
    return i < eventCount;
  }

  @Override
  public ArchivableEvent next()
  {
    try
    {
      return getNextEvent(symbol, points, indexFile);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @Override
  public Iterator<ArchivableEvent> iterator()
  {
    reset();
    return this;
  }

  public int getAge(TimeUnit timeUnit)
  {
    Date now = new Date();
    long diffInMillies = now.getTime() - date.getTime();
    return (int) DateUtils.convertTimeUnits(diffInMillies, TimeUnit.MILLISECONDS, TimeUnit.DAYS);
  }

  @SuppressWarnings("unchecked")
  public <E extends ArchivableEvent> Stream<E> stream()
  {
    reset();
    return (Stream<E>) StreamSupport.stream(spliterator(), false);
  }

  private void reset()
  {
    i = 0;
    try
    {
      indexFile.seek(0);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Date getDate()
  {
    return date;
  }

  @Override
  public int compareTo(TradingProcess o)
  {
    return date.compareTo(o.getDate());
  }

  public File getMppFile()
  {
    return mppFile;
  }

  public void setMppFile(File mppFile)
  {
    this.mppFile = mppFile;
  }

  public DoubleRowMatrix getTradeMatrix(TimeUnit timeUnit)
  {
    DoubleRowMatrix tradeMatrix = new DoubleRowMatrix(0, TradeTick.FIELDCNT).setName(symbol);

    final Vector lastt = new Vector(1);
    tradeStream().forEach(event -> {
      Vector point = new Vector(event.getMarks());
      double fractionalHourOfDay = event.getTimeOfDay();

      
      double t = DateUtils.convertTimeUnits(event.getTimeOfDay(), TimeUnit.HOURS, timeUnit);
      point.set(0, t);
      
      double prevt = lastt.get(0);
      if (prevt == t) { return; }

      if (fractionalHourOfDay >= openTime && fractionalHourOfDay <= closeTime)
      {
        lastt.set(0, t);
        tradeMatrix.appendRow(point);
       
      }
    });

    return tradeMatrix;
  }

  public Pair<DoubleRowMatrix, DoubleRowMatrix> getBuySellMatrix(TimeUnit timeUnit)
  {
    DoubleRowMatrix buyMatrix = new DoubleRowMatrix(0, TradeTick.FIELDCNT).setName(symbol + "_buys");
    DoubleRowMatrix sellMatrix = new DoubleRowMatrix(0, TradeTick.FIELDCNT).setName(symbol + "_sells");
    AtomicInteger midPointTrades = new AtomicInteger(0);
    Vector midPrice = new Vector(1);
    TradeClassifier classifier = new TradeClassifier();
    tradeAndQuoteStream().forEachOrdered(event -> {
      if (event instanceof TwoSidedQuote)
      {
        TwoSidedQuote quote = (TwoSidedQuote) event;
        midPrice.set(0, quote.getMidPrice());

      }
      else if (event instanceof TradeTick)
      {
        TradeTick tick = (TradeTick) event;
        // int side = Double.compare(tick.getPrice(), midPrice[0]);
        Side side = classifier.classify(tick.getPrice());
        classifier.record(tick.getPrice());

        if (side == Side.Unknown)
        {
          midPointTrades.incrementAndGet();
          // buyMatrix.appendRow(tick.getMarks());
          // sellMatrix.appendRow(tick.getMarks());
        }
        else if (side == Side.Buy)
        {
          buyMatrix.appendRow(tick.getMarks());
        }
        else if (side == Side.Sell)
        {
          sellMatrix.appendRow(tick.getMarks());
        }
        // out.println( event + " side=" + side + " midPrice=" + midPrice );

      }
    });

    int unknownCount = midPointTrades.get();
    double unknownRatio = (double) unknownCount / (double) (buyMatrix.getRowCount() + sellMatrix.getRowCount() + unknownCount);

    out.println("unknownPcnt=" + (unknownRatio * 100) + "% unknoCount=" + unknownCount);

    return new Pair<DoubleRowMatrix, DoubleRowMatrix>(buyMatrix, sellMatrix);
  }


  public static TradingProcess loadMppFile(String mppFilename) throws FileNotFoundException, IOException
  {
    Pair<RandomAccessFile, RandomAccessFile> mppDataIndexPair = new Pair<>(new RandomAccessFile(mppFilename, "r"),
                                                                           new RandomAccessFile(mppFilename + ".idx", "r"));

    File mppFile = new File(mppFilename);
    String symbol = mppFile.getName().split("-")[0];
    return new TradingProcess(mppDataIndexPair, mppFile, symbol);
  }

}
