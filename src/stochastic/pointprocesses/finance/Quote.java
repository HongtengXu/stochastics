package stochastic.pointprocesses.finance;

import java.io.Serializable;

public abstract class Quote extends ArchivableEvent implements Serializable
{
  public static final int FIELDCNT = 7;
  
  @Override
  public boolean isQuote()
  {
    return true;
  }

  private static final long serialVersionUID = 1L;

  protected String symbol;
  protected int size;
  protected double price;
  protected String mmid;
  protected String exchange;
  protected long receiveTime;
  protected long callbackTime;

  public Quote()
  {
    super( null, FIELDCNT );
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
    Quote other = (Quote) obj;
    if ( callbackTime != other.callbackTime )
      return false;
    if ( exchange == null )
    {
      if ( other.exchange != null )
        return false;
    }
    else if ( !exchange.equals( other.exchange ) )
      return false;
    if ( mmid == null )
    {
      if ( other.mmid != null )
        return false;
    }
    else if ( !mmid.equals( other.mmid ) )
      return false;
    if ( Double.doubleToLongBits( price ) != Double.doubleToLongBits( other.price ) )
      return false;
    if ( receiveTime != other.receiveTime )
      return false;
    if ( size != other.size )
      return false;
    if ( symbol == null )
    {
      if ( other.symbol != null )
        return false;
    }
    else if ( !symbol.equals( other.symbol ) )
      return false;
    return true;
  }

  public long getCallbackTime()
  {
    return callbackTime;
  }

  public String getExchange()
  {
    return exchange;
  }

  public String getMmid()
  {
    return mmid;
  }

  public double getPrice()
  {
    return price;
  }

  public long getReceiveTime()
  {
    return receiveTime;
  }

  public abstract Side getSide();
  
  public int getSize()
  {
    return size;
  }

  @Override
  public String getSymbol()
  {
    return symbol;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) ( callbackTime ^ ( callbackTime >>> 32 ) );
    result = prime * result + ( ( exchange == null ) ? 0 : exchange.hashCode() );
    result = prime * result + ( ( mmid == null ) ? 0 : mmid.hashCode() );
    long temp;
    temp = Double.doubleToLongBits( price );
    result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
    result = prime * result + (int) ( receiveTime ^ ( receiveTime >>> 32 ) );
    result = prime * result + size;
    result = prime * result + ( ( symbol == null ) ? 0 : symbol.hashCode() );
    return result;
  }

  public void setCallbackTime( long callbackTime )
  {
    this.callbackTime = callbackTime;
  }

  public void setExchange( String exchange )
  {
    this.exchange = exchange;
  }

  public void setMmid( String mmid )
  {
    this.mmid = mmid;
  }

  public void setPrice( double price )
  {
    this.price = price;
  }

  public void setReceiveTime( long receiveTime )
  {
    this.receiveTime = receiveTime;
  }

  public void setSize( int size )
  {
    this.size = size;
  }

  public void setSymbol( String symbol )
  {
    this.symbol = symbol;
  }

  @Override
  public String toString()
  {
    return String.format( "Quote[%s, symbol=%s, size=%s, price=%s, mmid=%s, exchange=%s, receiveTime=%s, callbackTime=%s]",
                          getTimeString(),
                          symbol,
                          size,
                          price,
                          mmid,
                          exchange,
                          receiveTime,
                          callbackTime );
  }

  public abstract boolean isBid();

  public abstract boolean isAsk();


}