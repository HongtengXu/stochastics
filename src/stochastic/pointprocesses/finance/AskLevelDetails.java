package stochastic.pointprocesses.finance;

public class AskLevelDetails extends LevelDetails
{

  private static final long serialVersionUID = 1L;

  public AskLevelDetails()
  {
    super();
  }

  public AskLevelDetails(String symbol, double price, Side side)
  {
    super( symbol, price, side );
  }

  @Override
  public String toString()
  {
    return "Ask" + super.toString();
  }

  @Override
  public Object clone() throws CloneNotSupportedException
  {
    LevelDetails clonedLevelDetails = new AskLevelDetails( symbol, price, getSide() );
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
