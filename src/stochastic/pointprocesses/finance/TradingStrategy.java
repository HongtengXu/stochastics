package stochastic.pointprocesses.finance;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import fastmath.DoubleMatrix;
import fastmath.Vector;
import fastmath.Vector.Condition;
import fastmath.matfile.MatFile;
import stochastic.pointprocesses.selfexciting.AbstractSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.ProcessEstimator;
import stochastic.pointprocesses.selfexciting.Type;
import stochastic.pointprocesses.ui.CalibratedTradingStrategyViewer;
import util.DateUtils;

public class TradingStrategy
{
  public static void
         main(String args[]) throws FileNotFoundException,
                             IOException
  {
    final String matFile = args.length > 0 ? args[0] : "/home/stephen/fm/SPY.mat";
    final String symbol = args.length > 1 ? args[1] : "SPY";

    TradingFiltration data = new TradingFiltration(MatFile.loadMatrix(matFile, symbol));

    ArrayList<AbstractSelfExcitingProcess> processes = getCalibratedProcesses(matFile, data, Type.ExtendedApproximatePowerlaw);

    CalibratedTradingStrategyViewer.launchModelViewer(processes).frame.setTitle(CalibratedTradingStrategyViewer.class.getSimpleName() + ": " + matFile);

    // TODO: 1. finish implementing the multivariate exponential self-exciting
    // process code
    // that was written before and adapt the exponential approximations of powerlaws
    // so
    // they work in a multivariate manner. In this case, bivariate process of
    // buys/sells
    // were the superposition of the two inferred processes follows a known process.
    // 2. Price direction expectation could be incorporated by introducing an
    // asymmetry in the trigger for long/short position triggering. 3. determine
    // some
    // threshold to trigger trades and see if its profitable

  }

  /**
   * 
   * @param matFile
   * @param filtration
   * 
   * @return an {@link ArrayList}
   * @throws IOException 
   */
  public static ArrayList<AbstractSelfExcitingProcess>
         getCalibratedProcesses(final String matFile,
                                TradingFiltration filtration, Type type ) throws IOException
  {
    int n = filtration.tradeIndexes.length;
    ArrayList<AbstractSelfExcitingProcess> processes = new ArrayList<>();
    for (int i = 0; i < n; i++)
    {
      DoubleMatrix markedPointSlice = filtration.markedPoints.sliceRows(i == 0 ? 0 : filtration.tradeIndexes[i - 1],
                                                                               filtration.tradeIndexes[i]);
      Vector timeSlice = markedPointSlice.col(0);

      AbstractSelfExcitingProcess process = type.instantiate();
      
      process.X = markedPointSlice;
      process.T = timeSlice;
      process.loadParameters(new File(matFile + "." + process.getType().getFilenameExtension() + "." + i + ".model"));
      processes.add(process);

    }
    return processes;
  }

  /**
   * 
   * @param times
   *          a {@link Vector} of timestamps covering the span
   *          {@link TradingProcess#openTime} to
   *          {@link TradingProcess#closeTime}
   * @return an array of offsets indicating the timestamp indexes which divides
   *         the data into n windows of length
   *         {@link ProcessEstimator#W} hours over the interval
   *         {@link TradingProcess#openTime} to
   *         {@link TradingProcess#closeTime}
   */
  public static int[]
         getIndices(Vector times)
  {
    int n = (int) (TradingProcess.tradingDuration / ProcessEstimator.W);
    //out.println("Dividing the interval containing " + times.size() + " points into " + n + " pieces");
    int indexes[] = new int[n];
    for (int i = 0; i < n; i++)
    {
      double startPoint = TradingProcess.openTime + ((i) * ProcessEstimator.W);
      double endPoint = TradingProcess.openTime + ((i + 1) * ProcessEstimator.W);

      double t = DateUtils.convertTimeUnits(endPoint, TimeUnit.HOURS, TimeUnit.MILLISECONDS);
      int idx = times.find(t, Condition.GTE, i == 0 ? 0 : indexes[i - 1]);
      if (idx == -1)
      {
        idx = times.size() - 1;
      }
      indexes[i] = idx;
    }
    return indexes;
  }
}
