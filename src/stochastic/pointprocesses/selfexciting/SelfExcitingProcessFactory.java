package stochastic.pointprocesses.selfexciting;

public class SelfExcitingProcessFactory
{
  public static AbstractSelfExcitingProcess
         spawnNewProcess(Type type)
  {
    switch (type)
    {
    case ApproximatePowerlaw:
      return new ApproximatePowerlawSelfExcitingProcess();
    case ExtendedApproximatePowerlaw:
      return new ExtendedApproximatePowerlawSelfExcitingProcess();
    default:
      throw new UnsupportedOperationException("TODO: " + type);
    }
  }
}
