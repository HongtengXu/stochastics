package stochastic.markovchains;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TransitionProbabilityMatrixTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void test()
  {
    TransitionProbabilityMatrix tpm = new TransitionProbabilityMatrix();
    tpm.add( 0, 0, 0.1 );
    tpm.add( 2, 3, 1.4 );
    tpm.add( 1, 1, 0.5 );
    tpm.add( 4, 2, 0.7 );
    tpm.add( 3, 2, 0.6 );
    tpm.add( 3, 4, 0.55 );
    tpm.normalize();
    tpm.rowSum().forEach( x -> {
      assertEquals( 1.0, x.doubleValue(), 0.000000001 );
    } );
  }

}
