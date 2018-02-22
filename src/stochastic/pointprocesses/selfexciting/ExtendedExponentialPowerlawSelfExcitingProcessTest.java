package stochastic.pointprocesses.selfexciting;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.ansi;
import static util.Console.println;

import java.io.File;
import java.io.IOException;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import fastmath.IntVector;
import fastmath.Vector;
import junit.framework.TestCase;
import stochastic.pointprocesses.autoexciting.multivariate.AbstractMutuallyExcitingProcess;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.DiagonalExtendedApproximatePowerlawMututallyExcitingProcess;

@SuppressWarnings(
{ "unused" })
public class ExtendedExponentialPowerlawSelfExcitingProcessTest extends TestCase
{

  private double phasedt;

  public void
         testSimulation()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    Vector points = process.simulate(5, 30000);
    out.println("pdiff = " + points.diff().mean());
    ExtendedApproximatePowerlawSelfExcitingProcess inferredProcess = process.copy();
    inferredProcess.T = points;
    inferredProcess.estimateParameters(20);
    out.println("simulated " + process);
    out.println("inferred " + inferredProcess);
    assertTrue(abs(process.meanRecurrenceTime() - inferredProcess.meanRecurrenceTime()) < 1);
  }

  public void
         testInvLambdaExpectation()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    // process.ε = 0.05;

    process.T = new Vector(new double[]
    { 65, 67, 86, 140, 141, 149, 151, 163, 201, 205 }).setName("T");

  }

  public void
         testInvLambda()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    // process.ε = 0.05;

    process.T = new Vector(new double[]
    { 65, 67, 86, 140, 141, 149, 151, 163, 201, 205 }).setName("T");
    int n = process.T.size();

    // process.T = process.T.subtract(process.T.get(0));
    process.trace = false;

    // process.printResults( process.estimateParameters(25) );

    // out.println( " Λ=" + process.Λ() );
    // for (int tk = 0; tk < 10; tk++)
    // {
    // out.println("A[" + tk + "]=" + Arrays.toString(process.A[tk]));
    // }
    out.println("estimated " + ansi().fgBrightYellow() + process + ansi().fgDefault() + " from " + process.T.size() + " points");
    out.println(process.getαβString());
    process.trace = false;
    double Λmean = process.Λ().mean();
    process.trace = false;
    double Λvar = process.Λ().variance();
    out.println("Λmean=" + ansi().fgBrightRed() + Λmean + ansi().fgDefault() + " Λvar=" + ansi().fgBrightRed() + Λvar + ansi().fgDefault());

    out.println(ansi().fgBrightGreen() + process.T.toString() + ansi().fgDefault());
    process.dT = null;
    out.println(ansi().fgBrightGreen() + process.dT().toString() + ansi().fgDefault());

    process.trace = true;
    out.println(" Λ=" + process.Λ());
    process.trace = false;

    process.trace = true;
    for (double y = 0.01; y <= 4.71; y += y < 4.6 ? 0.1 : 0.01)
    {
      process.trace = false;
      double dt = process.invΛ(y);
      process.trace = true;
      // Real dtReal = process.invΛReal(y);

      double q = process.Λ(n - 1, dt);

      out.println("y=" + y + " dt=" + dt + " q=" + q);
      out.flush();
      assertEquals(y, q, 1E-10);
    }
  }

  // public void testNΦ()
  // {
  //
  // }

  final public static double tolerance = 1E-12;

  public void
         testNormalization()
  {
    final ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();

    double z = process.Z();
    assertEquals(20.34, z, 1E-5);

  }

  public void
         testSaveLoad() throws IOException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = new ExtendedApproximatePowerlawSelfExcitingProcess();
    process.b = 1;
    process.η = 1;
    process.ε = 0.25;
    process.τ = 1;
    File tempFile = File.createTempFile("test", "params");
    out.println("Wrote params to " + tempFile.getAbsolutePath());
    process.storeParameters(tempFile);
    ExtendedApproximatePowerlawSelfExcitingProcess loaded = new ExtendedApproximatePowerlawSelfExcitingProcess();
    loaded.loadParameters(tempFile);
    assertEquals(process.getParameters(), loaded.getParameters());
  }

  // public void
  // testIntegralOfKernel()
  // {
  // ExtendedApproximatePowerlawSelfExcitingProcess process =
  // constructProcess();
  //
  //
  // RombergIntegrator integrator = new RombergIntegrator();
  // double integral = integrator.integrate(5000000, process::ν, 0, 30000 *
  // 10);
  // out.println("integral=" + integral);
  // assertEquals(1, integral, 1E-1);
  // }
  //
  public void
         testfandF()
  {

    final ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();

    println(process.getParamString());
    assertEquals(0.07971548, process.f(1.9), 1E-7);
    assertEquals(0.230423469, process.F(1.9), 1E-7);

  }

  public void
         testφ() throws InterruptedException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();
    process.T = new Vector(3);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 27);

    process.trace = false;
    ExponentialDistribution expDist = new ExponentialDistribution(1);
    double y = 0.75;
    out.println("T=" + process.T);
    out.println("y=" + y);
    out.println("dΛ=" + process.Λ());
    int n = process.T.size() - 1;

    double x = process.φ(1.2, y);
    
    out.println( "φ=" +x  );
  }

  public void
         testinvΛ() throws InterruptedException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();
    process.T = new Vector(3);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 27);

    process.trace = false;
    ExponentialDistribution expDist = new ExponentialDistribution(1);
    double y = 0.75;
    out.println("T=" + process.T);
    out.println("y=" + y);
    out.println("dΛ=" + process.Λ());
    int n = process.T.size() - 1;

    Vector compensated = process.Λ();
    process.trace = true;
    double nextdt = process.invΛ(y);
    process.trace = false;
    process.trace = true;
    process.trace = false;

    out.println("invΛ(y=" + y + ")=" + nextdt);

    process.T = process.T.copyAndAppend(process.T.fmax() + nextdt);
    process.dT = null;

    out.println("T=" + process.T);
    compensated = process.Λ();
    out.println("compensated=" + compensated);

  }

  public void
         testTotalΛ() throws InterruptedException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();
    process.T = new Vector(4);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 24);
    process.T.set(3, 27);

    AbstractMutuallyExcitingProcess mprocess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
    mprocess.assignParameters(process.getParameters().toDoubleArray());
    mprocess.T = process.T;
    mprocess.K = new IntVector(process.T.size());

    double a = process.Λ().sum();
    double b = process.totalΛ();
    double c = mprocess.Λ(0).sum();

    assertEquals(a, b, 1E-15);
    assertEquals(a, c, 1E-15);

  }

  public void
         testLL() throws InterruptedException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();
    process.T = new Vector(4);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 24);
    process.T.set(3, 27);

    AbstractMutuallyExcitingProcess mprocess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
    mprocess.assignParameters(process.getParameters().toDoubleArray());
    mprocess.T = process.T;
    mprocess.K = new IntVector(process.T.size());

    // out.println("process Z = " + process.Z());
    // out.println("mprocess Z = " + mprocess.Z(0, 0));

    Vector lp = process.λvector();
    Vector mlp = mprocess.λvector(0);

    out.println("lp=" + lp);
    out.println("mp=" + mlp);

    Vector ucomp = process.Λ();
    Vector mcomp = mprocess.Λ(0);

    assertTrue(ucomp.equals(mcomp, pow(10, -13)));
    out.println("ucomp=" + ucomp);
    out.println("mcomp=" + mcomp);

    assertTrue(lp.equals(mlp, pow(10, -13)));

    // XYChart chart = Plotter.chart("uni", "a", process::λ, 0, 300, t -> t);
    // Pair<double[], double[]> sample = Plotter.sampleFunction(t -> mprocess.λ(0,
    // t), 1000, 0, 300, t -> t);
    // chart.addSeries("m", sample.left, sample.right);
    // display(chart);
    //
    // while( chart != null )
    // {
    // Thread.sleep(1000);
    // }
    // out.println("lp=" + lp);
    // out.println("mlp=" + mlp);

    double a = process.logLik();
    double c = mprocess.logLik();

    assertEquals(a, c, 1E-15);

  }

  public void
         testA()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = constructProcess();
    process.T = new Vector(3);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 27);
    for (int j = 0; j < process.order(); j++)
    {
      for (int i = 0; i < process.T.size(); i++)
      {
        double a = process.Asum(i, j);
        double b = process.A(j, i);
        double d = process.B(i, j);
        // out.println("a=" + a + " b=" + b + " c=" + c + " d=" + d);
        assertEquals(a, b, 1E-14);
        assertEquals(a, 1 + d, 1E-14);
      }
    }

  }

  public static ExtendedApproximatePowerlawSelfExcitingProcess
         constructProcess()
  {
    final ExtendedApproximatePowerlawSelfExcitingProcess process = new ExtendedApproximatePowerlawSelfExcitingProcess();

    process.assignParameters(new double[]
    { 1, 0, 3, 1.78 });
    return process;
  }

}
