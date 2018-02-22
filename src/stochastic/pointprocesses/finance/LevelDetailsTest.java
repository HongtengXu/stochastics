package stochastic.pointprocesses.finance;

import junit.framework.TestCase;

public class LevelDetailsTest extends TestCase
{
  public void testEquals1() throws CloneNotSupportedException
  {
    AskLevelDetails AskLevelDetails = new AskLevelDetails( "TEST", 313.37, Side.Buy );
    AskLevelDetails.getOrders().put( "A", 200 );
    AskLevelDetails.getOrders().put( "B", 400 );
    AskLevelDetails.getOrders().put( "C", 100 );

    AskLevelDetails clonedAskLevelDetails = (AskLevelDetails) AskLevelDetails.clone();
    assertEquals( AskLevelDetails, clonedAskLevelDetails );
  }

  public void testEquals2() throws CloneNotSupportedException
  {
    AskLevelDetails AskLevelDetails = new AskLevelDetails( "TEST", 313.37, Side.Buy );
    AskLevelDetails.getOrders().put( "A", 200 );
    AskLevelDetails.getOrders().put( "B", 400 );
    AskLevelDetails.getOrders().put( "C", 100 );

    AskLevelDetails clonedAskLevelDetails = (AskLevelDetails) AskLevelDetails.clone();
    clonedAskLevelDetails.setSide( Side.Sell );
    assertFalse( AskLevelDetails.equals( clonedAskLevelDetails ) );
  }

  public void testEquals3() throws CloneNotSupportedException
  {
    AskLevelDetails AskLevelDetails = new AskLevelDetails( "TEST", 313.37, Side.Buy );
    AskLevelDetails.getOrders().put( "A", 200 );
    AskLevelDetails.getOrders().put( "B", 400 );
    AskLevelDetails.getOrders().put( "C", 100 );

    AskLevelDetails clonedAskLevelDetails = (AskLevelDetails) AskLevelDetails.clone();
    clonedAskLevelDetails.setTotSize( 300 );
    assertFalse( AskLevelDetails.equals( clonedAskLevelDetails ) );
  }

  /**
   * Unit test for Bug#14
   * 
   * @throws CloneNotSupportedException
   * 
   * @see http://nickel/show_bug.cgi?id=14
   */
  public void testEquals4() throws CloneNotSupportedException
  {
    AskLevelDetails AskLevelDetails = new AskLevelDetails( "TEST", 313.37, Side.Buy );
    AskLevelDetails.getOrders().put( "A", 200 );
    AskLevelDetails.getOrders().put( "B", 400 );
    AskLevelDetails.getOrders().put( "C", 100 );

    AskLevelDetails clonedAskLevelDetails = (AskLevelDetails) AskLevelDetails.clone();
    clonedAskLevelDetails.getOrders().put( "C", 101 );

    assertFalse( AskLevelDetails.equals( clonedAskLevelDetails ) );
  }
}
