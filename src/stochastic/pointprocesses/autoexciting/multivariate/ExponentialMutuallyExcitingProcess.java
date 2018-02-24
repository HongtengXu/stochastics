
package stochastic.pointprocesses.autoexciting.multivariate;

import static fastmath.Functions.product;
import static fastmath.Functions.seq;
import static fastmath.Functions.sum;
import static fastmath.Functions.uniformRandom;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.concat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.IntToDoubleFunction;
import java.util.function.Supplier;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.SimpleBounds;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;

import fastmath.DoubleColMatrix;
import fastmath.DoubleMatrix;
import fastmath.EigenDecomposition;
import fastmath.Pair;
import fastmath.Vector;
import fastmath.exceptions.FastMathException;
import fastmath.optim.ExtendedBOBYQAOptimizer;
import fastmath.optim.ObjectiveFunctionSupplier;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import fastmath.optim.PointValuePairComparator;
import fastmath.optim.SolutionValidator;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.DiagonalExtendedApproximatePowerlawMututallyExcitingProcess;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess;
import stochastic.pointprocesses.finance.TradingFiltration;
import stochastic.pointprocesses.selfexciting.BoundedParameter;
import stochastic.pointprocesses.selfexciting.ExponentialSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.Type;
import util.DateUtils;

public abstract class ExponentialMutuallyExcitingProcess extends MutuallyExcitingProcess
{

  public ExponentialMutuallyExcitingProcess()
  {

  }

  public static String[] statisticNames =
  { "Log-Lik", "1-KS(Λ,exp)", "mean(Λ)", "var(Λ)", "MM(Λ)", "LB(Λ)", "MMLB(Λ)", "E[dt]" };

  /**
   * 
   * @param type
   *          type of process to spawn
   * @param filtration
   * @return
   */
  public static ExponentialMutuallyExcitingProcess
         spawnNewProcess(Type type,
                         TradingFiltration filtration)
  {
    assert filtration.times != null : "tradingProcess.times is null";
    assert filtration.types != null : "tradingProcess.types is null";
    assert filtration.markedPoints != null : "tradingProcess.markedPoints is null";

    if (type == Type.MultivariateDiagonalExtendedApproximatePowerlaw)
    {
      ExponentialMutuallyExcitingProcess process = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(2);
      process.T = filtration.times;
      process.K = filtration.types;
      process.X = filtration.markedPoints;
      return process;
    }
    else if (type == Type.MultivariateFullExtendedApproximatePowerlaw)
    {
      ExponentialMutuallyExcitingProcess process = new FullyCovariantExtendedMutuallyExcitingExponentialPowerlawApproximationProcess(2);
      process.T = filtration.times;
      process.K = filtration.types;
      process.X = filtration.markedPoints;
      return process;
    }
    else if (type == Type.UnitRandomWalkMultivariateDiagonalExtendedApproximatePowerlaw)
    {
      ExponentialMutuallyExcitingProcess process = new UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess(2);
      process.T = filtration.times;
      process.K = filtration.types;
      process.X = filtration.markedPoints;
      return process;
    }
    {
      throw new UnsupportedOperationException("TODO: " + type);
    }
  }

  protected Entry<Double, Integer>[][][] lowerEntries;

  protected Entry<Double, Integer>[][][] upperEntries;

  private final ObjectiveFunctionSupplier objectiveFunctionSupplier = () -> new ObjectiveFunction(copy());

  protected double[][][][] A;

  protected boolean verbose = false;

  private ExponentialDistribution expDist = new ExponentialDistribution(1);

  private int predictionIntegrationLimit = 25;

  protected Pair<Vector[], TreeMap<Double, Integer>[]> cachedSubTimes;

  public boolean trace = false;

  public int M = 15;

  public double
         A(int j,
           int m,
           int n,
           int i)
  {
    assert j < order();
    assert 0 <= n && n < dim();
    assert i <= N(m) : "i=" + i + " N(" + m + ")=" + N(m);
    assert 0 <= m && m < dim() : format("type=%d tk=%d j=%d dim=%d order=%d\n", m, n, j, dim(), order());

    if (A == null)
    {
      A = new double[T.size() + 1][order()][dim()][dim()];
    }
    if (i < 0)
    {
      return 0;
    }
    if (i == 0)
    {
      A[i][j][m][n] = 0;
      return 0;
    }
    double val = A[i][j][m][n];
    if (val == 0)
    {
      double Tmi = T(m, i);
      double Tmi1 = T(m, i - 1);

      double intersection = sum(k -> exp(-β(j, m, n) * (Tmi - T(n, k))), Nopen(n, Tmi1), Nopen(n, Tmi) - 1);
      val = intersection + (exp(-β(j, m, n) * (Tmi - Tmi1)) * A(j, m, n, i - 1));
      A[i][j][m][n] = val;
    }
    return val;
  }

  public double
         Asum(int j,
              int m,
              int n,
              int i)
  {
    assert i >= 0;
    if (i == 0)
    {
      return 0;
    }
    double Tmi = T(m, i);
    int l = Nopen(n, Tmi) - 1;
    return sum(k -> exp(-β(j, m, n) * (Tmi - T(n, k))), 0, l);
  }

