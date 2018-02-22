package stochastic.pointprocesses.ui;

import static java.awt.EventQueue.invokeLater;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.stream.IntStream.rangeClosed;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.oxbow.swingbits.dialog.task.TaskDialogs;

import fastmath.DoubleColMatrix;
import fastmath.DoubleMatrix;
import fastmath.Vector;
import fastmath.matfile.MatFile;
import stochastic.pointprocesses.finance.TradingProcess;
import stochastic.pointprocesses.finance.TradingStrategy;
import stochastic.pointprocesses.selfexciting.AbstractSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.BoundedParameter;
import stochastic.pointprocesses.selfexciting.ExponentialSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.Type;
import util.Plotter;

public class ProcessModeller
{

  private static final class MatFileFilter extends FileFilter
  {
    @Override
    public boolean
           accept(File f)
    {
      return f.getName().toLowerCase().endsWith(".mat") || f.isDirectory();
    }

    @Override
    public String
           getDescription()
    {

      return ".mat files";
    }
  }

  private final class ModelParametersFileFilter extends FileFilter
  {
    @Override
    public String
           getDescription()
    {
      return process.getClass().getSimpleName() + " parameters (" + process.getType().getFilenameExtension() + " )";
    }

    @Override
    public boolean
           accept(File f)
    {
      return f.getName().contains("." + process.getType().getFilenameExtension() + ".") || f.isDirectory();
    }
  }

  private JFrame frame;
  private JComboBox<Type> processTypeComboBox;
  private ParameterPanel parameterPanel;
  private ExponentialSelfExcitingProcess process;
  private Container contentPane;
  private KernelPanel kernelPanel;
  private DefaultTableModel coeffecientModel;
  private JTable coeffecientTable;

  /**
   * Launch the application.
   */
  public static void
         main(String[] args)
  {
    EventQueue.invokeLater(() -> {
      try
      {
        ProcessModeller window = new ProcessModeller();
        window.frame.setVisible(true);
      }
      catch (Exception e)
      {
        e.printStackTrace(err);
      }
    });
  }

