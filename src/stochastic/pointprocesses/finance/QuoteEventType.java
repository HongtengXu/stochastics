package stochastic.pointprocesses.finance;

import static java.lang.System.out;

import java.util.EnumSet;

/**
 * In [La07], J. Large try to work with ten types of orders: <br>
 * <br>
 * 1. Market buy that moves the ask value <br>
 * 2. Market sale that moves the bid value <br>
 * 3. arriving bid moves best bid up <br>
 * 4. arriving ask moves best ask down <br>
 * 5. Market buy that doesn't move the ask value <br>
 * 6. Market sale that doesn't move the bid value <br>
 * 7. Bid at or below bid value <br>
 * 8. Ask at or above ask value <br>
 * 9. Cancelled bids <br>
 * 10. Cancelled asks <br>
 * 11. Midprice moves (the union of the bid and ask value moves)<br>
 * 
 * TODO: enhance this code to use {@link EnumSet}s, since they do really
 * efficient bit-shifting indices and boolean operations quickly, and this is
 * really what all of measure-theory boils down to, is sigma-algebras
 * 
 * @author crow
 *
 */
public enum QuoteEventType
{
  MARKET_BUY_PRICE_MOVES(Side.Buy, true, true, "TODO"),
  MARKET_SALE_PRICE_MOVES(Side.Sell, true, true, "TODO"),
  BID_INSIDE(Side.Buy, false, true, "TODO"),
  ASK_INSIDE(Side.Sell, false, true, "TODO"),
  MARKET_SALE_PRICE_STAYS(Side.Buy, true, false, "a sale large enough to change the price occured"),
  MARKET_BUY_PRICE_STAYS(Side.Sell, true, false, "a buy large enough to change the price occured"),
  BID_OUTSIDE(Side.Buy, false, false, "an order was added to the books, but it didn't change the inside price"),
  ASK_OUTSIDE(Side.Sell, false, false, "an order was added to the books, but it didn't change the inside price"),
  BID_CANCEL(Side.Buy, false, false, "a posted limit order was withdrawn"),
  ASK_CANCEL(Side.Sell, false, false, "a posted limit order was withdrawn"),
  MARKET_TRADE_PRICE_STAYS(null, true, false, "a trade occured, but wasn't large enough to change the price"),
  MARKET_TRADE_PRICE_MOVES(null, true, true, "a trade large enough to change the price occured"),
  MIDPRICE_MOVES(null, true, true, "midprice(arithmetic mean of inside bid and ask price) jumps (a signed measure)");

  private final Side side;

  private final boolean isImmediatelyExecutedMarketOrder;

  private final boolean movesPrice;

  private final String description;

  QuoteEventType(Side side, boolean isImmediatelyExecutedMarketOrder, boolean movesPrice, String description)
  {
    this.side = side;
    this.isImmediatelyExecutedMarketOrder = isImmediatelyExecutedMarketOrder;
    this.movesPrice = movesPrice;
    this.description = description;
  }

  public Side getSide()
  {
    return side;
  }

  public boolean getIsImmediatelyExecutedMarketOrder()
  {
    return isImmediatelyExecutedMarketOrder;
  }

  public boolean getMovesPrice()
  {
    return movesPrice;
  }

  public String getDescription()
  {
    return description;
  }

  public static void main( String args[] )
  {
    for ( QuoteEventType qet : QuoteEventType.values() )
    {
      out.println( qet.ordinal() + " → " + qet );
    }
  }
}
