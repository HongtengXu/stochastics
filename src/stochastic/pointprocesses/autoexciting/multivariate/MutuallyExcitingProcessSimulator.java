package stochastic.pointprocesses.autoexciting.multivariate;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.stream.IntStream.rangeClosed;
import static org.fusesource.jansi.Ansi.ansi;
import static util.Console.println;

import java.io.File;
import java.io.IOException;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.random.JDKRandomGenerator;

import dnl.utils.text.table.TextTable;
import fastmath.DoubleColMatrix;
import fastmath.IntVector;
import fastmath.Vector;
import fastmath.matfile.MatFile;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import junit.framework.TestCase;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.DiagonalExtendedApproximatePowerlawMututallyExcitingProcess;
import util.TerseThreadFactory;

public class MutuallyExcitingProcessSimulator
{

  static
  {
    System.setProperty("java.util.concurrent.ForkJoinPool.common.threadFactory", TerseThreadFactory.class.getName());
  }

  private AbstractMutuallyExcitingProcess process;

  public MutuallyExcitingProcessSimulator(MutuallyExcitingProcess process)
  {
    this.process = (AbstractMutuallyExcitingProcess) process;
  }

  public static void
         main(String[] args) throws IOException,
                             CloneNotSupportedException,
                             InterruptedException
  {
    // int threadCount = Runtime.getRuntime().availableProcessors();
    int threadCount = 1;

    int seed = args.length > 0 ? Integer.valueOf(args[0]) : 0;
    Vector hello = new Vector(threadCount);
    rangeClosed(0, threadCount - 1).parallel().forEach(thread -> {
      DiagonalExtendedApproximatePowerlawMututallyExcitingProcess process =
                                                                          FullyCovariantExtendedMututallyExcitingExponentialPowerlawApproximationProcessTest.constructLongerProcess();
      // process.T = ;
      process.τ.assign(1);
      process.ε.assign(0);
      process.η.assign(2.8483343724);
      process.b.assign(3.4935467810);

      // process.ε = 0.05;0
      process.T = new Vector(new double[] {});
      process.K = new IntVector(process.T.size());
      // process.trace = true;
      hello.set(thread, simulateProcess(process, seed + thread).diff().mean());
    });
    out.println("mean times: " + hello);
    out.println("average mean time: " + hello.mean());

  }

  public static Vector
         simulateProcess(DiagonalExtendedApproximatePowerlawMututallyExcitingProcess process,
                         int seed)
  {
    int lastRejectedPoint = -1;
    int rejects = 0;
    ExponentialDistribution expDist = new ExponentialDistribution(new JDKRandomGenerator(seed), 1);
    out.println("simulating " + ansi().fgBrightYellow()
                + process
                + ansi().fgDefault()
                + " from "
                + process.T.size()
                + " points with seed="
                + seed
                + " meanRecurrenceTimes="
                + process.meanRecurrenceTimeVector());
    int n = process.T.size();
    double nextTime = 0;
    int sampleCount = 130000;
    double startTime = currentTimeMillis();
    process.setAsize(sampleCount);
    for (int i = 0; i < sampleCount; i++)
    {
      for (int m = 0; m < process.dim(); i++)
      {
        double y = expDist.sample();
        // process.trace = false;
        // TODO: average over Λ and compare against the invariant projection
        double dt = process.invΛ(m, y);
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
        // process.trace = false;
        // Real dtReal = process.invΛReal(y);
        // if ( dtReal.fpValue() > 6669)
        // {
        // out.println( "clamping " + dtReal );
        // dtReal = new Real(dt);
        // }
        // process.trace = false;

        // double dtRealFpValue = dtReal.fpValue();
        out.println("dt=" + dt + " for y=" + y);
        double q = process.Λ(m, n - 1, dt);
        nextTime = (!process.T.isEmpty() ? process.T.getRightmostValue() : 0) + dt;
        // double marginalΛ = process.invΛ(m, 0.46);
        // out.println("marginalΛ=" + marginalΛ);

        TestCase.assertEquals("y != q", y, q, 1E-7);
        n++;
        process.appendTime(m, nextTime);
        double Edt = nextTime / n;
        // out.println("T=" + process.T.toIntVector());
        // out.println("Λ=" + process.Λ().slice(max(0, process.T.size() - 10),
        // process.T.size() - 1));
        if (i % 1000 == 1)
        {
          String msg = "seed=" + seed + " m=" + m + " i=" + i + " y=" + y + " = q = " + q + " dt=" + dt
          // + " marginal="
          // + marginalΛ
                       + " Λmean="
                       + process.Λ(m).mean()
                       + " Λvar="
                       + process.Λ(m).variance()
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
    }
    double duration = startTime - currentTimeMillis();

    String fn = "simulated." + seed + ".mat";
    MatFile.write(fn, process.T.setName("T").createMiMatrix());
    out.println("write " + fn);
    double seconds = duration / 1000;
    double pointsPerSecond = sampleCount / seconds;
    out.println("simulation rate: " + pointsPerSecond + " points/second");
    // process.printA();

    return process.T;
  }

  public static void
         storeParameterEstimationResults(File testFile,
                                         Vector data,
                                         AbstractMutuallyExcitingProcess process)
  {

    try
    {
      MatFile outFile = new MatFile(testFile, MutuallyExcitingProcessSimulator.class.getSimpleName());
      outFile.write(data.createMiMatrix());
      out.println("writing timestamp data, compensators and intensities to " + testFile.getAbsolutePath());
      for (int m = 0; m < process.dim(); m++)
      {
        Vector compensator = process.Λ(m).setName("comp" + m);
        Vector intensity = process.λvector(m).setName("intensity" + m);
        outFile.write(compensator.createMiMatrix());
        outFile.write(intensity.createMiMatrix());
      }
      outFile.close();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private boolean verbose = true;

  public TextTable
         printResults(ParallelMultistartMultivariateOptimizer multiopt)
  {

    println("estimated parameters for " + process);

    PointValuePair[] optima = multiopt.getOptima().toArray(new PointValuePair[0]);

    String[] columnHeaders = process.getColumnHeaders();

    Object[][] data = evaluateStatisticsForEachLocalOptima(optima, columnHeaders);

    TextTable tt = new TextTable(columnHeaders, data);

    tt.setAddRowNumbering(true);
    tt.printTable();

    return tt;
  }

  public Object[][]
         evaluateStatisticsForEachLocalOptima(PointValuePair[] optima,
                                              String[] columnHeaders)
  {
    Object[][] data = new Object[optima.length][columnHeaders.length];

    for (int i = 0; i < optima.length; i++)
    {
      Object[] row = process.evaluateParameterStatistics(optima[i].getPoint());

      for (int j = 0; j < columnHeaders.length; j++)
      {
        data[i][j] = row[j];
      }
    }
    return data;
  }

  public static Vector
         loadTimes(String filename,
                   String symbol) throws IOException
  {

    DoubleColMatrix matrix = MatFile.loadMatrix(filename, symbol);
    Vector data = matrix.col(0).setName("data");

    return data;
  }

}
