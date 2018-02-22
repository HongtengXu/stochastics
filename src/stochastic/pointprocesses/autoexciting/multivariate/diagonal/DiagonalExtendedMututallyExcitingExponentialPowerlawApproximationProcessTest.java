package stochastic.pointprocesses.autoexciting.multivariate.diagonal;

import static fastmath.Functions.sum;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.ansi;

import java.util.Arrays;
import java.util.TreeMap;

import org.apache.commons.math3.optim.PointValuePair;

import fastmath.IntVector;
import fastmath.Pair;
import fastmath.Vector;
import junit.framework.TestCase;
import stochastic.pointprocesses.autoexciting.multivariate.AbstractMutuallyExcitingProcess;
import stochastic.pointprocesses.autoexciting.multivariate.ExponentialMutuallyExcitingProcess;
import stochastic.pointprocesses.selfexciting.ExtendedApproximatePowerlawSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.ExtendedExponentialPowerlawSelfExcitingProcessTest;

public class DiagonalExtendedMututallyExcitingExponentialPowerlawApproximationProcessTest extends TestCase
{

  public void
         testMeanRecurrenceTime()
  {
    DiagonalExtendedApproximatePowerlawMututallyExcitingProcess process =
                                                                        DiagonalExtendedMututallyExcitingExponentialPowerlawApproximationProcessTest.constructLongerProcess();
    process.τ.assign(0.4663536036, 0.4343345865);
    process.ε.assign(0, 0);
    process.η.assign(2.6985900988, 2.4276896668);
    process.b.assign(10, 10);

    Vector recurrenceTimes = process.meanRecurrenceTimeVector();
    ExtendedApproximatePowerlawSelfExcitingProcess univarProcess = new ExtendedApproximatePowerlawSelfExcitingProcess();
    univarProcess.τ = process.τ.get(0);
    univarProcess.ε = process.ε.get(0);
    univarProcess.η = process.η.get(0);
    univarProcess.b = process.b.get(0);

    double z2 = process.Z(0, 0);
    double z = univarProcess.Z();

    for (int i = 0; i < process.order(); i++)
    {
      double alpham = process.α(i, 0, 0);
      double alphau = univarProcess.α(i);
      double betam = process.β(i, 0, 0);
      double betau = univarProcess.β(i);
      out.println(i + " multi alpha=" + alpham + " uni alpha=" + alphau + " multi beta=" + betam + " uni beta= " + betau);
    }
    out.println("univar params: " + univarProcess.getαβString());
    out.println("multivar params: " + process.getαβString());
    out.println("meanRecurrenceTime=" + univarProcess.meanRecurrenceTime());
    univarProcess.printResults(new PointValuePair[]
    { new PointValuePair(univarProcess.getParameters().toDoubleArray(), 100) });
    assertEquals(z, z2, pow(10, -13));

    assertEquals(recurrenceTimes.get(0), univarProcess.meanRecurrenceTime(), pow(10, -12));

  }

  public static DiagonalExtendedApproximatePowerlawMututallyExcitingProcess
         constructLongerProcess()
  {
    DiagonalExtendedApproximatePowerlawMututallyExcitingProcess process = constructProcess();
    process.ε.assign(0.02, 0.04);
    process.T = new Vector(new double[]
    { 25, 92, 93, 104, 112, 166, 167, 175, 176, 189, 227, 229 });
    process.K = new IntVector(new int[]
    { 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1 });
    return process;
  }

  public static DiagonalExtendedApproximatePowerlawMututallyExcitingProcess
         constructProcess()
  {
    final DiagonalExtendedApproximatePowerlawMututallyExcitingProcess process = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(2);

    process.assignParameters(new double[]
    { 1, 0, 3, 1.78, 1, 0.01, 2.99, 1.75 });

    process.T = new Vector(3);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 27);
    process.K = new IntVector(3);
    process.K.set(0, 0);
    process.K.set(1, 1);
    process.K.set(2, 0);

