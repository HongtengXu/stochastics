package stochastic.pointprocesses.selfexciting;

public enum Type
{
  ApproximatePowerlaw, ExtendedApproximatePowerlaw, MultivariateDiagonalExtendedApproximatePowerlaw, UnitRandomWalkMultivariateDiagonalExtendedApproximatePowerlaw, MultivariateFullExtendedApproximatePowerlaw;

  public String
         getFilenameExtension()
  {
    switch (this)
    {
    case ApproximatePowerlaw:
      return "apl";
    case ExtendedApproximatePowerlaw:
      return "eapl";
    case MultivariateDiagonalExtendedApproximatePowerlaw:
      return "meapl";
    case MultivariateFullExtendedApproximatePowerlaw:
      return "mfeapl";
    case UnitRandomWalkMultivariateDiagonalExtendedApproximatePowerlaw:
      return "urwmeapl";
    default:
      throw new UnsupportedOperationException("TODO: " + this);
    }
  }

  public AbstractSelfExcitingProcess
         instantiate()
  {
    return SelfExcitingProcessFactory.spawnNewProcess(this);
  }
}