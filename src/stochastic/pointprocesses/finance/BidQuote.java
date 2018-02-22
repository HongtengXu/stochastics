package stochastic.pointprocesses.finance;

public class BidQuote extends Quote
{
  @Override
  public String toString()
  {
    return "Bid" + super.toString();
  }

  public BidQuote()
  {

  }

  public BidQuote(String symbol,
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
  public boolean isBid()
  {
    return true;
  }

  @Override
  public boolean isAsk()
  {
    return false;
  }

  @Override
  public Side getSide()
  {
    return Side.Buy;
  }

  @Override
  public int getEventType()
  {
    // TODO Auto-generated method stub
    return 0;
  }

}
