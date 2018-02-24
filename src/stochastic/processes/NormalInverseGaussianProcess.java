package stochastic.processes;

import static fastmath.Functions.sum;
import static fastmath.Functions.uniformRandom;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.concat;
import static org.apache.commons.lang.ArrayUtils.addAll;
import static util.Console.println;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.SimpleBounds;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.random.RandomVectorGenerator;

import cern.jet.math.Bessel;
import dnl.utils.text.table.TextTable;
import fastmath.Pair;
import fastmath.Vector;
import fastmath.optim.ExtendedBOBYQAOptimizer;
import fastmath.optim.ObjectiveFunctionSupplier;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import fastmath.optim.PointValuePairComparator;
import fastmath.optim.SolutionValidator;
import stochastic.pointprocesses.selfexciting.BoundedParameter;
import umontreal.iro.lecuyer.rng.RandomStream;
import umontreal.iro.lecuyer.stochprocess.InverseGaussianProcess;

public class NormalInverseGaussianProcess extends umontreal.iro.lecuyer.stochprocess.NormalInverseGaussianProcess implements MultivariateFunction
{

  public NormalInverseGaussianProcess(double x0, double alpha, double beta, double mu, double delta, RandomStream streamBrownian, InverseGaussianProcess igP)
  {
    super(x0, alpha, beta, mu, delta, streamBrownian, igP);
  }

  public NormalInverseGaussianProcess(double x0,
                                      double alpha,
                                      double beta,
                                      double mu,
                                      double delta,
                                      RandomStream streamBrownian,
                                      RandomStream streamIG1,
                                      RandomStream streamIG2,
                                      String igType)
  {
    super(x0, alpha, beta, mu, delta, streamBrownian, streamIG1, streamIG2, igType);
  }

  public NormalInverseGaussianProcess(double x0, double alpha, double beta, double mu, double delta, RandomStream streamAll, String igType)
  {
    super(x0, alpha, beta, mu, delta, streamAll, igType);
  }

  protected static enum Parameter implements BoundedParameter
  {
    x0(-10, 10), alpha(-10, 10), beta(10, 10), mu(-10000, -10000), delta(10, 10);

    private double min;
    private double max;

    Parameter(double min, double max)
    {
      this.min = min;
      this.max = max;
    }

    @Override
    public String
           getName()
    {
      return name();
    }

    @Override
    public double
           getMin()
    {
      return min;
    }

    @Override
    public double
           getMax()
    {
      return max;
    }

    @Override
    public int
           getOrdinal()
    {
      return ordinal();
    }

  }

  private boolean verbose;
  private Field[] parameterFields;

  public BoundedParameter[]
         getBoundedParameters()
  {
    return Parameter.values();
  }

  public SimpleBounds
         getParameterBounds()
  {

    BoundedParameter[] bounds = getBoundedParameters();
    final int paramCount = bounds.length;
    double[] lowerBounds = range(0, paramCount).mapToDouble(i -> bounds[i].getMin()).toArray();
    double[] upperBounds = range(0, paramCount).mapToDouble(i -> bounds[i].getMax()).toArray();
    return new SimpleBounds(lowerBounds, upperBounds);
  }

  public final Field
         getField(String name)
  {
    Class<? extends Object> oClass = getClass();
    NoSuchFieldException nsfe = null;
    try
    {
      Field field = null;
      while (field == null && oClass != null)
      {
        try
        {
          field = oClass.getDeclaredField(name);
        }
        catch (NoSuchFieldException e)
        {
          oClass = oClass.getSuperclass();
          nsfe = e;
        }
      }
      if (field == null)
      {
        NoSuchElementException ne = new NoSuchElementException(nsfe.getMessage());
        ne.initCause(nsfe);
        throw ne;
      }
      field.setAccessible(true);
      return field;
    }
    catch (SecurityException e)
    {
      throw new RuntimeException(oClass.getName() + ": " + e.getMessage(), e);
    }
  }

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

