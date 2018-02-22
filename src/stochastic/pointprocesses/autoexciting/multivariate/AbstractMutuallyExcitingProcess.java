package stochastic.pointprocesses.autoexciting.multivariate;

import static fastmath.Functions.seq;
import static java.lang.Math.sqrt;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.stream.IntStream.range;
import static util.Console.println;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.function.IntConsumer;
import java.util.function.IntToDoubleFunction;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.SimpleBounds;

import dnl.utils.text.table.TextTable;
import fastmath.DoubleMatrix;
import fastmath.IntVector;
import fastmath.Pair;
import fastmath.Vector;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import stochastic.pointprocesses.selfexciting.BoundedParameter;
import stochastic.pointprocesses.selfexciting.Type;

public abstract class AbstractMutuallyExcitingProcess implements MultivariateFunction
{

  protected static int llcnt = 0;

  public Vector T;

  /**
   * integer-array indicating which dimension to which each point in
   * this{@link #T} corresponds
   */
  public IntVector K;

  public boolean verbose = false;

  /*
   * The first column of this matrix is identical with T, the remaining columns,
   * if any, are the marks associated with the timestamps in column 0
   */
  public DoubleMatrix X;

  @Override
  public Object
         clone()
  {
    try
    {
      AbstractMutuallyExcitingProcess spawn = getClass().getDeclaredConstructor().newInstance();
      spawn.assignParameters(getParameters().toDoubleArray());
      spawn.T = T;
      spawn.K = K;
      spawn.X = X;
      spawn.llcnt = llcnt;
      return spawn;
    }
    catch (Exception e)
    {
      if (e instanceof RuntimeException)
      {
        throw (RuntimeException) e;
      }
      throw new RuntimeException(e.getMessage(), e);
    }

  }

  @SuppressWarnings("unchecked")
  public <E extends AbstractMutuallyExcitingProcess>
         E
         copy()
  {
    return (E) clone();
  }

  protected Field[] parameterFields = null;

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

  public AbstractMutuallyExcitingProcess()
  {
    super();
  }

  public abstract Vector
         getParameters();

  public abstract Object[]
         evaluateParameterStatistics(double[] point);

  public abstract double
         getΛmomentMeasure();

  public abstract double
         getΛmomentLjungBoxMeasure();

  public abstract double
         getLjungBoxMeasure();

  public abstract double
         getΛKolmogorovSmirnovStatistic();

  public abstract AbstractMutuallyExcitingProcess
         newProcess(double[] point);

  public abstract Field[]
         getParameterFields();

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

  public abstract BoundedParameter[]
         getBoundedParameters();

  public int
         getParamCount()
  {
    return getBoundedParameters().length;
  }

  public abstract double
         meanRecurrenceTime(int m);

  public abstract int
         dim();

