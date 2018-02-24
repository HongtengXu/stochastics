package stochastic.pointprocesses.selfexciting;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static org.fusesource.jansi.Ansi.ansi;
import static util.Console.println;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.fusesource.jansi.Ansi.Color;

import fastmath.DoubleColMatrix;
import fastmath.IntVector;
import fastmath.Vector;
import fastmath.matfile.MatFile;
import fastmath.optim.ParallelMultistartMultivariateOptimizer;
import stochastic.annotations.Units;
import stochastic.pointprocesses.autoexciting.multivariate.AbstractMutuallyExcitingProcess;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.DiagonalExtendedApproximatePowerlawMututallyExcitingProcess;
import stochastic.pointprocesses.finance.TradingProcess;
import stochastic.pointprocesses.finance.TradingStrategy;
import umontreal.iro.lecuyer.probdist.DistributionFactory;
import umontreal.iro.lecuyer.probdist.NormalInverseGaussianDist;
import util.TerseThreadFactory;

public class ProcessEstimator
{
  @Units(time = TimeUnit.HOURS)
  public static final double W = 0.5; // half hour

  static
  {
    System.setProperty("java.util.concurrent.ForkJoinPool.common.threadFactory", TerseThreadFactory.class.getName());
  }

  private AbstractSelfExcitingProcess process;

  private int trajectoryCount = Runtime.getRuntime().availableProcessors();

  public ProcessEstimator(SelfExcitingProcess process)
  {
   this.process = (AbstractSelfExcitingProcess) process;
  }

  public static void
         main(String[] args) throws IOException,
                             CloneNotSupportedException
  {

    Type type = Type.ExtendedApproximatePowerlaw;
    String filename = args[0];
    int cpuMultiplier = 2;

    int trajectoryCount = Runtime.getRuntime().availableProcessors() * cpuMultiplier;
    String symbol = args[1];

    out.println("Estimating parameters for " + filename);
    ArrayList<AbstractSelfExcitingProcess> processes = estimateSelfExcitingProcess(type, filename, trajectoryCount, symbol);

    // TODO: use multivar estimator and compare results

  }

  /**
   * estimate the parameters of a Hawkes process model
   * 
   * @param type
   * @param filename
   * @param symbol
   * @return
   * @throws IOException
   */
  public static ArrayList<AbstractSelfExcitingProcess>
         estimateSelfExcitingProcess(Type type,
                                     String filename,
                                     String symbol) throws IOException

  {
    return estimateSelfExcitingProcess(type, filename, Runtime.getRuntime().availableProcessors(), symbol);
  }

  public static ArrayList<AbstractSelfExcitingProcess>
         estimateSelfExcitingProcess(Type type,
                                     String filename,
                                     int trajectoryCount,
                                     String symbol) throws IOException
  {
    Vector data = loadTimes(filename, symbol);

    return estimateSelfExcitingProcesses(type, trajectoryCount, data, filename);
  }

  /**
   * Return an array of calibrated self-exciting processes by splitting the
   * trading session up into windows of length this{@link #W}
   * 
   * @param type
   *          {@link Type} of self-exciting process to use
   * @param trajectoryCount
   *          number of random starts for the multistart optimizer to use to
   *          determine optimal parameters
   * @param times
   * @param sourcefilename
   *          TODO
   * 
   * @return
   * @throws IOException
   */
  public static ArrayList<AbstractSelfExcitingProcess>
         estimateSelfExcitingProcesses(Type type,
                                       int trajectoryCount,
                                       Vector times,
                                       String sourcefilename) throws IOException
  {

    double Edt = times.diff().mean();

    out.println("E[dt]=" + Edt);

    ArrayList<AbstractSelfExcitingProcess> processes = new ArrayList<>();
    int n = (int) (TradingProcess.tradingDuration / W);
    int indexes[] = TradingStrategy.getIndices(times);

    range(0, n).forEachOrdered(section -> {
      Vector slice = times.slice(section == 0 ? 0 : indexes[section - 1], indexes[section]);
      double sliceEdt = slice.diff().mean();

      out.println("E_" + section + "[dt]=" + sliceEdt);

      AbstractSelfExcitingProcess process = estimateSelfExcitingProcess(type, trajectoryCount, slice, sourcefilename, section);
      processes.add(process);

      File testFile = new File("test" + section + ".mat");
      File modelFile = new File(testFile.getAbsolutePath() + "." + type.getFilenameExtension() + ".model");

      storeParameterEstimationResults(testFile, slice, process, modelFile);

    });

    return processes;
  }