    return process;
  }

  public void
         testA()
  {
    ExponentialMutuallyExcitingProcess process = constructLongerProcess();

    for (int m = 0; m < process.dim(); m++)
    {
      for (int n = 0; n < process.dim(); n++)
      {
        for (int tk = 0; tk < process.N(m); tk++)
        {
          for (int j = 0; j < process.order(); j++)
          {
            double a = process.Asum(j, m, n, tk);
            double b = process.AsumToo(j, m, n, tk);
            double c = process.A(j, m, n, tk);
            out.format("m=%d n=%d tk=%d j=%d Asum=%f AsumToo=%f A=%f\n", m, n, tk, j, a, b, c);
            out.flush();
            assertEquals(String.format("j=%s m=%s n=%s tk=%s Asum=%s != A=%s", j, m, n, tk, a, b), a, b, 1E-8);
            assertEquals(String.format("j=%s m=%s n=%s tk=%s Asum=%s != A=%s", j, m, n, tk, a, b), a, c, 1E-8);
          }
        }
      }
    }

  }

  public void
         testIntensity() throws InterruptedException
  {
    ExponentialMutuallyExcitingProcess process = constructLongerProcess();

    Vector trueIntensity0 = new Vector(new double[]
    { 0.0000000000, 0.0009772861, 0.1226659480, 0.0020090410, 0.1236901367 });
    Vector trueIntensity1 = new Vector(new double[]
    { 0.0000000000, 0.0156125015, 0.0022376454, 0.1308789764, 0.0076528586, 0.0049141955, 0.0908575556 });
    Vector intensityFast = process.λvector(0);
    Vector intensity1Fast = process.λvector(1);

    Vector intensity = process.λvectorSlow(0);
    Vector intensity1 = process.λvectorSlow(1);

    out.println(ansi().fgBrightGreen() + "process="
                + process
                + "\nparams="
                + process.getParamString()
                + "\nαβstring="
                + process.getαβString()
                + "\nT[0]="
                + process.getTimes(0)
                + "\nT[1]="
                + process.getTimes(1)
                + "\nintensity0Slow="
                + intensity
                + "\nintensity1Slow="
                + intensity1
                + "\nintensity0Fast="
                + intensityFast
                + "\nintensity1Fast="
                + intensity1Fast
                + ansi().fgDefault());

    assertTrue("should equal " + trueIntensity0, trueIntensity0.equals(intensity, pow(10, -9)));
    assertTrue("should equal " + trueIntensity1, trueIntensity1.equals(intensity1, pow(10, -9)));
    assertTrue("should equal " + trueIntensity0, trueIntensity0.equals(intensityFast, pow(10, -9)));
    assertTrue("should equal " + trueIntensity1, trueIntensity1.equals(intensity1Fast, pow(10, -9)));

    // XYChart chart = chart("t (ms)", "λ0", t -> process.λ(0, t), 0, 300, t -> t);
    // Pair<double[], double[]> sample = Plotter.sampleFunction(t -> process.λ(1,
    // t), 10000, 0, 300, t -> t);
    // chart.addSeries("λ1", sample.left, sample.right);
    // display(chart);
    // double cnt = 1;
    // while (cnt > 0)
    // {
    // Thread.sleep(1000);
    // }

    double λt1 = process.λ(0, process.T(0, 1));
    out.println("λt1=" + λt1);

    double λ0 = process.λ(0, 116);
    // double λ0r = process.λrecursive(0, 116);
    // out.println( "λ0=" + λ0 + "\nλ0r=" + λ0r);
    assertEquals(0.006488609684355583, λ0, pow(10, -11));

    double λ1 = process.λ(1, 116);
    assertEquals(0.05166212018884022, λ1, pow(10, -10));

  }

  public void
         testCompensator()
  {
    Vector correctComp0 = new Vector(new double[]
    { .6367558268, .155201254, 1.197807576, .156229219 });
    Vector correctComp1 = new Vector(new double[]
    { .4998888423, .8070096157, .165624490, 1.059560873, .805224444, .278509920 });

    ExponentialMutuallyExcitingProcess process = constructLongerProcess();
    Vector comp0 = process.Λ(0);
    Vector comp1 = process.Λ(1);
    out.println("process " + process + "\ncomp0=" + comp0);
    out.println("comp1=" + comp1);

    Vector comp0slow = process.Λslow(0);
    Vector comp1slow = process.Λslow(1);
    out.println("comp0slow=" + comp0slow);
    out.println("comp1slow=" + comp1slow);

    out.println("correctcomp0=" + correctComp0);
    out.println("correctcomp1=" + correctComp1);

    assertEquals(comp0slow.sum(), comp0.sum(), pow(10, -12));
    assertEquals(comp1slow.sum(), comp1.sum(), pow(10, -12));

    assertTrue(correctComp0.equals(comp0, pow(10, -7)));
    assertTrue(correctComp1.equals(comp1, pow(10, -7)));

  }

  public void
         testGetAndAssignParameters()
  {
    ExponentialMutuallyExcitingProcess process = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(2);
    int paramCount = process.getParamCount() * process.dim;
    Vector randomParams = new Vector(paramCount);
    for (int i = 0; i < randomParams.size(); i++)
    {
      randomParams.set(i, random());
    }
    process.assignParameters(randomParams.toDoubleArray());
    Vector gotParams = process.getParameters();
    assertEquals(randomParams, gotParams);
    // out.println( "paramCount=" + paramCount );
  }

  public void
         testLikelihood()
  {
    AbstractMutuallyExcitingProcess process = constructLongerProcess();

    double ll = process.logLik();
    out.println(ansi().fgBrightCyan() + "ll for " + process + " is " + ll + ansi().fgDefault());
    assertTrue(Double.isFinite(ll));
  }

  public void
         testSubTimes()
  {
    AbstractMutuallyExcitingProcess process = constructProcess();
    process.T = new Vector(new double[]
    { 25, 91, 93, 112, 166, 167, 175, 176, 189, 227 });
    process.K = new IntVector(new int[]
    { 0, 0, 1, 0, 1, 1, 0, 0, 1, 1 });
    Pair<Vector[], TreeMap<Double, Integer>[]> sliced = process.getTimeSubsets();

    TreeMap<Double, Integer>[] timeIndices = sliced.right;
    out.println("sliced left= " + Arrays.toString(sliced.left) + " right=" + Arrays.toString(timeIndices));

  }

  public void
         testTotalΛ()
  {
    ExponentialMutuallyExcitingProcess process = constructLongerProcess();

    // SimpsonIntegrator integrator = new SimpsonIntegrator();
    // out.println("integrating total compensator");
    double total = sum(m -> {
      // Vector mtimes = process.getTimes(m);
      return process.Λ(m).sum();
      // return integrator.integrate(10000000, t -> process.λ(m, t),
      // mtimes.getLeftmostValue(), mtimes.getRightmostValue());
    }, 0, process.dim() - 1);
    out.println("total is " + total);
    double tl = process.totalΛ();
    out.println("totalΛ=" + tl);
    assertTrue(Double.isFinite(tl));
  }

  public void
         testf()
  {
    AbstractMutuallyExcitingProcess process = constructLongerProcess();
    out.println("testing f for " + process);
    double val0 = process.f(0, 0, 6.5);
    double val1 = process.f(1, 1, 6.5);
    /**
     * see MultivariateInverseCompensator.mw
     */
    assertEquals(0.021354862160664107589, val0, pow(10, -10));
    assertEquals(0.022194945435683109689, val1, pow(10, -10));
  }

  public void
         testF()
  {
    AbstractMutuallyExcitingProcess process = constructLongerProcess();
    out.println("testing F for " + process);
    double val0 = process.F(0, 0, 6.5);
    double val1 = process.F(1, 1, 6.5);
    /**
     * see MultivariateInverseCompensator.mw
     */
    assertEquals(0.44789085456473543591, val0, pow(10, -10));
    assertEquals(0.47192399850061964076, val1, pow(10, -10));

    val0 = process.F(0, 0, 650000);
    val1 = process.F(1, 1, 650000);
    /**
     * see MultivariateInverseCompensator.mw
     */
    assertEquals(0.99999999999312388979, val0, pow(10, -10));
    assertEquals(0.99999999999396731432, val1, pow(10, -10));
  }

  public void
         testZ()
  {
    AbstractMutuallyExcitingProcess process = constructProcess();

    double z0 = process.Z(0, 0);
    out.println("z0=" + z0);
    assertEquals(20.34, z0, pow(10, -13));

    double z1 = process.Z(1, 1);
    out.println("z1=" + z1);
    assertEquals(19.488945713548063, z1, pow(10, -13));

  }

  public void
         testAlphaBetaAndGamma()
  {
    DiagonalExtendedApproximatePowerlawMututallyExcitingProcess mprocess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    mprocess.assignParameters(process.getParameters().toDoubleArray());
    for (int j = 0; j < process.M; j++)
    {
      assertEquals(process.α(j), mprocess.α(j, 0, 0));
      assertEquals(process.β(j), mprocess.β(j, 0, 0));
      assertEquals(process.γ(j), mprocess.γ(j, 0, 0));
    }
  }

  public void
         testinvΛ() throws InterruptedException
  {
    DiagonalExtendedApproximatePowerlawMututallyExcitingProcess mprocess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    process.T = new Vector(3);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 27);
    mprocess.K = new IntVector(process.T.size());
    mprocess.T = process.T;
    process.τ = 1;
    process.ε = 0;
    process.η = 3;
    process.b = 1.75;
    mprocess.assignParameters(process.getParameters().toDoubleArray());

    out.println(process + " " + process.meanRecurrenceTime() + " LL " + process.logLik());
    out.println(mprocess + " " + mprocess.meanRecurrenceTime(0) + " LL " + process.logLik());
    // ExponentialMutuallyExcitingProcess process = constructLongerProcess();

    process.trace = true;
    // double hmm = process.φ(34, 1.9);
    // out.println("HOGWASH");
    mprocess.trace = true;

    Vector uvec = process.φvec(12, 1.9, process.T.size() - 1);
    Vector mvec = mprocess.φvec(0, 12, 1.9, process.T.size() - 1);

    out.println("uvec=" + Arrays.toString(uvec.toDoubleArray()));
    out.println("mvec=" + Arrays.toString(mvec.toDoubleArray()));

    int tk = process.T.size() - 1;
    double alsoHmm = mprocess.φ(0, 34, 1.9, tk);

    // out.println("hmm=" + hmm + " = alsoHmm = " + alsoHmm);
    // assertEquals(hmm, alsoHmm, pow(10, -13));

    double woo = process.φdt(34);
    out.println("HOGWASH");
    mprocess.trace = true;

    double ass = process.φ(34, 1.9);
    double hat = mprocess.φ(0, 34, 1.9);
    out.println("ass=" + ass + " should equal hat=" + hat);
    assertEquals(ass, hat, pow(10, -13));

    double waa = mprocess.φdt(0, 34);
    out.println("woo=" + woo + " = waa=" + waa);

    assertEquals(woo, waa, pow(10, -13));

    double yay = process.φδ(27, 1.7);
    double woop = mprocess.φδ(0, 27, 1.7);
    assertEquals(yay, woop);
    out.println("yay=" + yay + " should equal woop=" + woop);

    double invuni = process.invΛ(1);
    double invmulti = mprocess.invΛ(0, 1);
    out.println("inv uni " + invuni);
    out.println("inv multi " + invmulti);

    out.println("tk=" + tk);

    assertEquals(invmulti, invuni, pow(10, -13));

    double stfu = process.Λ(process.T.size() - 1, invuni);
    assertEquals(1, stfu, pow(10, -13));
    // out.println( "stfu " + stfu );

    double wack = mprocess.Λ(0, process.T.size() - 1, invmulti);
    // out.println( "wack " + wack );
    assertEquals(1, wack, pow(10, -13));

    process.appendTime(process.T.getRightmostValue() + invuni);
    invuni = process.invΛ(1);
    stfu = process.Λ(process.T.size() - 1, invuni);
    assertEquals(1, stfu, pow(10, -13));
    process.appendTime(process.T.getRightmostValue() + invuni);

    double lastdt = 0;
    double dtd = 1;
    for (int i = 0; dtd > pow(10, -10); i++)
    {
      invuni = process.invΛ(1);
      dtd = invuni - lastdt;
      lastdt = invuni;
      stfu = process.Λ(process.T.size() - 1, invuni);
      assertEquals(1, stfu, pow(10, -13));
      process.appendTime(process.T.getRightmostValue() + invuni);
    }

    /**
     * converges to ergodic fixed spacing
     */
    assertEquals(2381.3936987561174, lastdt, pow(10, -10));
    out.println(process.T.size());
    out.println(process.T);
    out.println(process.T.diff());
    out.println(process.Λ());

    // // double hmm = process.φδ(27, 1.2);
    // out.println("hmm=" + hmm);
    // hmm = process.φδ(37, 1.2);
    // out.println("hmm=" + hmm);
    // out.println("params = " + process.getαβString());
    // XYChart chart = Plotter.chart("a", "φδ(t, y=1)", t -> process.φδ(t, 1),
    // -30,550, t -> t);
    // Pair<double[], double[]> sample = Plotter.sampleFunction(t->process.φδ(t, 0.5
    // ), 1000, -30, 550, t->t);
    // chart.addSeries("φδ(t, y=0.5)", sample.left, sample.right );
    // sample = Plotter.sampleFunction(t->process.φδ(t, 0.75 ), 1000, -30, 550,
    // t->t);
    // chart.addSeries("φδ(t, y=0.75)", sample.left, sample.right );
    // sample = Plotter.sampleFunction(t->process.φδ(t, 1.25 ), 1000, -30, 550,
    // t->t);
    // chart.addSeries("φδ(t, y=1.25)", sample.left, sample.right );
    // display(chart);
    // double x = 2;
    // while (!Double.isNaN(x))
    // {
    // Thread.sleep(1000);
    // }
    //
    // assertTrue(Double.isFinite(x));
    // out.println("x=" + x);
  }

}
