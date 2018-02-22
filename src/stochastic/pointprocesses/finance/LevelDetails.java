package stochastic.pointprocesses.finance;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LevelDetails extends ArchivableEvent implements Cloneable, Serializable
{
  public static final int FIELDCNT = 6;
  
  final static class Orders extends ConcurrentHashMap<String, Integer>
  {
    private static final long serialVersionUID = 1L;

    public Orders(Map<String, Integer> mmids)
    {
      super( mmids );
    }

    public Orders()
    {
    }

  }

  @Override
  public abstract Object clone() throws CloneNotSupportedException;

  public LevelDetails(long timestamp, double price, Side side, String symbol, Map<String, Integer> mmids, int totSize)
  {
    super( LevelDetails.class, FIELDCNT );
    this.setTimestamp( timestamp );
    this.price = price;
    this.setSide( side );
    this.symbol = symbol;
    this.orders = new Orders( mmids );
    this.totSize = totSize;
  }

  private static final long serialVersionUID = 1L;

  double price = Double.NaN;
  private Side side;
  String symbol;
  private Orders orders = new Orders();

  private int totSize;

  public LevelDetails()
  {
    super( LevelDetails.class, FIELDCNT );
  }

  public LevelDetails(String symbol, double price, Side side)
  {
    super( LevelDetails.class, FIELDCNT );
    this.symbol = symbol;
    this.side = side;
    this.price = price;
    this.setTotSize( 0 );
  }

  // Call this when same side/price MMID or size for an MMID changes
  public long onMMIDChanged( String mmid, int size )
  {
    Integer currVal = ( size != 0 ) ? orders.put( mmid, size ) : orders.remove( mmid );
    setTotSize( getTotSize() + ( currVal == null ? size : size - currVal ) );
    return getTotSize();
  }

  public Orders getOrders()
  {
    return orders;
  }

  @Override
  public String getSymbol()
  {
    return symbol;
  }

  @Override
  public String toString()
  {
    return String.format( "LevelDetails[%s, price=%s, side=%s, symbol=%s, orders=%s, totSize=%s]",
                          getTimeString(),
                          price,
                          getSide(),
                          symbol,
                          orders,
                          getTotSize() );
  }

  public double getPrice()
  {
    return price;
  }

  public int getTotSize()
  {
    return totSize;
  }

  public void setTotSize( int totSize )
  {
    this.totSize = totSize;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ( ( orders == null ) ? 0 : orders.hashCode() );
    long temp;
    temp = Double.doubleToLongBits( price );
    result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
    result = prime * result + ( ( getSide() == null ) ? 0 : getSide().hashCode() );
    result = prime * result + ( ( symbol == null ) ? 0 : symbol.hashCode() );
    result = prime * result + ( totSize ^ ( totSize >>> 32 ) );
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
    LevelDetails other = (LevelDetails) obj;
    if ( orders == null )
    {
      if ( other.orders != null )
        return false;
    }
    else if ( !orders.equals( other.orders ) )
      return false;
    if ( Double.doubleToLongBits( price ) != Double.doubleToLongBits( other.price ) )
      return false;
    if ( getSide() != other.getSide() )
      return false;
    if ( symbol == null )
    {
      if ( other.symbol != null )
        return false;
    }
    else if ( !symbol.equals( other.symbol ) )
      return false;
    if ( totSize != other.totSize )
      return false;
    return true;
  }

  public Side getSide()
  {
    return side;
  }

  public void setSide( Side side )
  {
    this.side = side;
  }

  /**
   * @return the orders
   */
  public Orders getMmids()
  {
    return orders;
  }

  /**
   * @param orders
   *          the orders to set
   */
  public void setOrders( Orders mmids )
  {
    this.orders = mmids;
  }

}