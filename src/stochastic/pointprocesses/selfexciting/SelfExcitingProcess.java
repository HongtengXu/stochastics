package stochastic.pointprocesses.selfexciting;

import fastmath.Vector;

public interface SelfExcitingProcess
{

  double getBranchingRatio();

  double logLikelihood(Vector t);

  /**
   * 
   * @return unconditional mean intensity
   */
  double getStationaryλ();

  public double
         φδ(double t,
            double y);

  public double
         φδ(double t,
            double y,
            int tk);

  /**
   * 
   * @param t
   * @return conditional mean intensity
   */
  double λ(double t);

  public Vector
         Λ();

}