  public double
         AsumToo(int j,
                 int m,
                 int n,
                 int i)
  {
    assert i >= 0;
    if (i == 0)
    {
      return 0;
    }
    double TmiPrev = T(m, i - 1);
    double Tmi = T(m, i);
    return exp(-β(j, m, n) * (Tmi - TmiPrev)) * Asum(j, m, n, i - 1) + sum(k -> exp(-β(j, m, n) * (Tmi - T(n, k))), Nopen(n, TmiPrev), Nopen(n, Tmi) - 1);

  }

  /**
   * Calculate sum(α(j,m,n)/β(j,m,n),j=1..order)
   * 
   * @return branching matrix of dimsion this{@link #dim} x this{@link #dim}
   */
  public DoubleColMatrix
         calculateBranchingMatrix()
  {
    DoubleColMatrix αβ = new DoubleColMatrix(dim(), dim());
    for (int j = 0; j < order(); j++)
    {
      for (int m = 0; m < dim(); m++)
      {
        for (int n = 0; n < dim(); n++)
        {
          αβ.add(n, n, α(j, m, n) / β(j, m, n));
        }
      }
    }
    return αβ;
  }

  public Vector
         calculateIntensityFast(Pair<Vector[], TreeMap<Double, Integer>[]> timesSubPair,
                                int m)
  {
    final Vector[] timesSub = timesSubPair.left;

    final Vector mtimes = timesSub[m];
    final int Nm = mtimes.size();
    Vector intensity = new Vector(Nm);
    for (int i = 0; i < Nm; i++)
    {
      double lambda = 0;
      final double mtime = mtimes.get(i);

      if (i == 0)
      {
        for (int n = 0; n < dim(); n++)
        {
          final Vector ntimes = timesSub[n];
          final int Nn = ntimes.size();
          final double z = Z(m, n);
          for (int j = 0; j < order(); j++)
          {
            final double αjmn = α(j, m, n);
            final double βjmn = β(j, m, n);
            double ntime;
            for (int k = 0; k < Nn && (ntime = ntimes.get(k)) < mtime; k++)
            {
              lambda += (αjmn / z) * exp(-βjmn * (mtime - ntime));
            }
          }
        }
      }
      else
      {
        for (int n = 0; n < dim(); n++)
        {
          final Vector ntimes = timesSub[n];
          final int Nn = ntimes.size();
          final double z = Z(m, n);
          for (int j = 0; j < order(); j++)
          {
            final double αjmn = α(j, m, n);
            final double βjmn = β(j, m, n);
            double ntime;
            lambda += (αjmn / z) * exp(-βjmn * (mtime - T(m, i - 1))) * A(j, m, n, i - 1);
            for (int k = Nopen(n, mtimes.get(i - 1)); k < Nn && (ntime = ntimes.get(k)) < mtime; k++)
            {
              lambda += (αjmn / z) * exp(-βjmn * (mtime - ntime));
            }
          }
        }
      }

      intensity.set(i, lambda);
    }
    return intensity;
  }

  public Vector
         calculateIntensitySlow(Pair<Vector[], TreeMap<Double, Integer>[]> timesSubPair,
                                int m)
  {
    final Vector[] timesSub = timesSubPair.left;

    final Vector mtimes = timesSub[m];
    final int Nm = mtimes.size();
    Vector intensity = new Vector(Nm);
    for (int i = 0; i < Nm; i++)
    {
      double lambda = 0;
      final double mtime = mtimes.get(i);

      for (int n = 0; n < dim(); n++)
      {
        final Vector ntimes = timesSub[n];
        final int Nn = ntimes.size();
        final double z = Z(m, n);
        for (int j = 0; j < order(); j++)
        {
          final double αjmn = α(j, m, n);
          final double βjmn = β(j, m, n);
          double ntime;
          for (int k = 0; k < Nn && (ntime = ntimes.get(k)) < mtime; k++)
          {
            lambda += (αjmn / z) * exp(-βjmn * (mtime - ntime));
          }
        }
      }

      intensity.set(i, lambda);
    }
    return intensity;
  }

  public int
         dim()
  {
    return dim;
  }

