package stochastic.markovchains;

import java.nio.ByteBuffer;
import java.util.List;

import fastmath.AbstractMatrix;
import fastmath.Pair;
import fastmath.SquareDoubleColMatrix;
import fastmath.Vector;

public class TransitionProbabilityMatrix extends SquareDoubleColMatrix
{

  @Override
  public double add( int i, int j, double x )
  {
    int r = Math.max( i, j );
    int prevr = numRows;
    if ( r >= prevr )
    {
      resize( r + 1, r + 1 );
    }
    return super.add( i, j, x );
  }

  public TransitionProbabilityMatrix()
  {
  }

  public TransitionProbabilityMatrix(AbstractMatrix x)
  {
    super( x );
  }

  public TransitionProbabilityMatrix(ByteBuffer buffer, int baseOffset, int dim)
  {
    super( buffer, baseOffset, dim );
  }

  public TransitionProbabilityMatrix(ByteBuffer buffer, int dim)
  {
    super( buffer, dim );
  }

  public TransitionProbabilityMatrix(double[][] arr)
  {
    super( arr );
  }

  public TransitionProbabilityMatrix(String name)
  {
    super( 0, name );

  }

  public TransitionProbabilityMatrix(int dim, String name)
  {
    super( dim, name );

  }

  public TransitionProbabilityMatrix(int dim)
  {
    super( dim );
  }

  public TransitionProbabilityMatrix(List<Pair<Double, Double>> twoColMatrix)
  {
    super( twoColMatrix );
  }

  public TransitionProbabilityMatrix(ByteBuffer buffer, int baseOffset, int numRows, int numCols)
  {
    super( buffer, baseOffset, numRows, numCols );
  }

  @Override
  public TransitionProbabilityMatrix copy( boolean reuseBuffer )
  {
    return reuseBuffer ? new TransitionProbabilityMatrix( buffer, getBaseOffset(), numRows, numCols ) : new TransitionProbabilityMatrix( this );
  }

  /**
   * Normalize the matrix so the row sums are equal to 1 (conservation of
   * probability)
   * 
   * @return this
   */
  public Vector normalize()
  {
    Vector rowSums = new Vector( getRowCount() );
    
    rows().forEach( row -> {
      double measure = row.sum();
      rowSums.set( row.getIndex(), measure );
      if ( measure > 0 )
      {
        row.divide( measure );
      }
    } );
    return rowSums;
  }

}
