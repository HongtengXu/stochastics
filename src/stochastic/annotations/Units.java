package stochastic.annotations;

import java.util.concurrent.TimeUnit;

/**
 * TODO: refactor this to use the new @m annotation introduced in Java <a href=
 * "http://types.cs.washington.edu/checker-framework/current/checker-framework-manual.html#units-checker"
 * >http://types.cs.washington.edu/checker-framework/current/checker-framework-
 * manual.html#units-checker</a>
 * 
 * @author crow
 *
 */
public @interface Units
{
  TimeUnit time();
}
