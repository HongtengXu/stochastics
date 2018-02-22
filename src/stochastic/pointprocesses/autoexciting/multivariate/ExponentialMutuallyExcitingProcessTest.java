package stochastic.pointprocesses.autoexciting.multivariate;

import static java.lang.Math.pow;
import static java.lang.System.out;

import fastmath.DoubleMatrix;
import fastmath.IntVector;
import fastmath.Vector;
import junit.framework.TestCase;
import stochastic.pointprocesses.autoexciting.multivariate.diagonal.DiagonalExtendedApproximatePowerlawMututallyExcitingProcess;
import stochastic.pointprocesses.selfexciting.ExtendedApproximatePowerlawSelfExcitingProcess;
import stochastic.pointprocesses.selfexciting.ExtendedExponentialPowerlawSelfExcitingProcessTest;

public class ExponentialMutuallyExcitingProcessTest extends TestCase
{

  public void
         testMeanRecurrentTime()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    double mrt = process.meanRecurrenceTime();

    // Vector points = process.simulate(5, 20000);
    // out.println("pdiff = " + points.diff().mean());
    AbstractMutuallyExcitingProcess multidimProcess = new DiagonalExtendedApproximatePowerlawMututallyExcitingProcess(1);
    multidimProcess.assignParameters(process.getParameters().toDoubleArray());

    double multiMrt = multidimProcess.meanRecurrenceTime(0);

    assertEquals(mrt, multiMrt);

    // inferredProcess.T = points;
    // inferredProcess.estimateParameters(10);
    // out.println("simulated " + process);
    // out.println("inferred " + inferredProcess);
    // assertTrue(abs(process.meanRecurrenceTime() -
    // inferredProcess.meanRecurrenceTime()) < 10);
  }

  public void
         testTotalΛ() throws InterruptedException
  {
    ExtendedApproximatePowerlawSelfExcitingProcess process = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();
    process.T = new Vector(4);
    process.T.set(0, 0);
    process.T.set(1, 19);
    process.T.set(2, 24);
    process.T.set(3, 27);

    double a = process.Λ().sum();
    double b = process.totalΛ();
    assertEquals(a, b, 1E-15);

    FullyCovariantExtendedMutuallyExcitingExponentialPowerlawApproximationProcess mprocess =
                                                                                           new FullyCovariantExtendedMutuallyExcitingExponentialPowerlawApproximationProcess(1);
    mprocess.τ.set(0, 0, process.τ);
    mprocess.ε.set(0, 0, process.ε);
    mprocess.η.set(0, 0, process.η);
    mprocess.b.set(0, 0, process.b);

    // mprocess.assignParameters(process.getParameters().toDoubleArray());
    mprocess.T = process.T;
    mprocess.K = new IntVector(process.T.size());

    out.println("process=" + process);
    out.println("mprocess=" + mprocess);

    Vector comp = mprocess.Λ(0);
    out.println("comp=" + comp);
    double c = mprocess.totalΛ();
    assertEquals(a, c, 1E-15);

    assertTrue(process.Λ().equals(mprocess.Λ(0), pow(10, -13)));

  }

  public void
         testN()
  {
    ExponentialMutuallyExcitingProcess process = FullyCovariantExtendedMututallyExcitingExponentialPowerlawApproximationProcessTest.constructLongerProcess();

    assertEquals(0, process.Nopen(0, 24));
    assertEquals(1, process.Nopen(0, 26));
    assertEquals(1, process.Nopen(0, 91));
    assertEquals(2, process.Nopen(0, 92.01));
    assertEquals(0, process.Nopen(1, 91));
    assertEquals(4, process.Nopen(1, 168));
  }

  public void
         testA()
  {
    ExtendedApproximatePowerlawSelfExcitingProcess uniprocess = ExtendedExponentialPowerlawSelfExcitingProcessTest.constructProcess();

    FullyCovariantExtendedMutuallyExcitingExponentialPowerlawApproximationProcess process =
                                                                                          FullyCovariantExtendedMututallyExcitingExponentialPowerlawApproximationProcessTest.constructLongerProcess();
    process.ε.diag().set(0.0, 0.02);

    out.println(uniprocess);
    out.println(uniprocess.getαVector().toString());
    out.println(uniprocess.getβVector().toString());
    out.println(uniprocess.getαβVector());

    uniprocess.ε = 0.02;
    out.println();

    out.println(uniprocess);
    out.println(uniprocess.getαVector().toString());
    out.println(uniprocess.getβVector().toString());
    out.println(uniprocess.getαβVector());
    out.println();

    for (Vector typeTimes : process.getTimeSubsets().left)
    {
      out.println(typeTimes);

    }

    for (int j = 0; j < process.order(); j++)
    {
      out.println();

      out.println(process.getαMatrix(j).toString());
      out.println(process.getβMatrix(j).toString());
      DoubleMatrix αβmatrix = process.getαβMatrix(j);
      out.println(αβmatrix.toString());

      for (int m = 0; m < process.dim; m++)
      {
        int i = process.N(m) - 1;
        Vector row = new Vector(process.dim).setName("A[" + j + "," + m + ",n," + i + "]=");
        for (int n = 0; n < process.dim; n++)
        {
          row.set(n, process.Asum(j, m, n, i));
        }
        out.println(row);
      }
    }

  }

}
