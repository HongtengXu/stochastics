package stochastic.pointprocesses.finance;

import junit.framework.TestCase;

public class ArchivableEventTest extends TestCase
{
  public void testGetTimeOfDay()
  {
    ArchivableEvent event = new AskQuote();
    event.setTimestamp( 1441396457230l );
    double tod = event.getTimeOfDay();
    assertEquals( 15.904786111116408, tod ); // nearly four o'clock
  }
}
