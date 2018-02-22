package stochastic.pointprocesses.finance;

import java.io.Serializable;

public class ClosePrice extends ArchivableEvent implements Serializable
{
  public static enum Fields
  {
   Time,
   QuoteSource,
   Symbol,
   Price
  }

  public static final int FIELDCNT = Fields.values().length;

  public ClosePrice()
  {
    super( ClosePrice.class, FIELDCNT );
  }

  public ClosePrice(String quoteSource, String symbol, double price)
  {
    super( ClosePrice.class, FIELDCNT );
    this.quoteSource = quoteSource;
    this.symbol = symbol;
    this.price = price;
  }

  private static final long serialVersionUID = 1L;

  private String quoteSource;
  private String symbol;
  private double price;

  public String getQuoteSource()
  {
    return quoteSource;
  }

  public void setQuoteSource( String quoteSource )
  {
    this.quoteSource = quoteSource;
  }

  @Override
  public String getSymbol()
  {
    return symbol;
  }

  public void setSymbol( String symbol )
  {
    this.symbol = symbol;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice( double price )
  {
    this.price = price;
  }

  @Override
  public String toString()
  {
    return String.format( "ClosePrice[%s, quoteSource=%s, symbol=%s, price=%s]", getTimeString(), quoteSource, getSymbol(), price );
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits( price );
    result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
    result = prime * result + ( ( quoteSource == null ) ? 0 : quoteSource.hashCode() );
    result = prime * result + ( ( symbol == null ) ? 0 : symbol.hashCode() );
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
    ClosePrice other = (ClosePrice) obj;
    if ( Double.doubleToLongBits( price ) != Double.doubleToLongBits( other.price ) )
      return false;
    if ( quoteSource == null )
    {
      if ( other.quoteSource != null )
        return false;
    }
    else if ( !quoteSource.equals( other.quoteSource ) )
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

  @Override
  public int getEventType()
  {
    // TODO Auto-generated method stub
    return 0;
  }

}