  public Vector
         meanRecurrenceTimeVector()
  {
    return new Vector(seq(this::meanRecurrenceTime, 0, dim() - 1));
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

  public abstract double
         logLik();

  public abstract ParallelMultistartMultivariateOptimizer
         estimateParameters(int numStarts,
                            IntConsumer progressNotifier,
                            String filename,
                            int section);

  public abstract String[]
         getColumnHeaders();

  public abstract void
         loadParameters(File modelFile) throws IOException;

  public abstract void
         storeParameters(File modelFile) throws IOException;

  /**
   * kernel function
   * 
   * 
   * @return
   */
  public abstract double
         f(int m,
           int n,
           double t);

  /**
   * integrated kernel function, regarded as a cumulative distribution function
   * when this{@link #ρ()}=1, it gives the probability that an event happens
   * before time t
   * 
   * @param type
   *          TODO
   * @param t
   * 
   * @return ∫ν(s)ds(0,t) integral of this{@link #f(int, double)} over s=0..t
   */
  public abstract double
         F(int m,
           int n,
           double t);

  /**
   * normalization factor which ensures the integral of
   * this{@link #f(int, double)} over [0,∞] is equal to this#ρ()
   * 
   * @param n
   *          TODO
   * 
   * @return
   */
  public abstract double
         Z(int m,
           int n);

  /**
   * @see this{@link #getFieldValue(Field)} and this{@link #getField(String)}
   * @param paramName
   * @return
   */
  public double
         getFieldValue(String paramName)
  {
    return getFieldValue(getField(paramName));
  }

  public BoundedParameter
         getBoundedParameter(String name)
  {
    for (BoundedParameter param : getBoundedParameters())
    {
      if (param.getName().equals(name))
      {
        return param;
      }
    }
    return null;
  }

  /**
   * Calls {@link Field#setDouble(Object, double)} on
   * this{@link #getField(String)}
   * 
   * @param paramName
   * @return
   */
  public void
         setFieldValue(String paramName,
                       double value)
  {
    try
    {
      getField(paramName).setDouble(this, value);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Object[][]
         evaluateStatisticsForEachLocalOptima(PointValuePair[] optima,
                                              String[] columnHeaders)
  {
    Object[][] data = new Object[optima.length][columnHeaders.length];

    for (int i = 0; i < optima.length; i++)
    {
      double[] point = optima[i].getPoint();
      Object[] row = evaluateParameterStatistics(point);

      for (int j = 0; j < columnHeaders.length; j++)
      {
        data[i][j] = row[j];
      }
    }
    return data;
  }

  protected final int forecastStartIndex = 6;

  protected Entry<Double, Integer>[][][] lowerEntries;

  protected Entry<Double, Integer>[][][] upperEntries;

  protected Pair<Vector[], TreeMap<Double, Integer>[]> cachedSubTimes;

  public Vector[] dT;

  /**
   * @see this{@link #forecastStartIndex}
   * 
   * @return
   */
  public Vector
         getInnovationSequence(int m)
  {
    int n = N(m) - 2;
    return new Vector(seq((IntToDoubleFunction) tk -> innov(m, tk), 0, n)).slice(forecastStartIndex, n).setName("innov" + m);
  }

  public double
         innov(int m,
               int tk)
  {
    return invΛ(m, tk, 1) - (T(m, tk + 1) - T(m, tk));
  }

  public abstract double
         invΛ(int m,
              int tk,
              double y);

  public final double
         getMeanPredictionError(int m)
  {
    return getInnovationSequence(m).mean();
  }

  /**
   * 
   * @return {@link Math#sqrt(double)}(this{@link #getMeanSquaredPredictionError()}
   */
  public double
         getRootMeanSquaredPredictionError(int m)
  {
    return sqrt(getMeanSquaredPredictionError(m));
  }

  public double
         getMeanSquaredPredictionError(int m)
  {
    return getInnovationSequence(m).pow(2).mean();
  }

  public TextTable
         printResults(ParallelMultistartMultivariateOptimizer multiopt)
  {

    BoundedParameter[] params = getBoundedParameters();

    println("parameter estimates for " + toString());

    PointValuePair[] optima = multiopt.getOptima().toArray(new PointValuePair[0]);

    return printResults(optima);
  }

  public TextTable
         printResults(PointValuePair[] optima)
  {
    String[] columnHeaders = getColumnHeaders();

    Object[][] data = evaluateStatisticsForEachLocalOptima(optima, columnHeaders);

    TextTable tt = new TextTable(columnHeaders, data);

    tt.setAddRowNumbering(true);
    tt.printTable();

    return tt;
  }

  public abstract Vector
         λvector(int type);

  public abstract double
         getBranchingRatio();

  public abstract Type
         getType();

  public abstract double
         φδ(int m,
            double t,
            double y,
            int tk);

  public abstract double
         φδ(int m,
            double t,
            double y);

  public abstract Vector
         Λ(int m);

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

  /**
   * Given two Vectors (of times and types), calculate indices and partition
   * subsets of different types
   *
   * 
   * @return Pair<Vector times[dim],Map<time,type>[dim]>
   */
  @SuppressWarnings("unchecked")
  public final Pair<Vector[], TreeMap<Double, Integer>[]>
         getTimeSubsets()
  {
    assert T.size() == K.size();
    if (cachedSubTimes != null)
    {
      return cachedSubTimes;
    }
    final ArrayList<Double>[] timesSub = new ArrayList[dim()];
    final Vector[] timeVectors = new Vector[dim()];
    TreeMap<Double, Integer>[] timeIndices = new TreeMap[dim()];

    for (int i = 0; i < dim(); i++)
    {
      timesSub[i] = new ArrayList<Double>();
      timeIndices[i] = new TreeMap<Double, Integer>();
    }
    for (int i = 0; i < T.size(); i++)
    {
      int k = K.get(i);
      assert k >= 0;
      assert k < dim() : format("k=%d dim=%d", k, dim());
      timesSub[k].add(T.get(i));
    }
    for (int i = 0; i < dim(); i++)
    {
      ArrayList<Double> subTimes = timesSub[i];
      TreeMap<Double, Integer> subTimeIndices = timeIndices[i];
      for (int j = 0; j < subTimes.size(); j++)
      {
        subTimeIndices.put(subTimes.get(j), j);
      }
      timeVectors[i] = new Vector(timesSub[i]).setName("T" + i);
    }
    cachedSubTimes = new Pair<Vector[], TreeMap<Double, Integer>[]>(timeVectors, timeIndices);

    return cachedSubTimes;
  }

  /**
   * 
   * @param m
   * @return number of time points in the m-th dimension
   */
  public final int
         N(int m)
  {
    return getTimes(m).size();
  }

  @SuppressWarnings("unchecked")
  protected final Entry<Double, Integer>
            getLowerEntry(TreeMap<Double, Integer>[] subTimeIndex,
                          final double lowerTime,
                          int m,
                          int n,
                          int i)
  {
    Entry<Double, Integer> lowerEntry = lowerEntries == null ? null : lowerEntries[m][n][i];
    if (lowerEntry == null)
    {
      lowerEntry = subTimeIndex[n].ceilingEntry(lowerTime);
      if (lowerEntries == null)
      {
        lowerEntries = new Entry[dim()][dim()][T.size()];
      }
      lowerEntries[m][n][i] = lowerEntry;
    }
    return lowerEntry;
  }

  public final Vector
         getTimes(int type)
  {
    return getTimeSubsets().left[type];
  }

  @SuppressWarnings("unchecked")
  protected final Entry<Double, Integer>
            getUpperEntry(TreeMap<Double, Integer>[] subTimeIndex,
                          final double upperTime,
                          int m,
                          int n,
                          int i)
  {
    Entry<Double, Integer> upperEntry = upperEntries == null ? null : upperEntries[m][n][i];
    if (upperEntry == null)
    {
      upperEntry = subTimeIndex[n].ceilingEntry(upperTime);
      if (upperEntries == null)
      {
        upperEntries = new Entry[dim()][dim()][T.size()];
      }
      upperEntries[m][n][i] = upperEntry;
    }
    return upperEntry;
  }

}
