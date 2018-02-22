package stochastic.pointprocesses.ui;

import static java.lang.System.out;
import static util.Plotter.plot;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.XYStyler;

import fastmath.Pair;
import stochastic.pointprocesses.selfexciting.AbstractSelfExcitingProcess;
import util.Plotter;
import util.RelativeLayout;

public class KernelPanel extends JPanel
{
  private static final String INT_HAZARD = "∫h(t)";
  private static final String INT_KERNEL = "F=∫f(t)dt";
  private static final String HAZARD = "h(t)";
  private static final String KERNEL = "f(t)";
  private static final int f_MAXRANGE = 100;
  private static final int SAMPLES = 1000;

  private static final String ANTI_F = "anti(F)";
  private AbstractSelfExcitingProcess process;
  private XChartPanel<XYChart> inverseIntegratedImpulseResponseChartPanel;
  private XChartPanel<XYChart> impulseResponseChartPanel;
  private XYChart impulseResponseChart;
  private XYChart integratedImpulseResponseChart;
  private XChartPanel<XYChart> HphasePanel;
  double H = 0;
  private XChartPanel<XYChart> integratedImpulseResponseChartPanel;

  public KernelPanel(AbstractSelfExcitingProcess process)
  {
    super(new RelativeLayout());
    this.process = process;
    assert process != null;



    impulseResponseChartPanel = plot("t (ms)", KERNEL, process == null ? t -> 0 : process::f, 0, f_MAXRANGE);
    impulseResponseChart = impulseResponseChartPanel.getChart();

    plot(impulseResponseChart, HAZARD, process == null ? t -> 0 : process::F, 0, 100);

    inverseIntegratedImpulseResponseChartPanel = plot("t (ms)", INT_KERNEL, process == null ? t -> 0 : process::F, 0, f_MAXRANGE, chart -> {
      XYStyler styler = chart.getStyler();
      styler.setYAxisMin(0.0);
      styler.setYAxisMax(1.0);
    });
    integratedImpulseResponseChart = inverseIntegratedImpulseResponseChartPanel.getChart();

    add(impulseResponseChartPanel);
    add(inverseIntegratedImpulseResponseChartPanel);
    add(inverseIntegratedImpulseResponseChartPanel);
    add( HphasePanel = plot("t (ms)", "Hphase(H,t)", process == null ? t -> 0 : t -> process.φδ(H, t), 0, 3) );
    JSlider Hslider = new JSlider(SwingConstants.VERTICAL, 0, 1000, 500);
    Hslider.addChangeListener(event -> {
      H = Hslider.getValue() / 1000;
    });
    add(Hslider);
    add(new JLabel("H"));

  }

  public void
         setProcess(AbstractSelfExcitingProcess process)
  {
    this.process = process;
    // chart.updateXYSeries("sine", data[0], data[1], null);
  }

  public void
         refreshGraphs()
  {
    out.println("refresh graphs " + process.getParamString());
  //  Pair<double[], double[]> invhSample = Plotter.sampleFunction(process::invH, SAMPLES, 0, 1, t -> t);
//    inverseIntegratedHazardChart.updateXYSeries(ANTI_H, invhSample.left, invhSample.right, null);
//    inverseIntegratedHazardChartPanel.revalidate();
//    inverseIntegratedHazardChartPanel.repaint();

    Pair<double[], double[]> νSample = Plotter.sampleFunction(process::f, SAMPLES, 0, f_MAXRANGE, t -> t);
    impulseResponseChart.updateXYSeries(KERNEL, νSample.left, νSample.right, null);

  //  Pair<double[], double[]> hSample = Plotter.sampleFunction(process::h, SAMPLES, 0, f_MAXRANGE, t -> t);
   // impulseResponseChart.updateXYSeries(HAZARD, hSample.left, hSample.right, null);
    impulseResponseChartPanel.revalidate();
    impulseResponseChartPanel.repaint();

   // Pair<double[], double[]> ihSample = Plotter.sampleFunction(process::H, SAMPLES, 0, f_MAXRANGE, t -> t);
    Pair<double[], double[]> iνSample = Plotter.sampleFunction(process::F, SAMPLES, 0, f_MAXRANGE, t -> t);

 //   integratedImpulseResponseChart.updateXYSeries(INT_HAZARD, ihSample.left, ihSample.right, null);
    integratedImpulseResponseChart.updateXYSeries(INT_KERNEL, iνSample.left, iνSample.right, null);

    inverseIntegratedImpulseResponseChartPanel.revalidate();
    inverseIntegratedImpulseResponseChartPanel.repaint();
  }

}