  /**
   * Uses this{@link #getParameterFields()} to assign values from an array to the
   * specified Java fields
   * 
   * @param array
   *          of values ordered according to this{@link #getBoundedParameters()}
   */
  public void
         assignParameters(double[] point)
  {
    if (verbose)
    {
      out.println("assigning parameters " + Arrays.toString(point));
    }
    BoundedParameter[] params = getBoundedParameters();
    Field[] fields = getParameterFields();
    assert fields.length == params.length;
    assert point.length == params.length;

    for (int i = 0; i < fields.length; i++)
    {
      try
      {
        fields[i].setDouble(this, point[params[i].getOrdinal()]);
      }
      catch (IllegalArgumentException | IllegalAccessException e)
      {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    if (verbose)
    {
      out.println("assigned " + getParameters());
    }
  }

  public Vector
         getParameters()
  {
    return new Vector(stream(getParameterFields()).mapToDouble(field -> {
      try
      {
        return field.getDouble(this);
      }
      catch (Exception e)
      {
        throw new RuntimeException(e.getMessage(), e);
      }
    }));
  }

  private final ObjectiveFunctionSupplier objectiveFunctionSupplier = () -> new ObjectiveFunction(copy());
  private double[] x;

  public final ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts,
                            double x[],
                            IntConsumer progressNotifier)
  {
    int maxIters = Integer.MAX_VALUE;

    MaxEval maxEval = new MaxEval(maxIters);
    SimpleBounds simpleBounds = getParameterBounds();

    SolutionValidator validator = point -> {
      return true;
    };

    Supplier<MultivariateOptimizer> optimizerSupplier = () -> new ExtendedBOBYQAOptimizer(getParamCount() * 2 + 1, 10, 1E-4);

    ParallelMultistartMultivariateOptimizer multiopt = new ParallelMultistartMultivariateOptimizer(optimizerSupplier,
                                                                                                   numStarts,
                                                                                                   getRandomVectorGenerator(simpleBounds));

    PointValuePairComparator momentMatchingAutocorrelationComparator = (a,
                                                                        b) -> {
      NormalInverseGaussianProcess processA = newProcess(a.getPoint());
      NormalInverseGaussianProcess processB = newProcess(b.getPoint());
      double mma = processA.logLik(x);
      double mmb = processB.logLik(x);
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

    printResults(multiopt, x);

    return multiopt;
  }

  public NormalInverseGaussianProcess
         copy()
  {
    try
    {
      return (NormalInverseGaussianProcess) clone();
    }
    catch (CloneNotSupportedException e)
    {

      throw new RuntimeException(e.getMessage(), e);

    }
  }

  public TextTable
         printResults(ParallelMultistartMultivariateOptimizer multiopt,
                      double[] x)
  {

    BoundedParameter[] params = getBoundedParameters();

    println("parameter estimates for " + toString());

    PointValuePair[] optima = multiopt.getOptima().toArray(new PointValuePair[0]);

    return printResults(optima, x);
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

  public Object[][]
         evaluateStatisticsForEachLocalOptima(PointValuePair[] optima,
                                              String[] columnHeaders,
                                              double[] x)
  {
    Object[][] data = new Object[optima.length][columnHeaders.length];

    for (int i = 0; i < optima.length; i++)
    {
      Object[] row = evaluateParameterStatistics(optima[i].getPoint(), x);

      for (int j = 0; j < columnHeaders.length; j++)
      {
        data[i][j] = row[j];
      }
    }
    return data;
  }

  public double

         getFieldValue(Field param)
  {
    try
    {
      return param.getDouble(this);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * @return an array whose elements correspond to this{@link #statisticNames}
   */
  public Object[]
         evaluateParameterStatistics(double[] point,
                                     double x[])
  {
    NormalInverseGaussianProcess process = newProcess(point);

    // out.println(compensated.autocor(30));

    Object[] statisticsVector = new Object[]
    { process.logLik(x), 1, 2, 3 };

    return addAll(stream(getParameterFields()).map(param -> process.getFieldValue(param)).toArray(), statisticsVector);
  }

  public static String[] statisticNames =
  { "Log-Lik", "E[innov]", "std[innov]" };

  public TextTable
         printResults(PointValuePair[] optima,
                      double[] x)
  {
    String[] columnHeaders = getColumnHeaders();

    Object[][] data = evaluateStatisticsForEachLocalOptima(optima, columnHeaders, x);

    TextTable tt = new TextTable(columnHeaders, data);

    tt.setAddRowNumbering(true);
    tt.printTable();

    return tt;
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

        return point;
      }
      catch (Exception e)
      {
        e.printStackTrace(System.err);
        return null;
      }
    };
  }

  public int
         getParamCount()
  {
    return getBoundedParameters().length;
  }

  public double
         logLik(double[] x)
  {
    final int n = x.length;
    return -n * Math.log(Math.PI) + n * Math.log(alpha)
           + n * (gamma * delta - beta * mu)
           - n / 0.2e1
           - n * mu * mu * Math.pow(gamma, -0.2e1) / 0.2e1
           + sum(i -> Math.pow(gamma, -0.2e1) * x[i - 1] * mu - Math.pow(gamma, -0.2e1) * Math.pow(x[i - 1], 0.2e1) / 0.2e1
                      + beta * x[i - 1]
                      + Bessel.k1(delta * alpha * Math.sqrt(0.1e1 + Math.pow(x[i - 1] - mu, 0.2e1) * Math.pow(gamma, -0.2e1))),
                 0,
                 n);
  }

  private NormalInverseGaussianProcess
          newProcess(double[] point)
  {
    return new NormalInverseGaussianProcess(point[0], point[1], point[2], alpha, alpha, streamBrownian, igProcess);
  }

  @Override
  public double
         value(double[] point)
  {
    return logLik(x);
  }

}
