package stochastic.pointprocesses.finance;

import static java.lang.System.out;

import java.util.ArrayList;

import fastmath.DoubleMatrix;
import fastmath.IntVector;
import fastmath.Vector;

/**
 * Also called a "list-history"
 * 
 *
 */
public class TradingFiltration
{
  public TradingFiltration(DoubleMatrix markedPoints)
  {
    super();
    this.markedPoints = markedPoints;
    times = markedPoints.col(0).setName("times");
    prices = markedPoints.col(1).setName("prices");
    types = new IntVector(times.size());
    buyTimes = new Vector(times.size());
    sellTimes = new Vector(times.size());

    classifyTradeSequences();
  }

  public TradingFiltration(DoubleMatrix markedPointSlice, IntVector types)
  {
    super();
    this.markedPoints = markedPointSlice;
    assert markedPointSlice.getRowCount() == types.size();
    times = markedPoints.col(0).setName("times");
    prices = markedPoints.col(1).setName("prices");
    this.types = types;
    ArrayList<Double> b = new ArrayList<>();
    ArrayList<Double> s = new ArrayList<>();
    for (int i = 0; i < types.size(); i++)
    {
      if (types.get(i) == Side.Buy.ordinal())
      {
        b.add(markedPointSlice.get(i, 0));
      }
      else if (types.get(i) == Side.Sell.ordinal())
      {
        s.add(markedPointSlice.get(i, 0));
      }
      else
      {
        throw new IllegalArgumentException("hmm");
      }

    }
    buyTimes = new Vector(b);
    sellTimes = new Vector(s);
  }

  public void
         classifyTradeSequences()
  {
    TradeClassifier classifier = new TradeClassifier();

    int buyCount = 0;
    int sellCount = 0;
    int unclassifiedCount = 0;
    for (int i = 0; i < times.size(); i++)
    {
      double price = prices.get(i);
      Side side = classifier.classify(price);
      types.set(i, side.ordinal());
      classifier.record(price);
      double t = times.get(i);
      if (side == Side.Unknown)
      {
        unclassifiedCount++;
        side = Side.Buy;
        types.set(i, side.ordinal());
      }
      if (side == Side.Buy)
      {
        buyTimes.set(buyCount++, t);
      }
      else if (side == Side.Sell)
      {
        sellTimes.set(sellCount++, t);
      }

    }
    buyTimes = buyTimes.slice(0, buyCount);
    sellTimes = sellTimes.slice(0, sellCount);

    tradeIndexes = TradingStrategy.getIndices(times);
    buyIndexes = TradingStrategy.getIndices(buyTimes);
    sellIndexes = TradingStrategy.getIndices(sellTimes);
    //out.println("unclassified " + unclassifiedCount);
  }

  public Vector times;
  public Vector prices;
  public DoubleMatrix markedPoints;
  public IntVector types;
  public Vector buyTimes;
  public Vector sellTimes;
  public int[] tradeIndexes;
  public int[] buyIndexes;
  public int[] sellIndexes;
}