  /**
   * Create the application.
   */
  public ProcessModeller()
  {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   * 
   * @wbp.parser.entryPoint
   */
  private void
          initialize()
  {
    frame = new JFrame();
    frame.setTitle(getClass().getSimpleName());
    frame.setBounds(100, 100, 2000, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    JPanel topPanel = getTopPanel();
    topPanel.setMaximumSize(new Dimension(3000, 500));
    contentPane.add(topPanel, BorderLayout.PAGE_START);
    refreshProcess();

    updateParameterPanel();

    refreshTypeComboBox(null);

    contentPane.add(kernelPanel = new KernelPanel(process), BorderLayout.PAGE_END);

    doLayout();
  }

  public JPanel
         getTopPanel()
  {
    JPanel topPanel = new JPanel(new BorderLayout());

    JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

    Type[] processTypes = Type.values();
    processTypeComboBox = new JComboBox<>(processTypes);
    processTypeComboBox.addActionListener(this::refreshTypeComboBox);

    topLeftPanel = new JPanel(new FlowLayout());

    topLeftPanel.add(processTypeComboBox);
    JButton loadParameterButton = newLoadParametersButton();
    buttonPanel.add(loadParameterButton);
    JButton loadPointsButton = newLoadPointsButton();

    buttonPanel.add(loadPointsButton);
    JButton adaptButton = new JButton("Adapt");
    int numStarts = Runtime.getRuntime().availableProcessors() * 2;

    JProgressBar progressBar = new JProgressBar(0, numStarts);
    progressBar.setEnabled(true);
    progressBar.setStringPainted(true);
    progressBar.setString("Adaption...");
    adaptButton.addActionListener(event -> {
      new Thread(() -> {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        process.estimateParameters(numStarts, j -> {
          try
          {
            SwingUtilities.invokeAndWait(() -> {
              progressBar.setValue(j);
              progressBar.repaint();
            });
          }
          catch (InvocationTargetException | InterruptedException e)
          {
            e.printStackTrace(System.err);
            throw new RuntimeException(e.getMessage(), e);
          }
        });
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        progressBar.setValue(0);
        progressBar.setEnabled(false);
        try
        {
          SwingUtilities.invokeAndWait(() -> {
            for (BoundedParameter param : process.getBoundedParameters())
            {
              parameterPanel.setParameterValue(param.getName(), process.getFieldValue(param.getName()));
            }
          });
        }
        catch (InvocationTargetException | InterruptedException e)
        {
          e.printStackTrace(System.err);
          throw new RuntimeException(e.getMessage(), e);
        }
        onParameterUpdated();
      }).start();
    });
    buttonPanel.add(adaptButton);
    buttonPanel.add(progressBar);

    topLeftPanel.add(buttonPanel);
    topPanel.add(topLeftPanel, BorderLayout.WEST);

    process = (ExponentialSelfExcitingProcess) Type.values()[0].instantiate();

    coeffecientModel = new DefaultTableModel(process != null ? process.order() : 0, tableColumnNames.length);
    coeffecientModel.setColumnIdentifiers(tableColumnNames);
    coeffecientTable = new JTable(coeffecientModel);
    coeffecientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    updateCoeffecientTableValues();

    JPanel topRightPanel = new JPanel(new BorderLayout());

    tableScroller = new JScrollPane(coeffecientTable);
    topRightPanel.add(tableScroller, BorderLayout.PAGE_START);
    topPanel.add(topRightPanel, BorderLayout.CENTER);
    topRightPanel.revalidate();
    // ModelViewer.getLogPriceChart(process);
    topRightBottomPanel = new JPanel();
    topRightPanel.add(topRightBottomPanel, BorderLayout.PAGE_END);
    return topPanel;
  }

  public JButton
         newLoadParametersButton()
  {
    JButton loadParameterButton = new JButton("Load parameters");
    loadParameterButton.addActionListener(event -> {
      JFileChooser fileChooser = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
      fileChooser.setFileFilter(new ModelParametersFileFilter());
      if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
      {
        EventQueue.invokeLater(() -> {
          out.println("Loading parameters from " + fileChooser.getSelectedFile());
          process.loadParameters(fileChooser.getSelectedFile());
          parameterPanel.sliderHandlerEnabled = false;
          for (Field field : process.getParameterFields())
          {
            parameterPanel.setParameterValue(field.getName(), process.getFieldValue(field));
          }
          parameterPanel.sliderHandlerEnabled = true;
        });
      }

    });
    return loadParameterButton;
  }

  public JButton
         newLoadPointsButton()
  {
    JButton loadPointsButton = new JButton("Load points");
    ActionListener actionListener = event -> {
      chooseMarkedPointsFileToLoadFrom();
    };
    loadPointsButton.addActionListener(actionListener);
    return loadPointsButton;
  }

  public void
         chooseMarkedPointsFileToLoadFrom()
  {
    JFileChooser fileChooser = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
    fileChooser.setFileFilter(new MatFileFilter());
    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
    {
      loadMarkedPointsFromFile(topLeftPanel, fileChooser.getSelectedFile(), "SPY");
    }
  }

  public void
         loadMarkedPointsFromFile(JPanel topLeftPanel,
                                  File selectedFile,
                                  String symbol)
  {
    try
    {
      Map<String, DoubleColMatrix> data = null;
      data = MatFile.matrixMap(selectedFile);
      DoubleMatrix markedPoints = data.get(symbol);

      Vector times = markedPoints.col(0);

      out.println("Loading (marked) points from " + selectedFile + ": " + data.keySet());
      int n = (int) (TradingProcess.tradingDuration / 0.5);
      int indexes[] = TradingStrategy.getIndices(times);
      markedPoints = markedPoints.sliceRows(0, indexes[0]);
      times = markedPoints.col(0);
      out.println("Loaded the first 30 minutes of data containing " + markedPoints.getRowCount() + " points");

      ArrayList<AbstractSelfExcitingProcess> processes = new ArrayList<>();
      ;
      times = times.copy().subtract(times.get(0));
      process.T = times;
      process.X = markedPoints;
      Vector counts = new Vector(rangeClosed(1, times.size()).mapToDouble(i -> i));
      topLeftPanel.add(new XChartPanel<XYChart>(Plotter.plot(times, counts, "counts")));
      doLayout();
    }
    catch (IOException e)
    {
      invokeLater(() -> {
        TaskDialogs.showException(e);
      });
    }
  }

  public static void
         resizeColumns(JTable table)
  {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++)
    {
      int width = 50; // Min width
      for (int row = 0; row < table.getRowCount(); row++)
      {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        Component comp = table.prepareRenderer(renderer, row, column);
        width = Math.max(comp.getPreferredSize().width + 1, width);
      }
      // if (width > 300)
      // width = 300;
      columnModel.getColumn(column).setPreferredWidth(width + 10);
    }
  }

  JPanel topRightBottomPanel;

  public void
         updateParameterPanel()
  {
    if (parameterPanel != null)
    {
      topRightBottomPanel.remove(parameterPanel);
    }
    topRightBottomPanel.add(parameterPanel = new ParameterPanel(process, this::onParameterUpdated), BorderLayout.PAGE_END);
    topRightBottomPanel.revalidate();
    doLayout();
  }

  public void
         onParameterUpdated()
  {
    if (kernelPanel != null)
    {
      kernelPanel.refreshGraphs();
    }
    updateCoeffecientTableValues();
  }

  public void
         doLayout()
  {
    frame.validate();
    frame.pack();
  }

  public void
         refreshTypeComboBox(ActionEvent event)
  {
    refreshProcess();
    updateCoeffecientTableValues();

    /**
     * TODO: add m and M here
     */
    // JPanel mrowPanel = getParameterRow("m", 0.1, 5, 1000);
    // JPanel MrowPanel = getParameterRowPanel("M", 1, 15, 15);
    // JSpinner Mspinner = new JSpinner(new SpinnerNumberModel(15, 1, 15, 1));

    // parameterPanel.add(Mspinner);
    // JPanel MowPanel = new JPanel(new GridLayout(1, 5));

  }

  public AbstractSelfExcitingProcess
         refreshProcess()
  {
    Type type = getSelectedType();
    if (process == null || !process.getType().equals(type))
    {
      out.println("Switched kernel to " + type);
      ExponentialSelfExcitingProcess prevProcess = process;
      process = (ExponentialSelfExcitingProcess) type.instantiate();
      if (prevProcess != null)
      {
        process.T = prevProcess.T;
        process.X = prevProcess.X;
      }
      coeffecientModel.setRowCount(process.order());
      updateParameterPanel();
      if (kernelPanel != null)
      {
        kernelPanel.setProcess(process);
      }

    }
    updateCoeffecientTableValues();

    tableScroller.revalidate();

    return process;
  }

  public void
         updateCoeffecientTableValues()
  {
    for (int i = 0; i < process.order(); i++)
    {
      double amplitude = process.α(i) / process.Z();
      double decayRate = process.β(i) / process.Z();
      double γ = process.γ(i);
      double halfDuration = process.getHalfDuration(i);
      coeffecientModel.setValueAt(i, i, 0);
      coeffecientModel.setValueAt(amplitude, i, 1);
      coeffecientModel.setValueAt(decayRate, i, 2);
      coeffecientModel.setValueAt(halfDuration, i, 3);
    }
    resizeColumns(coeffecientTable);
    coeffecientTable.repaint();
  }

  public Type
         getSelectedType()
  {
    return Type.values()[processTypeComboBox.getSelectedIndex()];
  }

  String[] tableColumnNames = new String[]
  { "#", "α/Z", "β/Z", "1/2-duration" };

  private JScrollPane tableScroller;
  private JPanel topLeftPanel;

}
