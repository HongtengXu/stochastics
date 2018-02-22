package stochastic.pointprocesses.autoexciting.multivariate.diagonal;

import stochastic.pointprocesses.selfexciting.BoundedParameter;


/**
 * Special case of {@link DiagonalExtendedApproximatePowerlawMututallyExcitingProcess} where τ[1..m]=[1..1] and ε=[0......0]
 *
 */
public class UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess extends DiagonalExtendedApproximatePowerlawMututallyExcitingProcess
{

  public static enum Parameter implements BoundedParameter
  {
    η(0.1, 10), b(0, 5);

    Parameter(double min, double max)
    {
      this.min = min;
      this.max = max;
    }

    private double max;

    private double min;

    @Override
    public double
           getMax()
    {
      return max;
    }

    @Override
    public double
           getMin()
    {
      return min;
    }

    @Override
    public String
           getName()
    {
      return name();
    }

    @Override
    public int
           getOrdinal()
    {
      return ordinal();
    }

  }

  @Override
  public BoundedParameter[]
         getBoundedParameters()
  {
    return Parameter.values();
  }

  public UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess(int dim)
  {
    super(dim);
    for ( int i = 0; i < dim; i++ )
    {
      τ.set(i,1);
      ε.set(i,0);
    }
  }

}
