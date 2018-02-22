package stochastic.pointprocesses.autoexciting.multivariate;

import static java.lang.System.out;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import fastmath.DoubleMatrix;
import fastmath.Vector;
import fastmath.matfile.MatFile;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess;
import stochastic.pointprocesses.finance.Side;
import stochastic.pointprocesses.finance.TradingFiltration;
import stochastic.pointprocesses.finance.TradingProcess;
import stochastic.pointprocesses.finance.TradingStrategy;

public class BivariateProcessPredictor
{

  public static void
         main(String[] args) throws IOException
  {
    File modelFile = new File("/home/stephen/fastmath/SPY.mat.meapl.0.model");
    UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess process = new UnitRandomWalkExtendedApproximatePowerlawMutuallyExcitingProcess(2);
    process.loadParameters(modelFile);
    out.println("Loaded " + process + " from " + modelFile.getAbsolutePath());
    TradingFiltration filtration = new TradingFiltration(MatFile.loadMatrix("/home/stephen/fastmath/SPY.mat", "SPY"));
    ArrayList<TradingFiltration> slices = sliceFiltration(filtration);
    TradingFiltration firstSlice = slices.get(0);

    process.K = firstSlice.types;
    process.T = firstSlice.times;
    process.X = firstSlice.markedPoints;
    double ll = process.logLik();
    Vector innov = process.getInnovationSequence(0);
    out.println("ll=" + ll);
    Vector buyTimes = process.getTimes(Side.Buy.ordinal());
    Vector sellTimes = process.getTimes(Side.Sell.ordinal());
    assert (firstSlice.buyTimes.size() == buyTimes.size());
    assert (firstSlice.sellTimes.size() == sellTimes.size());

    double mean = process.Λ(0).mean();
    out.println("mean0=" + mean);

    for (int i = 0; i < 20; i++)
    {
      double fuck = process.invΛ(0, i, 1);
      out.println( "fuck=" + fuck );
    }

    MatFile.write("innov.mat", innov.createMiMatrix());
  }

  public static ArrayList<TradingFiltration>
         sliceFiltration(TradingFiltration tradingProcess) throws IOException
  {
    Vector times = tradingProcess.times;

    int n = (int) (TradingProcess.tradingDuration / MutuallyExcitingProcessEstimator.W);

    ArrayList<TradingFiltration> slices = new ArrayList<TradingFiltration>();

    for (int section = 0; section < n; section++)
    {
      int firstRow = section == 0 ? 0 : tradingProcess.tradeIndexes[section - 1];
      int lastRow = tradingProcess.tradeIndexes[section];
      DoubleMatrix markedPointSlice = tradingProcess.markedPoints.sliceRows(firstRow, lastRow);
      TradingFiltration slice = new TradingFiltration(markedPointSlice, tradingProcess.types.slice(firstRow, lastRow));
      // slice.tradeIndexes = TradingStrategy.getIndices(times);

      slices.add(slice);
    }

    return slices;
  }
}