  public static AbstractSelfExcitingProcess
         estimateSelfExcitingProcess(Type type,
                                     int trajectoryCount,
                                     Vector slice,
                                     String filename,
                                     int section)
  {
    AbstractSelfExcitingProcess process = SelfExcitingProcessFactory.spawnNewProcess(type);
    ProcessEstimator estimator = new ProcessEstimator(process);
    estimator.setTrajectoryCount(trajectoryCount);
    estimator.estimate(slice);

//    AbstractMutuallyExcitingProcess multivarProcess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
//    multivarProcess.T = slice;
//    multivarProcess.K = new IntVector(slice.size());
//    out.println("estimating multivar ");
//    ParallelMultistartMultivariateOptimizer opt = multivarProcess.estimateParameters(10, ev -> {
//    }, filename, section);
//    multivarProcess.printResults(opt);
//    out.println("estimated " + multivarProcess);

    return process;
  }

  public static void
         storeParameterEstimationResults(File testFile,
                                         Vector data,
                                         AbstractSelfExcitingProcess process,
                                         File modelFile)
  {
    Vector compensator = process.Λ().setName("comp");
    Vector intensity = process.λvector().setName("intensity");
    out.println("writing timestamp data, compensator, intensity, and innovation to " + testFile.getAbsolutePath() + " and parameters to " + modelFile);
    Vector innovation = process.getInnovationSequence().setName("innov");
    
    NormalInverseGaussianDist nigDist = DistributionFactory.getDistributionMLE(NormalInverseGaussianDist.class, innovation.toDoubleArray(), innovation.size() );
    
    try
    {
      process.storeParameters(modelFile);
      MatFile.write(testFile, data.createMiMatrix(), compensator.createMiMatrix(), intensity.createMiMatrix(), innovation.createMiMatrix());
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void
         setTrajectoryCount(int trajectoryCount)
  {
    this.trajectoryCount = trajectoryCount;
  }

  private boolean verbose = true;

  /*
   * 
   * @return number of trajectories do generate during search for optimal
   * parameters
   * 
   */
  public int
         getTrajectoryCount()
  {
    return trajectoryCount;
  }

  public AbstractSelfExcitingProcess
         estimate(Vector data)
  {
    if (verbose)
    {
      println(ansi().fgRed()
                    .bgBright(Color.WHITE)
                    .a("spawning ")
                    .fgBrightMagenta()
                    .a(getTrajectoryCount())
                    .fgGreen()
                    .a(" " + process.getClass().getSimpleName())
                    .fgRed()
                    .bgBright(Color.WHITE)
                    .a("es to estimate the model parameters ")
                    .fgBrightBlue()
                    .a("[" + asList(process.getParameterFields()).stream().map(field -> field.getName()).collect(joining(",")))
                    .fgRed()
                    .bgBright(Color.WHITE)
                    .a("] most likely to have generated the observed sequence of ")
                    .fgBrightMagenta()
                    .a(data.size())
                    .fgRed()
                    .bgBright(Color.WHITE)
                    .a(" timestamps")
                    .reset());
    }

    process.T = data;
    ParallelMultistartMultivariateOptimizer optimizer = process.estimateParameters(getTrajectoryCount(), null);

    // double averageError = process.getInnovationSequence().mean();
    // out.println("E(I)=" + process.getMeanPredictionError()
    //
    // + " sqrt(E(I^2))="
    // + process.getRootMeanSquaredPredictionError());
    return process;

  }

  public static Vector
         loadTimes(String filename,
                   String symbol) throws IOException
  {

    DoubleColMatrix matrix = MatFile.loadMatrix(filename, symbol);
    Vector times = matrix.col(0).copy().setName("times");

    return times;
  }

}
