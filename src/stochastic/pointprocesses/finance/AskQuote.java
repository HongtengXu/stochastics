package stochastic.pointprocesses.finance;

public class AskQuote extends Quote
{
  @Override
  public boolean isAsk()
  {
    return true;
  }
  
  @Override
  public boolean isBid()
  {
    return false;
  }
  
  @Override
  public String toString()
  {
    return "Ask" + super.toString();
  }

  public AskQuote()
  {

  }

  public AskQuote(String symbol,
                  int size,
                  double price,
                  String mmid,
                  String exchange,
                  long receiveTime,
                  long callbackTime)
  {
    super();
    this.symbol = symbol;
    this.size = size;
    this.price = price;
    this.mmid = mmid;
    this.exchange = exchange;
    this.receiveTime = receiveTime;
    this.callbackTime = callbackTime;
  }

  private static final long serialVersionUID = 1L;

  @Override
  public Side getSide()
  {
    return Side.Sell;
  }

  @Override
  public int getEventType()
  {
    // TODO Auto-generated method stub
    return 0;
  }

}
