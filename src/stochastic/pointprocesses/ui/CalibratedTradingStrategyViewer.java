package stochastic.pointprocesses.ui;

import static java.lang.Math.exp;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static util.Plotter.plot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.XYStyler;

import fastmath.Vector;
import fastmath.matfile.MatFile;
import stochastic.pointprocesses.finance.TradingFiltration;
import stochastic.pointprocesses.finance.TradingStrategy;
import stochastic.pointprocesses.selfexciting.AbstractSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.Type;
import util.DateUtils;
import util.Plotter;

/**
 * TODO: add emperical autocorrelation and histogram
 * 
 */
public class CalibratedTradingStrategyViewer
{

  public JFrame frame;
  private JTable table;
  private List<AbstractSelfExcitingProcess> processes;
  private XChartPanel<XYChart> priceChartPanel;
  private JPanel bottomPanel;

  public static void
         main(String args[]) throws IOException
  {
    final String matFile = args.length > 0 ? args[0] : "/home/stephen/fm/SPY.mat";
    final String symbol = args.length > 1 ? args[1] : "SPY";

    TradingFiltration filtration = new TradingFiltration(MatFile.loadMatrix(matFile, symbol));

    ArrayList<AbstractSelfExcitingProcess> processes = TradingStrategy.getCalibratedProcesses(matFile, filtration, Type.ExtendedApproximatePowerlaw);

    CalibratedTradingStrategyViewer.launchModelViewer(processes);

  }

  public CalibratedTradingStrategyViewer(List<AbstractSelfExcitingProcess> processes)
  {
    Object[][] data = getProcessParametersAndStatisticsMatrix(processes);

    String[] columnHeaders = processes.get(0).getColumnHeaders();

    initialize();
    DefaultTableModel tableModel = new DefaultTableModel()
    {
      @Override
      public boolean
             isCellEditable(int arg0,
                            int arg1)
      {
        return false;
      }
    };
    tableModel.setDataVector(data, columnHeaders);
    table.setModel(tableModel);
    this.processes = processes;
  }

  public static Object[][]
         getProcessParametersAndStatisticsMatrix(List<AbstractSelfExcitingProcess> processes)
  {
    List<Object[]> processStats = processes.stream()
                                           .map(process -> process.evaluateParameterStatistics(process.getParameters().toDoubleArray()))
                                           .collect(toList());
    int N = processStats.get(0).length;
    Object[][] stats = new Object[processStats.size()][N];
    for (int i = 0; i < processStats.size(); i++)
    {
      Object[] row = processStats.get(i);
      for (int j = 0; j < N; j++)
      {
        stats[i][j] = row[j];
      }
    }
    return stats;
  }

  /**
   * Initialize the contents of the frame.
   */
  private void
          initialize()
  {
    frame = new JFrame();
    frame.setTitle(getClass().getSimpleName());
    frame.setBounds(100, 100, 2200, 1057);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    table = new JTable();
    table.setCellSelectionEnabled(true);
    table.setColumnSelectionAllowed(true);

    JScrollPane scrollPane = new JScrollPane(table);

    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitPane.setTopComponent(scrollPane);
    splitPane.setDividerLocation(250);
    bottomPanel = new JPanel();

    splitPane.setBottomComponent(bottomPanel);
    bottomPanel.setLayout(new GridLayout(3, 1));

    frame.getContentPane().add(splitPane, BorderLayout.CENTER);
  }

  public void
         show()
  {

    plotData();

    EventQueue.invokeLater(() -> frame.setVisible(true));
  }

  public void
         plotData()
  {
    AbstractSelfExcitingProcess firstProcess = processes.get(0);

    plotProcess(firstProcess);

  }

  public void
         plotProcess(AbstractSelfExcitingProcess process)
  {

    XYChart priceChart = getLogPriceChart(process);
    priceChartPanel = new XChartPanel<XYChart>(priceChart);

    XChartPanel<XYChart> intensityChart = getIntensityChartPanel(process);

    bottomPanel.add(priceChartPanel);
    bottomPanel.add(intensityChart);
    bottomPanel.add(new KernelPanel(process));
  }

  public XChartPanel<XYChart>
         getIntensityChartPanel(AbstractSelfExcitingProcess process)
  {
    double firstTime = process.T.fmin();
    double intensityPlotLengthInSeconds = 5;
    double lastTime = firstTime + 1000 * intensityPlotLengthInSeconds;

    XChartPanel<XYChart> intensityChart =
                                        plot("t (seconds)", "λ(t)", t -> process.λ(t) * 1000, firstTime, lastTime, 2000, t -> (t - firstTime) / 1000, chart -> {
                                          chart.setTitle("conditional intensity(event rate)");
                                          chart.setYAxisTitle("events per second");
                                          chart.getStyler().setSeriesColors(new Color[]
                                          { Color.RED });
                                        });

    return intensityChart;
  }

  public static CalibratedTradingStrategyViewer
         launchModelViewer(ArrayList<AbstractSelfExcitingProcess> processes)
  {
    CalibratedTradingStrategyViewer viewer = new CalibratedTradingStrategyViewer(processes);
    viewer.show();
    return viewer;
  }

  public static XYChart
         getLogPriceChart(AbstractSelfExcitingProcess process)
  {
    double millisecondsToHours = DateUtils.convertTimeUnits(1, TimeUnit.MILLISECONDS, TimeUnit.HOURS);
    double millisecondsToSeconds = DateUtils.convertTimeUnits(1, TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
    Vector elapsedTimesInMilliseconds = process.T;
    Vector timesInHours = elapsedTimesInMilliseconds.copy().multiply(millisecondsToHours);
    Vector elapsedTimesInSeconds = elapsedTimesInMilliseconds.copy().multiply(millisecondsToSeconds);
    assert timesInHours.equals(process.X.col(0));
    Vector logPrices = process.X.col(1).copy().add(1).log();
    double logReferencePrice = logPrices.get(0);
    Vector relativeLogPricePercentages = logPrices.subtract(logReferencePrice).multiply(100);
    double referencePrice = exp(logReferencePrice);
    String logPriceName = format("(ln(1+price)-ln(1+%4.2f))%%", referencePrice);

    XYChart priceChart = new XYChart(2000, 500);

    priceChart.addSeries(logPriceName, timesInHours.toDoubleArray(), relativeLogPricePercentages.toDoubleArray());
    priceChart.setTitle("price Δ%");
    priceChart.setXAxisTitle("t (hours)");
    priceChart.setYAxisTitle(logPriceName);
    XYStyler styler = priceChart.getStyler();
    Plotter.configureStyler(styler);
    styler.setLegendVisible(false);
    styler.setToolTipsEnabled(true);
    styler.setMarkerSize(0);
    styler.setYAxisTicksVisible(true);
    return priceChart;
  }

}
