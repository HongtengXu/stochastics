package stochastic.pointprocesses.finance;

import java.io.Serializable;

import fastmath.Vector;

public class TradeTick extends ArchivableEvent implements Serializable
{
  @Override
  public void setStrings()
  {

  }

  @Override
  public int getEventType()
  {
    return EventType.TradeTick.ordinal();
  }

  public static enum Fields
  {
   Time,
   Price,
   Volume,
   CumulativeVolume,
  }

  private static final long serialVersionUID = 1L;

  public static final int FIELDCNT = Fields.values().length;

  private String symbol;

  public TradeTick()
  {
    super( TradeTick.class, FIELDCNT );
  }

  public TradeTick(Vector point, String symbol)
  {
    super( TradeTick.class, point );
    this.symbol = symbol;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ( ( marks == null ) ? 0 : marks.hashCode() );
    return result;
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
    TradeTick other = (TradeTick) obj;
    if ( symbol == null )
    {
      if ( other.symbol != null )
        return false;
    }
    else if ( !symbol.equals( other.symbol ) )
      return false;
    return true;
  }

  public long getCumulativeVolume()
  {
    return (int) marks.get( Fields.CumulativeVolume.ordinal() );
  }

  public double getPrice()
  {
    return marks.get( Fields.Price.ordinal() );
  }

  @Override
  public String getSymbol()
  {
    return symbol;
  }

  public int getVolume()
  {
    return (int) marks.get( Fields.Volume.ordinal() );
  }

  public void setCumulativeVolume( long cumulativeVolume )
  {
    marks.set( Fields.CumulativeVolume.ordinal(), cumulativeVolume );
  }

  public void setPrice( double price )
  {
    marks.set( Fields.Price.ordinal(), price );
  }

  public void setVolume( int volume )
  {
    marks.set( Fields.Volume.ordinal(), volume );
  }

  @Override
  public String toString()
  {
    return String.format( "TradeTick[time=%d, timeOfDay=%f, symbol=%s, volume=%s, cumulativeVolume=%s, price=%s]",
                          getTimestamp(),
                          getTimeOfDay(),
                          getSymbol(),
                          getVolume(),
                          getCumulativeVolume(),
                          getPrice() );
  }

  @Override
  public boolean isQuote()
  {
    return false;
  }

}
