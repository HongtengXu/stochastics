package stochastic.pointprocesses.selfexciting;

import static java.lang.System.out;

import java.io.IOException;

import fastmath.Vector;
import fastmath.matfile.MatFile;
import stochastic.processes.NormalInverseGaussianProcess;

public class InnovationEstimator
{

  public static void
         main(String[] args) throws IOException
  {
    Vector innov = MatFile.loadMatrix("test0.mat", "innov").asVector();
    out.println("mean innov=" + innov.mean());
    NormalInverseGaussianProcess nigProcess = new NormalInverseGaussianProcess();
    nigProcess.α = 0.02;
    nigProcess.β = 0.01;
    nigProcess.δ = 20;
    nigProcess.μ = 4;
    double ll = nigProcess.logLik(innov.toDoubleArray());
    out.println("ll=" + ll);

    nigProcess.estimateParameters(Runtime.getRuntime().availableProcessors() * 1000, innov.toDoubleArray(), iters -> out.println("#iters " + iters));

    // NormalInverseGaussianDist nigDist =
    // DistributionFactory.getDistributionMLE(NormalInverseGaussianDist.class,
    // innov.toDoubleArray(), innov.size());
    // out.println( "estimated " + nigDist );
  }

}
