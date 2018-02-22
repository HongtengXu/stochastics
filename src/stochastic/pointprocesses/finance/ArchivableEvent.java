package stochastic.pointprocesses.finance;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import fastmath.Vector;
import stochastic.annotations.Units;
import util.DateUtils;
import util.ThreadLocalDateFormat;

/**
 * 
 * @author crow
 */
public abstract class ArchivableEvent implements HasSymbol, Serializable
{

  private static final long serialVersionUID = 1L;


  protected long timestamp;

  protected transient Vector marks;

  // final private Vector marks;

  @Units(time = TimeUnit.SECONDS)
  public double getTimeInSeconds()
  {
    return (double) getTimestamp() / (double) MILLISECONDS_IN_SECOND;
  }

  public static final int SECONDS_PER_DAY = 86400;

  /**
   * 
   * @return hour of day past midnight according to this{@link #getTimestamp()}
   */
  @Units(time = TimeUnit.HOURS)
  public double getTimeOfDay()
  {
    double tod = getTimeInSeconds() % SECONDS_PER_DAY;
    double hod = tod / 3600.0;
    hod += DateUtils.getTimeZoneOffset( getTimestampInMilliseconds() );
    return hod;
  }

  public double getTimeOfDay( TimeUnit units )
  {
    return DateUtils.convertTimeUnits(getTimestamp(), TimeUnit.MILLISECONDS, units);   
  }
  
  /**
   * 1 thousand, 4 zeros
   */
  private static final long MILLISECONDS_IN_SECOND = 1000;

  protected <E extends ArchivableEvent> ArchivableEvent(Class<E> eventClass, int n)
  {
    marks = new Vector( n );
  }

  protected <E extends ArchivableEvent> ArchivableEvent(Class<E> eventClass, Vector point)
  {
    marks = point;
    setStrings();
  }

  /**
   * 
   * @return the date&time in human readable format
   */
  public String getTimeString()
  {
    return "t=" + ThreadLocalDateFormat.formatMS( getTimestamp() );
  }

  public long getTimestampInMilliseconds()
  {
    return getTimestamp();
  }

  public final long getArchiveTimestamp()
  {
    return getTimestamp();
  }

  /***
   * 
   * @return a timestamp in milliseconds
   */
  public final long getTimestamp()
  {
    return (long) marks.get( 0 );
    // return timestamp;
  }

  /**
   * 
   * @param timestamp
   *          (in unix-milliseconds)
   */
  public final void setTimestamp( long timestamp )
  {
    this.timestamp = timestamp;
    try
    {
      marks.set( 0, timestamp );
    }
    catch( IndexOutOfBoundsException oob )
    {
      throw new RuntimeException( this.getClass().getName(), oob );
    }
  }

  @Override
  public abstract boolean equals( Object obj );

  @Override
  public abstract int hashCode();

  /**
   * sub-classes override this to short-cut instanceof (is this necessary on
   * 'modern' VM's?') anyway, it can't hurt.
   * 
   * @return if this is either an {@link AskQuote} or a {@link BidQuote}
   */
  public boolean isQuote()
  {
    return false;
  }

  public Vector getMarks()
  {
    return marks;
  }

  public abstract int getEventType();

  public static enum EventType
  {
   TwoSidedQuote,
   TradeTick
  }

  public void setStrings()
  {
    // TODO Auto-generated method stub

  }

}
