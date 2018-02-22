package stochastic.pointprocesses.finance;

import fastmath.Vector;

public class TwoSidedQuote extends ArchivableEvent
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public int getEventType()
  {
    return EventType.TwoSidedQuote.ordinal();
  }

  @Override
  public void setStrings()
  {

  }

  public static final int FIELDCNT = Fields.values().length;

  public static enum Fields
  {
   Time,
   Ask,
   AskSize,
   Bid,
   BidSize
  };

  protected String symbol;
  protected long volume = 0;
  protected long receiveTime;
  protected long callbackTime;
  protected String mmidBid;
  protected String mmidAsk;
  private String exchangeBid;
  private String exchangeAsk;

  protected TwoSidedQuote()
  {
    super( TwoSidedQuote.class, FIELDCNT );
  }

  public TwoSidedQuote(Vector point, String symbol)
  {
    super( TwoSidedQuote.class, point );
    this.symbol = symbol;
  }

  @Override
  public String toString()
  {
    return String.format( "TwoSidedQuote[time=%d,timeOfDay=%f,symbol=%s, bid=%s@%s, ask=%s@%s]",
                          getTimestamp(),
                          getTimeOfDay(),
                          getSymbol(),
                          getBidSize(),
                          getBid(),
                          getAskSize(),
                          getAsk() );
  }

  public double getAsk()
  {
    return marks.get( Fields.Ask.ordinal() );
  }

  public void setAsk( double ask )
  {
    marks.set( Fields.Ask.ordinal(), ask );
  }

  public int getAskSize()
  {
    return (int) marks.get( Fields.AskSize.ordinal() );
  }

  public void setAskSize( int askSz )
  {
    marks.set( Fields.AskSize.ordinal(), askSz );
  }

  public double getBid()
  {
	  try
	  {
    return marks.get( Fields.Bid.ordinal() );
	  }
	  catch(IndexOutOfBoundsException iobe)
	  {
		  iobe.printStackTrace( System.err );
		  return Double.NaN;
	  }
    // .Fields.return bid;
  }

  public void setBid( double bid )
  {
    marks.set( Fields.Bid.ordinal(), bid );
    // this.bid = bid;
  }

  public int getBidSize()
  {
    return (int) marks.get( Fields.BidSize.ordinal() );
  }

  public void setBidSize( int bidSz )
  {
    marks.set( Fields.BidSize.ordinal(), bidSz );
  }

  @Override
  public String getSymbol()
  {
    return symbol;
  }

  public void setSymbol( String ticker )
  {
    this.symbol = ticker;
  }

  public long getVolume()
  {
    return volume;
  }

  public void setVolume( long volume )
  {
    this.volume = volume;
  }

  @Override
  public int hashCode()
  {
    return marks.hashCode();
  }

  @Override
  public boolean equals( Object obj )
  {
    if ( this == obj )
      return true;
    if ( obj == null )
      return false;
    if ( getClass() != obj.getClass() )
      return false;
    TwoSidedQuote other = (TwoSidedQuote) obj;
    return marks.equals( other.marks );
  }

  @Override
  public boolean isQuote()
  {
    return true;
  }

  /**
   * 
   * @return bid + ask / 2
   */
  public double getMidPrice()
  {
    return ( getBid() + getAsk() ) / 2;
  }

  /**
   * 
   * @return ask - bid
   */
  public double getSpread()
  {
    return getAsk() - getBid();
  }

  public String getExchangeBid()
  {
    return exchangeBid;
  }

  public void setExchangeBid( String exchangeBid )
  {
    this.exchangeBid = exchangeBid;
  }

  public String getExchangeAsk()
  {
    return exchangeAsk;
  }

  public void setExchangeAsk( String exchangeAsk )
  {
    this.exchangeAsk = exchangeAsk;
  }
}
