package stochastic.pointprocesses.selfexciting;

import static java.lang.System.out;

import java.io.IOException;

import fastmath.Vector;
import fastmath.matfile.MatFile;
import umontreal.iro.lecuyer.probdist.DistributionFactory;
import umontreal.iro.lecuyer.probdist.NormalInverseGaussianDist;

public class InnovationEstimator
{

  public static void
         main(String[] args) throws IOException
  {
    Vector innov = MatFile.loadMatrix("test0.mat", "innov").asVector();
    out.println( "mean innov=" + innov.mean() );
    //NormalInverseGaussianDist nigDist = DistributionFactory.getDistributionMLE(NormalInverseGaussianDist.class, innov.toDoubleArray(), innov.size());
    //out.println( "estimated " + nigDist );
  }

}