  public ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts,
                            IntConsumer progressNotifier,
                            String filename,
                            int section)
  {
    int maxIters = Integer.MAX_VALUE;

    MaxEval maxEval = new MaxEval(maxIters);
    SimpleBounds simpleBounds = getParameterBounds();
    AtomicInteger solutionCounter = new AtomicInteger();

    SolutionValidator validator = point -> {
      ExponentialMutuallyExcitingProcess process = newProcess(point.getPoint());
      // out.println(Thread.currentThread().getName() + " validating " + process + "
      // which has a LL score of " + process.logLik());

      for (int i = 0; i < process.dim(); i++)
      {
        double mean = process.Λ(i).mean();
        // out.println(Thread.currentThread().getName() + " process mean dim " + i + "
        // is " + mean);
        if (mean < 0)
        {
          return false;
        }
      }

      final int solutionCount = solutionCounter.incrementAndGet();
      File modelFile = new File(filename + "." + process.getType().getFilenameExtension() + "." + section + "." + solutionCount + ".model");
      double firstTimestampInInterval = DateUtils.convertTimeUnits(process.T.getLeftmostValue(), TimeUnit.MILLISECONDS, TimeUnit.HOURS);
      double lastTimestampInInterval = DateUtils.convertTimeUnits(process.T.getRightmostValue(), TimeUnit.MILLISECONDS, TimeUnit.HOURS);

      out.println("Storing estimated parameters in " + modelFile
                  + " covering the range "
                  + firstTimestampInInterval
                  + " to "
                  + lastTimestampInInterval
                  + " hours");

      try
      {
        process.storeParameters(modelFile);
      }
      catch (IOException e)
      {
        e.printStackTrace(System.err);
        throw new RuntimeException(e.getMessage(), e);
      }

      return true;
    };

    Supplier<MultivariateOptimizer> optimizerSupplier = () -> {
      ExtendedBOBYQAOptimizer optimizer = new ExtendedBOBYQAOptimizer(getParamCount() * dim() * 2 + 1, 10, 1E-5);
      // ExtendedBOBYQAOptimizer optimizer = new
      // ExtendedBOBYQAOptimizer(getParamCount() * ( dim() * dim() ) * 2 + 1, 10,
      // 1E-5);
      // optimizer.
      return optimizer;
    };

    ParallelMultistartMultivariateOptimizer multiopt = new ParallelMultistartMultivariateOptimizer(optimizerSupplier,
                                                                                                   numStarts,
                                                                                                   getRandomVectorGenerator(simpleBounds));

    PointValuePairComparator momentMatchingAutocorrelationComparator = (a,
                                                                        b) -> {
      try
      {
        ExponentialMutuallyExcitingProcess processA = newProcess(a.getPoint());
        ExponentialMutuallyExcitingProcess processB = newProcess(b.getPoint());
        // double mma = processA.getΛmomentLjungBoxMeasure();
        double mma = processA.getΛmomentMeasure();
        // double mmb = processB.getΛmomentLjungBoxMeasure();
        double mmb = processB.getΛmomentMeasure();
        return Double.compare(mma, mmb);
      }
      catch (Exception e)
      {
        e.printStackTrace(System.err);
        return 1;
      }
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

    return multiopt;
  }

  @Override
  public final double
         f(int m,
           int n,
           double t)
  {
    assert t >= 0 : "t cannot be negative, was " + t + " m=" + m + " n=" + n;
    return sum(j -> α(j, m, n) * exp(-β(j, m, n) * t), 0, order() - 1) / Z(m, n);
  }

  /**
   * @return 1+(∑_m∑_j(α[j,m,n]/β[j,m,n])*exp(-β[j,m,n]*t))/Z(type) <br>
   *         m=0..dim-1 j=0..order-1
   */
  @Override
  public final double
         F(int m,
           int n,
           double t)
  {
    return 1 - sum(j -> (α(j, m, n) / β(j, m, n)) * exp(-β(j, m, n) * t), 0, order() - 1) / Z(m, n);
  }

  @Override
  public final double
         getBranchingRatio()
  {
    return 1;
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

  /**
   * return a function of the Ljung-Box statistic which measures the amount of
   * autocorrelation remaining in the compensator up to lags of
   * this{@link #LJUNG_BOX_ORDER}
   * 
   * @return (Λ().getLjungBoxStatistic( this{@link #LJUNG_BOX_ORDER} ) - (
   *         this{@link #LJUNG_BOX_ORDER} - 2 ))^2
   */
  public final double
         getLjungBoxMeasure()
  {
    double x = 0;
    for (int type = 0; type < dim(); type++)
    {
      x += pow(Λ(type).getLjungBoxStatistic(ExponentialSelfExcitingProcess.LJUNG_BOX_ORDER) - (ExponentialSelfExcitingProcess.LJUNG_BOX_ORDER - 2), 2);
    }
    return x / dim();
  }

  @Override
  public final double
         getMeanSquaredPredictionError(int m)
  {
    throw new UnsupportedOperationException("TODO");
  }

  public final int
         getPredictionIntegrationLimit(int m)
  {
    return predictionIntegrationLimit;
  }

  public final UnivariateFunction
         getPredictiveDensity(final int m,
                              final Vector[] timesSub,
                              final TreeMap<Double, Integer>[] subTimeIndex,
                              final Vector mtimes,
                              final int N,
                              final double eta)
  {
    return new UnivariateFunction()
    {
      @Override
      public double
             value(double t)
      {
        final double A[][][] = new double[order()][dim()][dim()];
        mtimes.set(N - 1, t);
        final int dummy = N - 1;

        double sum = -eta;
        for (int i = 1; i < N; i++)
        {
          double upperTime = mtimes.get(i);
          double lowerTime = mtimes.get(i - 1);
          double lowerTimeBeforeLast = i > 2 ? mtimes.get(i - 2) : Double.NEGATIVE_INFINITY;
          // assert upperTime >= lowerTime;

          sum = 0;
          for (int n = 0; n < dim(); n++)
          {
            final Vector ntimes = timesSub[n];
            Entry<Double, Integer> lowerEntryBeforeLast =
                                                        Double.isInfinite(lowerTimeBeforeLast) ? null
                                                                                               : getLowerEntry(subTimeIndex, lowerTimeBeforeLast, m, n, i - 1);
            Entry<Double, Integer> lowerEntry = getLowerEntry(subTimeIndex, lowerTime, m, n, i);
            Entry<Double, Integer> upperEntry = getUpperEntry(subTimeIndex, upperTime, n, m, i);
            int lowerkBeforeLast = lowerEntryBeforeLast != null ? lowerEntryBeforeLast.getValue() : 0;
            int lowerk = lowerEntry != null ? lowerEntry.getValue() : 0;
            int upperk = upperEntry != null ? upperEntry.getValue() : mtimes.size();

            for (int j = 0; j < order(); j++)
            {
              final double αjmn = α(j, m, n);
              final double βjmn = β(j, m, n);
              double ktime;
              int k;
              double subsum = exp(-βjmn * (lowerTime - lowerTimeBeforeLast)) * A[j][m][n];
              for (k = lowerkBeforeLast; k < lowerk; k++)
              {
                ktime = ntimes.get(k);
                if (ktime < lowerTime && ktime >= lowerTimeBeforeLast)
                {
                  subsum += exp(-βjmn * (lowerTime - ktime));
                }
              }
              A[j][m][n] = subsum;

              double innerSum = subsum * (1 - exp(-βjmn * (upperTime - lowerTime)));
              for (k = lowerk; k < upperk; k++)
              {
                ktime = ntimes.get(k);
                if (ktime >= lowerTime && ktime < upperTime)
                {
                  innerSum += 1 - exp(-βjmn * (upperTime - ktime));
                }
              }

              sum += (αjmn / βjmn) * innerSum;

            }
          }
        }

        System.out.println("t=" + t + " eta=" + eta + " sum=" + sum);

        return sum;
      }
    };
  }

  protected final RandomVectorGenerator
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

  @Override
  public final double
         getRootMeanSquaredPredictionError(int m)
  {
    throw new UnsupportedOperationException("TODO");
  }

  /**
   * The mean of 1 minus the Kolmogorov Smirnov statistic averaged over each type
   * 1..dim
   */
  public final double
         getΛKolmogorovSmirnovStatistic()
  {
    return sum(m -> {
      Vector sortedCompensator = new Vector(Λ(m).doubleStream().sorted()).reverse();
      KolmogorovSmirnovTest ksTest = new KolmogorovSmirnovTest();
      double ksStatistic = ksTest.kolmogorovSmirnovStatistic(new ExponentialDistribution(1), sortedCompensator.toDoubleArray());
      return 1 - ksStatistic;
    }, 0, dim() - 1) / dim();
  }

  /**
   * functions which takes its minimum when the mean and the variance of the
   * compensator is closer to 1
   * 
   * @return this{@link #getΛmomentMeasure()} * log( 1 +
   *         this{@link #getLjungBoxMeasure()} )
   */
  public final double
         getΛmomentLjungBoxMeasure()
  {
    // return getΛmomentMeasure() * getLjungBoxMeasure() * -logLik();
    return getΛmomentMeasure() * log(1 + getLjungBoxMeasure());
  }

  /**
   * functions which takes its minimum when the mean and the variance of the
   * compensator is closer to 1
   * 
   * @return measure which is greater the closer the first two moments of the
   *         compensator are to unity
   */
  public final double
         getΛmomentMeasure()
  {
    double x = 0;
    for (int m = 0; m < dim(); m++)
    {
      Vector dT = Λ(m);
      Vector moments = dT.normalizedMoments(2);
      Vector normalizedSampleMoments = (moments.copy().subtract(1)).abs();
      x += normalizedSampleMoments.sum();
    }
    return x / dim();
  }

  /**
   * @return false if any of the absolute values of the eigenvalues of this
   *         {@link #calculateBranchingMatrix()} are >=1 or if the eigenvalue
   *         decomposition cannot be calculated
   */
  public final boolean
         isStationary()
  {
    EigenDecomposition eig;
    try
    {
      eig = calculateBranchingMatrix().eig();
    }
    catch (FastMathException e)
    {
      e.printStackTrace(err);
      return false;
    }
    for (int i = 0; i < dim(); i++)
    {
      double d = sqrt(pow(eig.getRealEigenvalues().get(i), 2) + pow(eig.getImaginaryEigenvalues().get(i), 2));
      if (d >= 1)
      {
        return false;
      }
    }
    return true;
  }

  public final Double
         lastT(int m)
  {
    assert m < dim() : "m=" + m + " >= dim";

    return getTimeSubsets().right[m].lastKey();
  }

  @Override
  public final void
         loadParameters(File modelFile) throws IOException
  {
    throw new UnsupportedOperationException("TODO");
  }

  /**
   * the log-likelihood, without the additive constant T so its not exactly the
   * true LL but the result is the same
   * 
   */
  public final double
         logLik()
  {
    double maxT = T.getRightmostValue();
    double ll = sum(m -> {
      Vector λvector = λvector(m).slice(1, N(m) - 1);

      Vector lslice = λvector.log();
      double compsum = sum(i -> sum(n -> sum(j -> {
        double α = α(j, m, n);
        double β = β(j, m, n);
        double x = ((α / β) * (1 - exp(-β * (maxT - T(m, i))))) / Z(m, n);
        // out.format("j=%d m=%d n=%d α=%f β=%f x=%f\n",j,m,n,α,β,x);
        return x;
      }, 0, order() - 1), 0, dim() - 1), 0, N(m) - 1);
      //out.println("compsum(m=" + m + ")=" + compsum);
      return lslice.sum() - compsum;
    }, 0, dim() - 1) + (maxT - T.getLeftmostValue());
    if (llcnt++ % 25 == 1)
    {
      out.println(Thread.currentThread().getName() + " " + this + "#" + llcnt + " ll=" + ll);
    }
    return ll;

  }

  /**
   * the log-likelihood, without the additive constant T so its not exactly the
   * true LL but the result is the same
   * 
   */
  public final double
         logLikSlower()
  {
    double ll = sum(m -> {
      Vector λvector = λvector(m).slice(1, N(m) - 1);

      Vector lslice = λvector.log();
      Vector comp = Λ(m);
      double compsum = comp.sum();

      return lslice.sum() - compsum;
    }, 0, dim() - 1) + (T.getRightmostValue() - T.getLeftmostValue());
    if (llcnt++ % 10 == 0)
    {
      out.println(Thread.currentThread().getName() + " #" + llcnt + " " + this + " = " + " ll=" + String.format("%30.30f", ll));
    }
    return ll;

  }

  @Override
  public final double
         meanRecurrenceTime(int m)
  {

    Vector beta = new Vector(seq((IntToDoubleFunction) j -> β(j, m, m), 0, order() - 1));
    Vector gamma = new Vector(seq((IntToDoubleFunction) j -> γ(j, m, m, 2), 0, order() - 1));
    double numerator = sum(j -> γ(j, m, m, 2), 0, order() - 1);
    double dp = product(k -> β(k, m, m), 0, order() - 1);
    double ds = sum(j -> γ(j, m, m, 1), 0, order() - 1);
    double denominator = dp * ds;
    double ratio = (numerator / denominator) / Z(m, m);
    // out.format("meanRecurrenceTime m=%d numerator=%35.35f dp=%s ds=%s
    // denominator=%30.30f ratio=%30.30f order=%d\nbeta=%s\ngamma=%s\n",
    // m,
    // numerator,
    // dp,
    // ds,
    // denominator,
    // ratio,
    // order(),
    // Arrays.toString(beta.toDoubleArray()),
    // Arrays.toString(gamma.toDoubleArray()));
    return ratio;
  }

  /**
   * counting function for the number of events of a specified type and occuring
   * at or before a specified time
   * 
   * @param base
   *          of event, integer in [0,dim)
   * @param t
   * 
   * @return number of events of type m before time t
   */
  public final int
         Nclosed(int type,
                 double t)
  {
    Entry<Double, Integer> entry = getTimeSubsets().right[type].floorEntry(t);
    return entry == null ? 0 : (entry.getValue() + 1);
  }

  public ExponentialMutuallyExcitingProcess
         newProcess(double[] point)
  {
    ExponentialMutuallyExcitingProcess process = (ExponentialMutuallyExcitingProcess) this.clone();
    process.assignParameters(point);
    return process;
  }

  /**
   * counting function for the number of events of a specified type and occuring
   * *strictly before* a specified time
   * 
   * @param base
   *          of event, integer in [0,dim)
   * @param t
   * 
   * @return number of events of type m before time t
   */
  public final int
         Nopen(int type,
               double t)
  {
    Entry<Double, Integer> entry = getTimeSubsets().right[type].lowerEntry(t);
    return entry == null ? 0 : (entry.getValue() + 1);
  }

  @Override
  public final int
         order()
  {
    return M + 1;
  }

  /**
   *
   * @param timesSubPair
   * @param m
   *          dim
   * @param eta
   *          i.i.d. exponential random variable with mean 1
   * @return
   */
  public final double
         predict(Pair<Vector[], TreeMap<Double, Integer>[]> timesSubPair,
                 final int m)
  {
    final Vector[] timesSub = timesSubPair.left;
    final TreeMap<Double, Integer>[] subTimeIndex = timesSubPair.right;
    final Vector mtimes = timesSub[m].extend(1);
    final int N = mtimes.size();
    final double lastTime = mtimes.get(mtimes.size() - 2);

    BrentSolver solver = new BrentSolver(pow(10, -15));
    Vector inverses = new Vector(10000);
    for (int l = 0; l < inverses.size(); l++)
    {
      final double eta = expDist.sample();
      UnivariateFunction integrand = getPredictiveDensity(m, timesSub, subTimeIndex, mtimes, N, eta);
      double nextTime = solver.solve(100000, integrand, lastTime - 0.0001, lastTime + 100);
      inverses.set(l, nextTime);
    }
    return inverses.mean();
  }

  public void
         setAsize(int sampleCount)
  {
    A = new double[sampleCount][order()][dim()][dim()];
  }

  public void
         setPredictionIntegrationLimit(int predictionIntegrationLimit)
  {
    this.predictionIntegrationLimit = predictionIntegrationLimit;
  }

  public void
         storeParameters(File modelFile) throws IOException
  {
    FileOutputStream fileOutputStream = new FileOutputStream(modelFile, false);
    DataOutputStream dos = new DataOutputStream(fileOutputStream);
    Vector params = getParameters();
    for (int i = 0; i < getParamCount(); i++)
    {
      dos.writeDouble(params.get(i));
    }
    dos.close();
    fileOutputStream.close();
  }

  /**
   * get time of i-th point of the m-th proces
   * 
   * @param m
   *          ordinal, integer in [0,dim)
   * @param i
   *          time index, starts at 0
   * @return
   */
  public double
         T(int m,
           int i)
  {
    if (i < 0)
    {
      return 0;
    }
    assert i >= 0 : "i cannot be negative, was " + i;
    assert m < dim() : "m=" + m + " >= dim";
    Vector Tm = getTimeSubsets().left[m];
    assert i < Tm.size() : format("m=%s i=%s Tm.size=%s\n", m, i, Tm.size());
    return Tm.get(i);
  }

  @Override
  public final double
         totalΛ()
  {
    return sum(k -> Λ(k).sum(), 0, dim() - 1);
  }

  /**
   * 
   * @param m
   *          dimension/index of the process, an integer in [0,dim)
   * 
   * @return ∏(∏(β(m,n,j),j=1..P),n=1..M)
   */
  public final double
         v(int m)
  {
    return product((IntToDoubleFunction) j -> product((IntToDoubleFunction) n -> β(m, n, j), 0, dim() - 1), 0, order() - 1);
  }

  @Override
  public final double
         value(double[] point)
  {
    ExponentialMutuallyExcitingProcess clone = copy();
    clone.assignParameters(point);

    double score = Double.NaN;

    score = clone.logLik();

    // if (verbose)
    // {
    // out.println(Thread.currentThread().getName() + " " + this + " LL score=" +
    // score);
    // }

    return score;

  }

  /**
   * 
   * @return normalizing constant such that the branching rate equals 1
   */
  @Override
  public final double
         Z(int m,
           int n)
  {
    double c = sum(j -> {
      double a = α(j, m, n);
      double b = β(j, m, n);
      return a / b;
    }, 0, order() - 1);
    return c != 0.0 ? c : 1;
  }

  /**
   * 
   * @param j
   *          index in [0,order()-1]
   * @param m
   *          from type in [0,dim-1]
   * @param n
   *          to type in [0,dim-1]
   * @return the j-th element of the Vector of parameters corresponding to the
   *         k-th type
   */
  protected abstract double
            α(int j,
              int m,
              int n);

  /**
   * 
   * @param j
   *          index in [0,order()-1]
   * @param m
   *          from type in [0,dim-1]
   * @param n
   *          to type in [0,dim-1]
   * @return the j-th element of the Vector of parameters corresponding to the
   *         k-th type
   */
  protected abstract double
            β(int j,
              int m,
              int n);

  public double
         γ(int k,
           int m,
           int n)
  {
    return γ(k, m, n, 1);
  }

  public double
         γ(int k,
           int m,
           int n,
           int p)
  {
    IntToDoubleFunction a = j -> j == k ? α(j, m, n) : pow(β(j, m, n), p);

    return product(a, 0, order() - 1);
  }

  public double
         η(int m,
           double dt)
  {
    double Tm = lastT(m);

    double vol = sum(j -> sum(n -> β(m, n, j), 0, dim() - 1), 0, order() - 1);

    double measure = (dt + Tm) * vol;
    out.println("vol=" + vol + " dt=" + dt + " Tm=" + Tm + " measure=" + measure);
    return exp(measure);
  }

  /**
   * the conditional intensity of the m-th dimension of the process at time t
   * 
   * @param m
   * @param d
   * @return
   */
  public double
         λ(int m,
           double t)
  {
    assert t >= 0 : "t cannot be negative, was " + t;
    /**
     * TODO: recursive intensity
     */
    // out.println("λ(m=" + m + ", t=" + t);
    return sum(n -> sum(k -> f(m, n, t - T(n, k)), 0, Nopen(n, t) - 1), 0, dim() - 1);
  }

  public Vector
         Λ(int m)
  {
    Pair<Vector[], TreeMap<Double, Integer>[]> timesSubPair = getTimeSubsets();
    final Vector[] timesSub = timesSubPair.left;
    final TreeMap<Double, Integer>[] subTimeIndex = timesSubPair.right;
    final Vector mtimes = timesSub[m];
    final int N = mtimes.size();
    Vector compensator = new Vector(N - 1);
    double A[][][] = new double[order()][dim()][dim()];

    for (int i = 1; i < N; i++)
    {
      double upperTime = mtimes.get(i);
      double lowerTime = mtimes.get(i - 1);
      double lowerTimeBeforeLast = i > 2 ? mtimes.get(i - 2) : Double.NEGATIVE_INFINITY;

      double sum = 0;
      for (int n = 0; n < dim(); n++)
      {
        final Vector ntimes = timesSub[n];
        Entry<Double, Integer> lowerEntryBeforeLast = Double.isInfinite(lowerTimeBeforeLast) ? null
                                                                                             : getLowerEntry(subTimeIndex, lowerTimeBeforeLast, m, n, i - 1);
        Entry<Double, Integer> lowerEntry = getLowerEntry(subTimeIndex, lowerTime, m, n, i);
        Entry<Double, Integer> upperEntry = getUpperEntry(subTimeIndex, upperTime, m, n, i);
        int lowerkBeforeLast = lowerEntryBeforeLast != null ? lowerEntryBeforeLast.getValue() : 0;
        int lowerk = lowerEntry != null ? lowerEntry.getValue() : 0;
        int upperk = upperEntry != null ? upperEntry.getValue() : ntimes.size();

        for (int j = 0; j < order(); j++)
        {
          final double αjmn = α(j, m, n);
          final double βjmn = β(j, m, n);
          double ktime;
          int k;
          double subsum = exp(-βjmn * (lowerTime - lowerTimeBeforeLast)) * A[j][m][n];
          for (k = lowerkBeforeLast; k < lowerk; k++)
          {
            ktime = ntimes.get(k);
            if (ktime < lowerTime && ktime >= lowerTimeBeforeLast)
            {
              subsum += exp(-βjmn * (lowerTime - ktime));
            }
          }
          A[j][m][n] = subsum;

          double innerSum = subsum * (1 - exp(-βjmn * (upperTime - lowerTime)));
          for (k = lowerk; k < upperk; k++)
          {
            ktime = ntimes.get(k);
            if (ktime >= lowerTime && ktime < upperTime)
            {
              innerSum += 1 - exp(-βjmn * (upperTime - ktime));
            }
          }

          sum += (αjmn / βjmn) * innerSum / Z(m, n);

        }
      }
      compensator.set(i - 1, sum);
    }
    return compensator;

  }

  /**
   * 
   * @param m
   *          ordinal ranging from 0 to this{@link #dim()}-1
   * @param tk
   * @return ∫λ(t)dt where t ranges from T[tk] to T[tk+1]
   */
  public double
         Λ(int m,
           int tk)
  {
    assert 0 <= m && m < dim();
    return Λ(m).get(tk);
  }

  /**
   * n-th compensated point, expensive non-recursive O(n^2) runtime version
   * 
   * @param i
   *          >= 1 and <= n
   * @return sum(k -> iψ(T.get(i + 1) - T.get(k)) - iψ(T.get(i) - T.get(k)), 0,
   *         i-1)
   */
  public final double
         Λ(int m,
           int i,
           double dt)
  {
    return sum(n -> sum(j -> (α(j, m, n) / β(j, m, n)) * (1 - (exp(-β(j, m, n) * dt))) * (1 + A(j, m, n, i)), 0, order() - 1) / Z(m, n), 0, dim() - 1);
  }

  public Vector
         Λslow(int m)
  {
    final Vector mtimes = getTimes(m);
    final int N = mtimes.size();
    Vector compensator = new Vector(N - 1);
    for (int i = 1; i < N; i++)
    {
      double upperTime = mtimes.get(i);
      double lowerTime = mtimes.get(i - 1);
      double sum = 0;
      for (int n = 0; n < dim(); n++)
      {
        final Vector ntimes = getTimes(n);
        final int Nn = ntimes.size();
        final double Z = Z(m, n);

        for (int j = 0; j < order(); j++)
        {
          final double αjmn = α(j, m, n);
          final double βjmn = β(j, m, n);
          double ktime;
          int k = 0;

          for (k = 0; k < Nn && (ktime = ntimes.get(k)) < upperTime; k++)
          {
            sum += ((αjmn / βjmn) * (exp(-βjmn * (lowerTime - ktime)) - exp(-βjmn * (upperTime - ktime)))) / Z;
          }

        }
      }
      compensator.set(i - 1, sum);
    }
    return compensator;
  }

  public final Vector
         λvector(int m)
  {
    return calculateIntensityFast(getTimeSubsets(), m);

  }

  public final Vector
         λvectorSlow(int m)
  {
    return calculateIntensitySlow(getTimeSubsets(), m);

  }

  /**
   * 
   * @param y
   *          exponentially distributed random variable
   * @return the value of T[n+1] that will cause the compensator to be equal to
   *         Λ(T[n],T[N+1])=y
   */
  public double
         invΛ(int m,
              double y)
  {
    double dt = 0;

    double δ = 0;
    double lastTime = (T == null || T.isEmpty()) ? 0 : T.getRightmostValue();
    double nextTime = lastTime;

    for (int i = 0; i <= 1000; i++)
    {
      δ = φδ(m, dt = (nextTime - lastTime), y);

      if (trace)
      {
        out.println("double dt[" + i + "]=" + dt + " δ=" + δ + " for y=" + y);
      }
      nextTime = nextTime + δ;
      if (abs(δ) < 1E-10 || !Double.isFinite(δ))
      {
        break;
      }

    }
    return dt;
  }

  public double
         φ(int m,
           double dt,
           double y)
  {
    int tk = getTimes(m).size() - 1;
    return φ(m, dt, y, tk);
  }

  public Vector
         φvec(int m,
              double dt,
              double y,
              int tk)
  {
    return new Vector(seq((IntToDoubleFunction) j -> γ(j, m, m) * (Asum(j, m, m, tk)) * (exp(-dt * β(j, m, m)) - 1), 0, order() - 1));
  }

  public double
         φ(int m,
           double dt,
           double y,
           int tk)
  {
    // return sum(j -> γ(j, m, m) * (1 + A(j, m, m, tk)) * (exp(-dt * β(j, m, m)) -
    // 1), 0, order() - 1) + y * βproduct(m, m) * Z(m, m);

    return sum(n -> sum(j -> γ(j, m, n) * (1 + A(j, m, n, tk)) * (exp(-dt * β(j, m, n)) - 1), 0, order() - 1) + y * βproduct(m, n) * Z(m, n), 0, dim() - 1);
  }

  public double
         βproduct(int m,
                  int n)
  {
    return product(k -> β(k, m, n), 0, order() - 1);
  }

  public double
         φdt(int m,
             double dt)
  {
    int tk = getTimes(m).size() - 1;

    return φdt(m, dt, tk);
  }

  public double
         φdt(int m,
             double dt,
             int tk)
  {
    return sum(n -> sum(j -> γ(j, m, n) * (1 + A(j, m, n, tk)) * β(j, m, n) * exp(-(dt) * β(j, m, n)), 0, order() - 1), 0, dim() - 1);
  }

  @Override
  public double
         φδ(int m,
            double t,
            double y)
  {
    int tk = getTimes(m).size() - 1;
    return φ(m, t, y, tk) / φdt(m, t, tk);
  }

  @Override
  public double
         φδ(int m,
            double t,
            double y,
            int tk)
  {
    return φ(m, t, y, tk) / φdt(m, t, tk);
  }

  public String
         getαβString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int j = 0; j < order(); j++)
    {
      for (int m = 0; m < dim(); m++)
      {
        for (int n = 0; n < dim(); n++)
        {
          sb.append(format(", alpha[%d,%d,%d]=%s, beta[%d,%d,%d]=%s", j + 1, m + 1, n + 1, α(j, m, n), j + 1, m + 1, n + 1, β(j, m, n)));
        }
      }
    }
    sb.append("}");
    return sb.toString();
  }

  public DoubleMatrix
         getαMatrix(int j)
  {
    DoubleColMatrix alpha = new DoubleColMatrix(dim, dim);
    for (int m = 0; m < dim(); m++)
    {
      for (int n = 0; n < dim(); n++)
      {
        alpha.set(m, n, α(j, m, n));
      }
    }
    return alpha.setName("α[" + j + "]");
  }

  public DoubleMatrix
         getβMatrix(int j)
  {
    DoubleColMatrix β = new DoubleColMatrix(dim, dim);
    for (int m = 0; m < dim(); m++)
    {
      for (int n = 0; n < dim(); n++)
      {
        β.set(m, n, β(j, m, n));
      }
    }
    return β.setName("β[" + j + "]");
  }

  public DoubleMatrix
         getαβMatrix(int j)
  {
    DoubleColMatrix αβ = new DoubleColMatrix(dim, dim);
    for (int m = 0; m < dim(); m++)
    {
      for (int n = 0; n < dim(); n++)
      {
        αβ.set(m, n, α(j, m, n) / β(j, m, n));
      }
    }
    return αβ.setName("(α/β)[" + j + "]");
  }

  public abstract Vector
         getParameters();

  public abstract void
         assignParameters(double[] point);

  /**
   * 
   * @param field
   * @param j
   * @return the n-th element of the {@link Vector} referenced by field
   */
  public double
         getFieldValue(Field field,
                       int m,
                       int n)
  {
    return getMatrixField(field).get(m, n);
  }

  public abstract String
         getParamString();

  public final synchronized Field[]
         getParameterFields()
  {
    if (parameterFields == null)
    {
      BoundedParameter[] parameters = getBoundedParameters();
      parameterFields = new Field[parameters.length];
      int i = 0;
      for (BoundedParameter param : parameters)
      {
        parameterFields[i++] = getField(param.getName());
      }
    }
    return parameterFields;
  }

  public final DoubleColMatrix
         getMatrixField(int field)
  {
    return getMatrixField(getParameterFields()[field]);
  }

  public final DoubleColMatrix
         getMatrixField(Field field)
  {
    try
    {
      DoubleColMatrix matrix = (DoubleColMatrix) field.get(this);
      if (matrix == null)
      {
        matrix = new DoubleColMatrix(dim, dim).setName(field.getName());
        field.set(this, matrix);
      }
      return matrix;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void
         expandA()
  {
    // if (trace)
    {
      out.println("Expanding A to size " + (int) (T.size() * 1.2));
    }
    double[][][][] newA = new double[(int) (T.size() * 1.2)][order()][dim()][dim()];

    for (int i = 0; i < A.length; i++)
    {
      double[][][] bMatrix = A[i];
      newA[i] = new double[order()][dim()][dim()];
      System.arraycopy(bMatrix, 0, newA[i], 0, order());
    }
    A = newA;
  }

  public void
         appendTime(int m,
                    double nextTime)
  {
    double dt = nextTime - (T.isEmpty() ? 0 : T.getRightmostValue());
    assert dt >= 0 : "dt cannot be negative, dt=" + dt + " where nextTime=" + nextTime + " and max(T)=" + T.getRightmostValue();
    if (trace)
    {
      out.println("appendTime(m=" + m + ", nextTime=" + nextTime);
    }
    T = T.copyAndAppend(nextTime);
    K = K.copyAndAppend(m);
    dT[m] = dT[m].copyAndAppend(dt);
    if (A.length < T.size())
    {
      expandA();
    }
    // TODO: updated cachedSubTimes, upperEntries and lowerEntries instead of
    // rebuilding it each call
    cachedSubTimes = null;
    upperEntries = null;
    lowerEntries = null;
  }
}
