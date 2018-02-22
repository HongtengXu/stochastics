package stochastic.pointprocesses.finance;

import static java.lang.System.out;

import junit.framework.TestCase;

public class TradeClassifierTest extends TestCase
{
  public void testLimit()
  {
    TradeClassifier classifier = new TradeClassifier();
    classifier.record(8.19);
    out.println( "classifier=" + classifier );
    classifier.record(8.18);
    out.println( "classifier=" + classifier );
    classifier.record(8.2);
    out.println( "classifier=" + classifier );
    classifier.record(8.2);
    out.println( "classifier=" + classifier );
    classifier.record(8.2);
    out.println( "classifier=" + classifier );
    classifier.record(8.29);
    out.println( "classifier=" + classifier );
    classifier.record(8.31);
    out.println( "classifier=" + classifier );
    classifier.record(8.31);
    out.println( "classifier=" + classifier );
    classifier.record(8.31);
    out.println( "classifier=" + classifier );
    
  }
}
