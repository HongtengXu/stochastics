package stochastic.pointprocesses.finance;

import static java.util.Arrays.stream;

import java.util.stream.Stream;


public enum Side
{
 Buy,
 Sell,
 Unknown;

  public static Stream<Side> SIDES()
  {
    return stream( values() );
  }

  /**
   * 
   * @return 1 if this{@link #isBuy()}, -1 otherwise
   */
  public int sign()
  {
    return getSign();
  }

  public Side oppositeSide()
  {
    return isBuy() ? Sell : isSell() ? Buy : Unknown;
  }

  public boolean isBuy()
  {
    return ( this == Buy );
  }

  public boolean isSell()
  {
    return ( this == Sell );
  }

  public int getSign()
  {
    return isBuy() ? 1 : isSell() ? -1 : 0;
  }

  public static Side fromSignedVolume( int marketOrderVolume )
  {
    return marketOrderVolume > 0 ? Side.Buy : marketOrderVolume < 0 ? Side.Sell : Side.Unknown;
  }
}
