package stochastic.pointprocesses.selfexciting;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.ansi;

import java.io.Serializable;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;

import fastmath.Vector;
import fastmath.matfile.MatFile;
import junit.framework.TestCase;

public class ExtendedApproximatePowerlawSelfExcitingProcess extends ApproximatePowerlawSelfExcitingProcess implements MultivariateFunction, Serializable
{

  @Override
  public Type
         getType()
  {
    return Type.ExtendedApproximatePowerlaw;
  }

  public ExtendedApproximatePowerlawSelfExcitingProcess()
  {
  }

  public static enum Parameter implements BoundedParameter
  {
    τ(1, 10), ε(0, 0.5), η(0.1, 10), b(0, 10);

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

  public double b;

  public double η;

  @Override
  public BoundedParameter[]
         getBoundedParameters()
  {
    return Parameter.values();
  }

  @Override
  public int
         order()
  {
    return M + 1;
  }

//  @Override
//  public double
//         Z()
//  {
//    return (ε < 1E-14) ? (b * η + M) : ((pow(τ, -ε) * (pow(m, ε) - pow(m, -ε * (M - 1)))) / (pow(m, ε) - 1) + b * η);
//  }

  @Override
  public double
         α(int i)
  {
    return i < M ? super.α(i) : αS();
  }

  public double
         αS()
  {
    return b;
  }

  @Override
  public double
         β(int i)
  {
    return i < M ? super.β(i) : βS();
  }

  public double
         βS()
  {
    return 1 / η;
  }

  public void
         setAsize(int sampleCount)
  {
    A = new double[sampleCount][order()];
  }

  public Vector
         simulate(int seed,
                  int sampleCount)
  {
    int lastRejectedPoint = -1;
    int rejects = 0;
    ExponentialDistribution expDist = new ExponentialDistribution(new JDKRandomGenerator(seed), 1);
    T = new Vector(new double[]
    { 0 });
    out.println("simulating " + ansi().fgBrightYellow() + this + ansi().fgDefault() + " from " + T.size() + " points with seed=" + seed);
    int n = T.size();
    double nextTime = 0;
    double startTime = currentTimeMillis();
    setAsize(sampleCount);
    for (int i = 0; i < sampleCount; i++)
    {

      double y = expDist.sample();
      trace = false;
      // TODO: average over Λ and compare against the invariant projection
      double dt = invΛ(y);
      if (dt > 10000 || dt < 0.001)
      {
        int pointsSinceLastRejection = lastRejectedPoint == -1 ? 0 : (i - lastRejectedPoint);
        lastRejectedPoint = i;
        rejects++;
        out.println("seed " + seed
                    + ":"
                    + ansi().fgBrightRed()
                    + "rejecting dt="
                    + dt
                    + " for y="
                    + y
                    + "#"
                    + rejects
                    + " points since last reject="
                    + pointsSinceLastRejection
                    + ansi().fgDefault());
        continue;
      }
      trace = false;
      // Real dtReal = process.invΛReal(y);
      // if ( dtReal.fpValue() > 6669)
      // {
      // out.println( "clamping " + dtReal );
      // dtReal = new Real(dt);
      // }
      trace = false;

      // double dtRealFpValue = dtReal.fpValue();
      double q = Λ(n - 1, dt);
      nextTime = (T.getRightmostValue() + dt);
      double marginalΛ = invΛ(1);
      // out.println("marginalΛ=" + marginalΛ);

      TestCase.assertEquals("y != q", y, q, 1E-7);
      n++;
      appendTime(nextTime);
      double Edt = nextTime / n;
      // out.println("T=" + process.T.toIntVector());
      // out.println("Λ=" + process.Λ().slice(max(0, process.T.size() - 10),
      // process.T.size() - 1));
      if (i % 1000 == 0)
      {
        String msg = "seed=" + seed
                     + " i="
                     + i
                     + " y="
                     + y
                     + " = q = "
                     + q
                     + " dt="
                     + dt
                     + " marginal="
                     + marginalΛ
                     + " Λmean="
                     + Λ().mean()
                     + " Λvar="
                     + Λ().variance()
                     + " nextTime="
                     + nextTime
                     + " Edt="
                     + Edt;
        out.println(msg);

      }

      // String msg = "i=" + i + " y=" + y + " = q = " + q + " dt=" + dt + " dtReal="
      // + dtReal + " dtRealFpValue=" + dtRealFpValue + " nextTime=" + nextTime;
      if (abs(y - q) > 1E-8)
      {
        out.println(seed + ":" + ansi().fgBrightRed() + " rejecting dt=" + dt + " for y=" + y + " q=" + q + "# " + rejects + ansi().fgDefault());
        continue;
      }

    }
    double duration = startTime - currentTimeMillis();

    String fn = "simulated." + seed + ".mat";
    MatFile.write(fn, T.setName("T").createMiMatrix());
    out.println("write " + fn);
    double seconds = duration / 1000;
    double pointsPerSecond = sampleCount / seconds;
    out.println("simulation rate: " + pointsPerSecond + " points/second");
    // process.printA();

    return T;
  }

}
