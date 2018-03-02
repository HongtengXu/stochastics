package stochastic.processes;

import static fastmath.Functions.sum;
import static fastmath.Functions.uniformRandom;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
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

public class NormalInverseGaussianProcess implements MultivariateFunction
{

  @Override
  public String
         toString()
  {
    return String.format("NormalInverseGaussianProcess[α=%s, β=%s, μ=%s, δ=%s]", α, β, μ, δ);
  }

  @Override
  protected Object
            clone() throws CloneNotSupportedException
  {
    NormalInverseGaussianProcess copy = new NormalInverseGaussianProcess();
    copy.α = α;
    copy.β = β;
    copy.μ = μ;
    copy.δ = δ;
    copy.x = x;
    return copy;
  }

  public NormalInverseGaussianProcess(double α, double β, double γ, double μ, double δ)
  {
    super();
    this.α = α;
    this.β = β;
    this.μ = μ;
    this.δ = δ;
  }

  /**
   * smaller values of α implies heavy tails and larger values imply lighter tails
   */
  public double α;

  /**
   * 
   */
  public double β;

  public double μ;

  public double δ;

  protected static enum Parameter implements BoundedParameter
  {
    α(0, 0.2), β(0, 0.2), μ(0, 10), δ(0, 70);

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

  public double
         γ()
  {
    return sqrt(pow(α, 2) - pow(β, 2));
  }

  public double
         logLik(double[] x)
  {
    this.x = x;
    final int n = x.length;
    return sum(i -> {
      double y = x[i - 1];
      double c = Math.exp(δ * Math.sqrt(α * α - β * β) - β * μ + β * y);
      double fuck = α * Math.sqrt(δ * δ + μ * μ - 0.2e1 * y * μ + y * y);
      double b = c * Bessel.k1(fuck) * Math.pow(δ * δ + μ * μ - 0.2e1 * y * μ + y * y, -0.1e1 / 0.2e1) * α * δ;
      double a = Math.log(b);
      try
      {

      }
      catch (ArithmeticException ae)
      {
        out.println("fuck=" + fuck);
      }

      if (Double.isNaN(a))
      {
        return a;
      }
      return a;

    }, 1, n);
  }

  private boolean verbose;
  private Field[] parameterFields;

  public NormalInverseGaussianProcess(double point, double point2, double point3, double point4)
  {
    assignParameters(new double[]
    { point, point2, point3, point4 });
  }

  public NormalInverseGaussianProcess()
  {
  }

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
    this.x = x;
    int maxIters = Integer.MAX_VALUE;

    MaxEval maxEval = new MaxEval(maxIters);
    SimpleBounds simpleBounds = getParameterBounds();

    SolutionValidator validator = point -> {
      NormalInverseGaussianProcess process = newProcess(point.getPoint());
      process.x = x;
      double ll = process.logLik();
      // out.println("validating " + process + " with LL score " + ll);
      return Double.isFinite(ll);
    };

    Supplier<MultivariateOptimizer> optimizerSupplier = () -> new ExtendedBOBYQAOptimizer(getParamCount() * 2 + 1, 100, 1E-14);

    ParallelMultistartMultivariateOptimizer μltiopt = new ParallelMultistartMultivariateOptimizer(optimizerSupplier,
                                                                                                  numStarts,
                                                                                                  getRandomVectorGenerator(simpleBounds));

    PointValuePairComparator logLikComparator = (a,
                                                 b) -> {
      NormalInverseGaussianProcess processA = newProcess(a.getPoint());
      NormalInverseGaussianProcess processB = newProcess(b.getPoint());
      double mma = processA.logLik(x);
      double mmb = processB.logLik(x);
      return Double.compare(mma, mmb);
    };

    double startTime = currentTimeMillis();
    PointValuePair optiμm =
                          μltiopt.optimize(progressNotifier, GoalType.MAXIMIZE, logLikComparator, validator, maxEval, objectiveFunctionSupplier, simpleBounds);
    double stopTime = currentTimeMillis();
    double secondsElapsed = (stopTime - startTime) / 1000;
    double evaluationsPerSecond = μltiopt.getEvaluations() / secondsElapsed;
    double minutesElapsed = secondsElapsed / 60;

    assignParameters(optiμm.getKey());

    out.format("estimation completed in %f minutes at %f evals/sec\n", minutesElapsed, evaluationsPerSecond);

    // plot("λ(t)", this::λ, T.fmin(), T.fmax(), 5000 );

    printResults(μltiopt, x);

    return μltiopt;
  }

  public double
         logLik()
  {
    return logLik(x);
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
         printResults(ParallelMultistartMultivariateOptimizer μltiopt,
                      double[] x)
  {

    BoundedParameter[] params = getBoundedParameters();

    println("parameter estimates for " + toString());

    PointValuePair[] optima = μltiopt.getOptima().toArray(new PointValuePair[0]);

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
    { process.logLik(x), process.mean(), process.stdev(), 3 };

    return addAll(stream(getParameterFields()).map(param -> process.getFieldValue(param)).toArray(), statisticsVector);
  }

  public double
         stdev()
  {
    return sqrt(var());
  }

  public double
         var()
  {
    return δ * pow(α, 2) / pow(γ(), 3);
  }

  public double
         mean()
  {
    return μ + δ * β / γ();
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
        point[Parameter.α.ordinal()] = point[Parameter.α.ordinal()] + point[Parameter.β.ordinal()];
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

  private NormalInverseGaussianProcess
          newProcess(double[] point)
  {
    NormalInverseGaussianProcess newProcess = new NormalInverseGaussianProcess(point[0], point[1], point[2], point[3]);
    newProcess.x = x;
    return newProcess;
  }

  @Override
  public double
         value(double[] point)
  {
    NormalInverseGaussianProcess process = newProcess(point);
    return process.logLik(x);
  }

}
