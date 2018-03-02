package stochastic.pointprocesses.selfexciting;

import static fastmath.Functions.product;
import static fastmath.Functions.seq;
import static fastmath.Functions.sum;
import static fastmath.Functions.uniformRandom;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.concat;
import static org.apache.commons.lang.ArrayUtils.addAll;
import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.Supplier;

import javax.swing.JProgressBar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.SimpleBounds;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;

import fastmath.Functions;
import fastmath.Pair;
import fastmath.Vector;
import fastmath.optim.ExtendedBOBYQAOptimizer;
import fastmath.optim.ObjectiveFunctionSupplier;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import fastmath.optim.PointValuePairComparator;
import fastmath.optim.SolutionValidator;
import util.AutoArrayList;

public abstract class ExponentialSelfExcitingProcess extends AbstractSelfExcitingProcess implements MultivariateFunction, Cloneable, SelfExcitingProcess
{
  private static final int MAX_ITERS = 1000;

  public String
         getαβString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("{Z=" + Z());
    for (int i = 0; i < order(); i++)
    {
      sb.append(format(", alpha[%d]=%f, beta[%d]=%f", i + 1, α(i), i + 1, β(i)));
    }
    sb.append("}");
    return sb.toString();
  }

  public double
         meanRecurrenceTime()
  {
    Vector beta = new Vector(seq((IntToDoubleFunction) j -> β(j), 0, order() - 1));

    Vector gamma = new Vector(seq((IntToDoubleFunction) j -> γ(j, 2), 0, order() - 1));

    double numer = sum(j -> γ(j, 2), 0, order() - 1);
    double denom = (product(this::β, 0, order() - 1)) * sum(k -> γ(k, 1), 0, order() - 1) * Z();
    double ratio = numer / denom;
    // out.format("meanRecurrenceTime(univar) numerator=%20.20f denominator=%20.20f
    // ratio=%20.20f order=%d\nbeta=%s\ngamma=%s\n",
    // numer,
    // denom,
    // ratio,
    // order(),
    // Arrays.toString(beta.toDoubleArray()),
    // Arrays.toString(gamma.toDoubleArray()));
    return ratio;
  }

  /**
   * 
   * @param tk
   *          index of last (most recent) time to occur T[tk] in the point process
   * @param y
   *          exponentially distributed random variable
   * 
   * @return the value of T[n+1] that will cause the compensator to be equal to
   *         Λ(T[n],T[N+1])=y
   */
  public double
         invΛ(int tk,
              double y)
  {
    double dt = 0;

    double δ = 0;
    double lastTime = T.get(tk);
    double nextTime = lastTime;

    for (int i = 0; i <= 1000; i++)
    {
      δ = φδ(dt = (nextTime - lastTime), y, tk);

      if (trace)
      {
        out.println("double dt[" + i + "]=" + dt + " δ=" + δ);
      }
      nextTime = nextTime + δ;
      if (abs(δ) < 1E-10 || !Double.isFinite(δ))
      {
        break;
      }

    }
    return dt;
  }

  /**
   * 
   * @param y
   *          exponentially distributed random variable
   * 
   * @return the value of T[n+1] that will cause the compensator to be equal to
   *         Λ(T[n],T[N+1])=y
   */
  public double
         invΛ(double y)
  {
    double dt = 0;

    double δ = 0;
    double lastTime = T == null ? 0 : T.getRightmostValue();
    double nextTime = lastTime;

    for (int i = 0; i <= 1000; i++)
    {
      δ = φδ(dt = (nextTime - lastTime), y);

      if (trace)
      {
        out.println("double dt[" + i + "]=" + dt + " δ=" + δ);
      }
      nextTime = nextTime + δ;
      if (abs(δ) < 1E-10 || !Double.isFinite(δ))
      {
        break;
      }

    }
    return dt;
  }

  public Vector
         getαVector()
  {
    return new Vector(seq(this::α, 0, order() - 1)).setName("α");
  }

  public Vector
         getβVector()
  {
    return new Vector(seq(this::β, 0, order() - 1)).setName("β");
  }

  public Vector
         getαβVector()
  {
    return new Vector(seq((IntToDoubleFunction) k -> α(k) / β(k), 0, order() - 1)).setName("α/β");
  }

  /**
   * product(j -> j == k ? α(j) : β(j), 0, order() - 1)
   * 
   * @param k
   * @return this{@link #γ(int, int)}(k,1)
   */
  public double
         γ(int k)
  {
    return γ(k, 1);
  }

  /**
   * product(j -> j == k ? α(j) : β(j), 0, order() - 1)
   * 
   * @param k
   * @return
   */
  public double
         γ(int k,
           int p)
  {
    AutoArrayList<Double> a = γsfp.getOrCreate(p);
    return a.getOrCreate(k);
  }

  public double
         γproductfp(int k,
                    int p)
  {
    IntToDoubleFunction a = j -> j == k ? α(j) : pow(β(j), p);

    return product(a, 0, order() - 1);
  };

  private AutoArrayList<AutoArrayList<Double>> γsfp = new AutoArrayList<AutoArrayList<Double>>(p -> new AutoArrayList<>(k -> γproductfp(k, p)));

  /**
   * The mean lifetime can be looked at as a "scaling time", because the
   * exponential decay equation can be written in terms of the mean lifetime, τ,
   * instead of the decay constant, λ
   * 
   * @param k
   * @return
   */
  public double
         getScalingTime(int k)
  {
    return 1 / (γ(k));
  }

  /**
   * @return {@link Functions#product(IntFunction, int, int)}(this{@link #β(int)},0,this{@link #order()}-1)
   */
  public double
         βproduct()
  {
    return product(this::β, 0, order() - 1);
  }

  final static ExponentialDistribution expDist = new ExponentialDistribution(1);

  final static KolmogorovSmirnovTest ksTest = new KolmogorovSmirnovTest();

  private final ObjectiveFunctionSupplier objectiveFunctionSupplier = () -> new ObjectiveFunction(copy());

  public boolean trace = false;

  public boolean verbose = false;

  /**
   * functions which takes its minimum when the mean and the variance of the
   * compensator is closer to 1
   * 
   * @return measure which is greater the closer the first two moments of the
   *         compensator are to unity
   */
  public double
         getΛmomentMeasure()
  {
    Vector dΛ = Λ();
    if (dΛ.size() == 0)
    {
      return Double.NaN;
    }

    Vector moments = dΛ.normalizedMoments(2);
    Vector normalizedSampleMoments = (moments.copy().subtract(1)).abs();
    return normalizedSampleMoments.sum();
  }

  /**
   * functions which takes its minimum when the mean and the variance of the
   * compensator is closer to 1
   * 
   * @return this{@link #getΛmomentMeasure()} * log( 1 +
   *         this{@link #getLjungBoxMeasure()} )
   */
  public double
         getΛmomentLjungBoxMeasure()
  {
    return getΛmomentMeasure() * log(1 + getLjungBoxMeasure());
  }

  /**
   * return a function of the Ljung-Box statistic which measures the amount of
   * autocorrelation remaining in the compensator up to lags of
   * this{@link #LJUNG_BOX_ORDER}
   * 
   * @return (Λ().getLjungBoxStatistic( this{@link #LJUNG_BOX_ORDER} ) - (
   *         this{@link #LJUNG_BOX_ORDER} - 2 ))^2
   */
  public double
         getLjungBoxMeasure()
  {
    return pow(Λ().getLjungBoxStatistic(LJUNG_BOX_ORDER) - (LJUNG_BOX_ORDER - 2), 2);
  }

  public static final int LJUNG_BOX_ORDER = 10;

  public ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts)
  {
    return estimateParameters(numStarts, (IntConsumer) null);
  }

  public ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts,
                            JProgressBar progressMonitor)
  {
    return estimateParameters(numStarts, j -> progressMonitor.setValue(j));
  }

  public final ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts,
                            IntConsumer progressNotifier)
  {
    int maxIters = Integer.MAX_VALUE;

    MaxEval maxEval = new MaxEval(maxIters);
    SimpleBounds simpleBounds = getParameterBounds();

    SolutionValidator validator = point -> {
      ExponentialSelfExcitingProcess process = newProcess(point.getPoint());
      return process.Λ().mean() > 0;
    };

    Supplier<MultivariateOptimizer> optimizerSupplier = () -> new ExtendedBOBYQAOptimizer(getParamCount() * 2 + 1, 10, 1E-4);

    ParallelMultistartMultivariateOptimizer multiopt = new ParallelMultistartMultivariateOptimizer(optimizerSupplier,
                                                                                                   numStarts,
                                                                                                   getRandomVectorGenerator(simpleBounds));

    PointValuePairComparator momentMatchingAutocorrelationComparator = (a,
                                                                        b) -> {
      ExponentialSelfExcitingProcess processA = newProcess(a.getPoint());
      ExponentialSelfExcitingProcess processB = newProcess(b.getPoint());
      double mma = processA.getΛmomentLjungBoxMeasure();
      double mmb = processB.getΛmomentLjungBoxMeasure();
      return Double.compare(mma, mmb);
    };

    double startTime = currentTimeMillis();
    PointValuePair optimum = multiopt.optimize(progressNotifier,
                                               GoalType.MAXIMIZE,
                                               momentMatchingAutocorrelationComparator,
                                               validator,
                                               maxEval,
                                               objectiveFunctionSupplier,
                                               simpleBounds);
    double stopTime = currentTimeMillis();
    double secondsElapsed = (stopTime - startTime) / 1000;
    double evaluationsPerSecond = multiopt.getEvaluations() / secondsElapsed;
    double minutesElapsed = secondsElapsed / 60;

    assignParameters(optimum.getKey());

    out.format("estimation completed in %f minutes at %f evals/sec\n", minutesElapsed, evaluationsPerSecond);

    // plot("λ(t)", this::λ, T.fmin(), T.fmax(), 5000 );

    printResults(multiopt);

    return multiopt;
  }

  protected final double
            Λ(double dt,
              int tk)
  {
    assert dt >= 0 : "dt cannot be negative, was " + dt + " at tk=" + tk;
    double Λ = 0;
    for (int j = 0; j < order(); j++)
    {
      double α = α(j);
      double β = β(j);
      Λ += (α / β) * (1 - exp(-β * dt)) * A(j, tk);
    }
    if (trace)
    {
      out.println("A[" + tk + "]=" + Arrays.toString(A[tk]));
    }
    return Λ / Z();
  }

  public final double
         getBranchingRatio()
  {
    return sum(i -> α(i) / β(i), 0, order() - 1) / Z();
  }

  /**
   * 
   * @return a list formed by concatenating the names of the parameters enumerated
   *         by this{@link #getBoundedParameters()} and the names of the
   *         statistics enumerated by this{@link #statisticNames}
   */
  public String[]
         getColumnHeaders()
  {
    return concat(stream(getBoundedParameters()).map(param -> param.getName()), asList(statisticNames).stream()).collect(toList()).toArray(new String[0]);
  }

  public double
         getΛKolmogorovSmirnovStatistic()
  {

    KolmogorovSmirnovTest ksTest = new KolmogorovSmirnovTest();
    Vector comp = Λ();

    if (comp.size() == 0)
    {
      return Double.NaN;
    }
    Vector sortedCompensator = new Vector(comp.doubleStream().sorted()).reverse();
    double ksStatistic = ksTest.kolmogorovSmirnovStatistic(expDist, sortedCompensator.toDoubleArray());
    return 1 - ksStatistic;
  }

  protected RandomVectorGenerator
            getRandomVectorGenerator(SimpleBounds bounds)
  {
    return () -> {
      try
      {
        double[] point = rangeClosed(0,
                                     bounds.getLower().length - 1).mapToDouble(dim -> uniformRandom(new Pair<>(bounds.getLower()[dim], bounds.getUpper()[dim])))
                                                                  .toArray();
        if (trace)
        {
          out.println(Thread.currentThread().getName() + " starting from " + Arrays.toString(point));
        }
        return point;
      }
      catch (Exception e)
      {
        e.printStackTrace(System.err);
        return null;
      }
    };
  }

  public Vector
         getSpectrum(int n)
  {
    throw new UnsupportedOperationException("TODO: implement simulation then generate sample autocorrelation from simulated samples since the combinatorial complexixity of the analytic expression would require about 17 years to evaluate on even a really fast machine.");
  }

  @Override
  public double
         getStationaryλ()
  {
    return 1 / mean();
  }

  /**
   * integrated kernel function
   * 
   * @param t
   * @return
   */
  @Override
  public double
         F(double t)
  {
    return sum(i -> (α(i) / β(i)) * (1 - exp(-β(i) * t)), 0, order() - 1) / Z();
  }

  public void
         loadParameters(File file)
  {
    try
    {
      FileInputStream fileInputStream = new FileInputStream(file);
      DataInputStream dis = new DataInputStream(fileInputStream);
      Vector params = new Vector((int) (file.length() / Double.BYTES));
      for (int i = 0; i < params.size(); i++)
      {
        params.set(i, dis.readDouble());
      }
      dis.close();
      fileInputStream.close();
      assignParameters(params.toDoubleArray());
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public final double
         logLik()
  {
    if (T == null)
    {
      return Double.NaN;
    }
    Vector λvector = λvector().slice(1, T.size() - 1);

    Vector lslice = λvector.log();

    double tn = (T.getRightmostValue() - T.getLeftmostValue());
    // out.println( "tn=" + tn );
    double compSum = totalΛ();
    // out.println( "compSum=" + compSum );

    return tn + lslice.sum() - compSum;
    // A = new double[n][order()];
    //
    // double S[] = new double[order()];
    // for (int tk = 1; tk < n; tk++)
    // {
    // double t = T.get(tk);
    // double dt = t - T.get(tk - 1);
    // double λ = evolveλ(dt, t, S);
    //
    // if (λ > 0)
    // {
    // ll += log(λ);
    // }
    //
    // // ll -= Λ;
    //
    // }

    // if (Double.isNaN(ll))
    //
    // {
    // if (verbose)
    // {
    // out.println(Thread.currentThread().getName() + " NaN for LL ");
    // }
    // ll = Double.NEGATIVE_INFINITY;
    // }
    //
    // return ll;

  }

  @Override
  public double

         logLikelihood(Vector t)
  {
    ExponentialSelfExcitingProcess spawn = copy();
    spawn.T = t;
    return spawn.logLik();
  }

  /**
   * 
   * @return theoretical mean
   */
  public final double
         mean()
  {
    return nthNormalizedMoment(1);
  }

  public ExponentialSelfExcitingProcess
         newProcess(double[] point)
  {
    ExponentialSelfExcitingProcess process = (ExponentialSelfExcitingProcess) this.clone();
    process.assignParameters(point);
    return process;
  }

  private Vector
          normalizedMoments(int n)
  {
    return new Vector(rangeClosed(1, n).mapToDouble(i -> nthNormalizedMoment(i)));
  }

  /**
   * 
   * @return n'th (raw) moment E[X^n]
   */
  public double
         nthMoment(int n)
  {
    return nthNormalizedMoment(n) * factorial(n);
  }

  /**
   * 
   * @return n'th (raw) factorial moment E[X^n]/n!
   */
  public double
         nthNormalizedMoment(int n)
  {
    return sum(i -> (α(i) / pow(β(i), n + 1)), 0, order() - 1) / Z();
  }

  public abstract int
         order();

  public double
         Λ(int i)
  {
    return sum(j -> (α(j) / β(j)) * (1 - (exp(-β(j) * (T.get(i + 1) - T.get(i))))) * A(j, i), 0, order() - 1) / Z();
  }

  /**
   * n-th compensated point, expensive non-recursive O(n^2) runtime version
   * 
   * @param i
   *          >= 1 and <= n
   * @return sum(k -> iψ(T.get(i + 1) - T.get(k)) - iψ(T.get(i) - T.get(k)), 0,
   *         i-1)
   */
  public double
         Λ(int i,
           double dt)
  {
    return sum(j -> (α(j) / β(j)) * (1 - (exp(-β(j) * dt))) * A(j, i), 0, order() - 1) / Z();
  }

  Vector dT;

  Vector dT()
  {
    if (dT != null)
    {
      return dT;
    }
    else
    {
      return dT = T.diff();
    }
  }

  public void
         storeParameters(File file) throws IOException
  {
    FileOutputStream fileOutputStream = new FileOutputStream(file, false);
    DataOutputStream dos = new DataOutputStream(fileOutputStream);
    Vector params = getParameters();
    for (int i = 0; i < getParamCount(); i++)
    {
      dos.writeDouble(params.get(i));
    }
    dos.close();
    fileOutputStream.close();
  }

  @Override
  public String
         toString()
  {
    return getClass().getSimpleName() + getParamString();
  }

  public double
         totalΛ()
  {
    double tn = T.getRightmostValue();

    return (sum(i -> sum(j -> (α(j) / β(j)) * (1 - exp(-β(j) * (tn - T.get(i)))), 0, order() - 1), 0, T.size() - 1)) / Z();
  }

  @Override
  public final double
         value(double[] point)
  {
    assignParameters(point);
    return logLik();

  }

  /**
   * 
   * @return theoretical variance
   */
  public final double
         variance()
  {
    return sum(i -> (2 * α(i)) / pow(β(i), 3), 0, order() - 1) / Z();
  }

  public abstract double
         Z();

  /**
   * 
   * @param j
   *          index in [0,order()-1]
   * @return the j-th α parameter
   */
  public abstract double
         α(int j);

  /**
   * 
   * @param j
   *          index in [0,order()-1]
   * @return the j-th β parameter
   */
  public abstract double
         β(int j);

  /**
   * intensity function
   * 
   * @param t
   * 
   * @return intensity at time t
   */
  public final double
         λ(double t)
  {
    assert t >= 0 : "t cannot be negative, was " + t;

    DoubleAdder sum = new DoubleAdder();
    double s;
    for (int i = 0; i < T.size() && (s = T.get(i)) < t; i++)
    {
      double dt = t - s;
      sum.add(f(dt));
    }
    return sum.doubleValue();
  }

  public Vector
         Λ()
  {
    if (T == null)
    {
      return new Vector();
    }
    final int n = T.size() - 1;

    Vector compensator = new Vector(n);
    for (int i = 0; i < n; i++)
    {
      compensator.set(i, Λ(i));
    }
    return compensator.setName("Λ");

  }

  public Vector
         iΛ()
  {

    final int n = T.size() - 1;

    // return Λ(n);
    Vector integratedCompensator = new Vector(n);
    for (int i = 0; i < n; i++)
    {
      integratedCompensator.set(i, iΛ(i + 1));
    }
    return integratedCompensator.setName("iΛ");

  }

  /**
   * n-th integrated compensator, expensive non-recursive O(n^2) runtime version
   * 
   * @param i
   *          >= 1 and <= n
   * @return sum(k -> iψ(T.get(i + 1) - T.get(k)) - iψ(T.get(i) - T.get(k)), 0,
   *         i-1)
   */
  protected double
            iΛ(int i)
  {
    return sum(k -> sum(j -> (α(j) / β(j)) * (1 - (exp(-β(j) * (T.get(i) - T.get(k))))), 0, order() - 1), 0, i - 1) / Z();
  }

  public double
         NΦ(double y,
            double nextTime)
  {
    double lastTime = T.getRightmostValue();
    return nextTime - φδ(nextTime - lastTime, y);
  }

  /**
   * potential function
   * 
   * @param dt
   * @param y
   * @return
   */
  public double
         φ(double dt,
           double y)
  {
    int tk = T.size() - 1;
    return φ(dt, y, tk);
  }

  public Vector
         φvec(double dt,
              double y,
              int tk)
  {
    return new Vector(seq((IntToDoubleFunction) j -> γ(j) * A(j, tk) * (exp(-dt * β(j)) - 1), 0, order() - 1));
  }

  public double
         φ(double dt,
           double y,
           int tk)
  {
    return sum(j -> γ(j) * A(j, tk) * (exp(-dt * β(j)) - 1), 0, order() - 1) + y * βproduct() * Z();
  }

  public double
         φdt(double dt)
  {
    int tk = T.size() - 1;
    return φdt(dt, tk);
  }

  public double
         φdt(double dt,
             int tk)
  {
    return sum(j -> γ(j) * A(j, tk) * β(j) * exp(-(dt) * β(j)), 0, order() - 1);
  }

  /**
   * 
   * @param t
   * @param y
   * @return this{@link #φ(double, double)} / this{@link #φdt(double)}
   */
  public double
         φδ(double t,
            double y)
  {
    int tk = T == null ? -1 : (T.size() - 1);
    return φδ(t, y, tk);
  }

  public double
         φδ(double t,
            double y,
            int tk)
  {
    return φ(t, y, tk) / φdt(t, tk);
  }

  public Vector
         λvector()
  {
    final int n = T.size();
    Vector λ = new Vector(n);

    double S[] = new double[order()];
    for (int i = 1; i < n; i++)
    {
      double t = T.get(i);
      double dt = t - T.get(i - 1);
      λ.set(i, evolveλ(dt, T.get(i), S));
    }

    return λ;
  }

  /**
   * kernel function
   * 
   * @param t
   * @return
   */
  public final double
         f(double t)
  {
    return sum(i -> α(i) * exp(-β(i) * t), 0, order() - 1) / Z();
  }

  public static String[] statisticNames =
  { "Log-Lik", "KS(Λ)", "mean(Λ)", "var(Λ)", "MM(Λ)", "LB(Λ)", "MMLB(Λ)", "E[dt]" };

  double A[][];

  /**
   * @return an array whose elements correspond to this{@link #statisticNames}
   */
  public Object[]
         evaluateParameterStatistics(double[] point)
  {
    ExponentialSelfExcitingProcess process = newProcess(point);
    double ksStatistic = process.getΛKolmogorovSmirnovStatistic();

    Vector compensated = process.Λ();

    // out.println(compensated.autocor(30));

    Object[] statisticsVector = new Object[]
    { process.logLik(),
      ksStatistic,
      compensated.mean(),
      compensated.variance(),
      process.getΛmomentMeasure(),
      process.getLjungBoxMeasure(),
      process.getΛmomentLjungBoxMeasure(),
      process.meanRecurrenceTime() };

    return addAll(stream(getParameterFields()).map(param -> process.getFieldValue(param)).toArray(), statisticsVector);
  }

  public double
         Asum(int tk,
              int j)
  {
    if (tk < 0)
    {
      return 0;
    }
    double Ti = T.get(tk);
    return 1 + sum(k -> exp(-β(j) * (Ti - T.get(k))), 0, tk - 1);
  }

  public double
         A(int j,
           int tk)
  {
    if (tk < 0)
    {
      return 1;
    }
    if (A == null)
    {
      A = new double[T.size()][order()];
    }
    if (tk == 0)
    {
      A[tk][j] = 1;
      return 1;
    }
    double val = A[tk][j];
    if (val == 0)
    {
      double intersection = 1;
      // double a = A(j, tk - 1);
      double a = A[tk - 1][j];
      if (a == 0)
      {
        throw new IllegalArgumentException("A not populated at j=" + j + " tk=" + tk);
      }
      val = intersection + (exp(-β(j) * (T.get(tk) - T.get(tk - 1))) * a);
      assert val != 0 : String.format("A[%d][%d]=%s\n", tk, j, val);
      A[tk][j] = val;
    }
    return val;
  }

  public void
         printA()

  {
    for (int i = 0; i < A.length; i++)
    {
      out.println("A[" + i + "]=" + Arrays.toString(A[i]));
    }
  }

  /**
   * 
   * @param tk
   * @param j
   * @return this{@link #A(int, int)}(tk,j)-1
   */
  public double
         B(int tk,
           int j)
  {
    return A(j, tk) - 1;
  }

  protected final double
            evolveλ(double dt,
                    double t,
                    double[] S)
  {
    assert dt >= 0 : "dt cannot be negative, was " + dt + " at t=" + t;
    double λ = 0;
    for (int j = 0; j < order(); j++)
    {
      S[j] = exp(-β(j) * dt) * (1 + S[j]);
      λ += α(j) * S[j];
    }
    return λ / Z();
  }

  /**
   * time it takes the impulse to decay to half its initial value
   * 
   * @param i
   * @return log(2) / β(i) / Z
   */
  public double
         getHalfDuration(int i)
  {
    return log(2) / (β(i) / Z());
  }

  public void
         appendTime(double nextTime)
  {
    double dt = nextTime - T.getRightmostValue();
    assert dt >= 0 : "dt cannot be negative, dt=" + dt + " where nextTime=" + nextTime + " and max(T)=" + T.getRightmostValue();
    T = T.copyAndAppend(nextTime);
    dT = dT().copyAndAppend(dt);
    if (A.length < T.size())
    {
      expandA();
    }
  }

  public void
         expandA()
  {
    // if (trace)
    {
      out.println("Expanding A to size " + (int) (T.size() * 1.2));
    }
    double[][] newA = new double[(int) (T.size() * 1.2)][order()];

    for (int i = 0; i < A.length; i++)
    {
      double[] bMatrix = A[i];
      int aLength = bMatrix.length;
      newA[i] = new double[aLength];
      System.arraycopy(bMatrix, 0, newA[i], 0, aLength);
    }
    A = newA;
  }

}