package stochastic.pointprocesses.finance;

public class BidLevelDetails extends LevelDetails
{

  private static final long serialVersionUID = 1L;

  public BidLevelDetails()
  {
    super();
  }

  public BidLevelDetails(String symbol, double price, Side side)
  {
    super( symbol, price, side );
  }

  @Override
  public String toString()
  {
    return "Bid" + super.toString();
  }

  @Override
  public Object clone() throws CloneNotSupportedException
  {
    LevelDetails clonedLevelDetails = new BidLevelDetails( symbol, price, getSide() );
    clonedLevelDetails.setOrders( new Orders( getOrders() ) );
    clonedLevelDetails.setTotSize( getTotSize() );
    return clonedLevelDetails;
  }

  @Override
  public int getEventType()
  {
    // TODO Auto-generated method stub
    return 0;
  }

}
