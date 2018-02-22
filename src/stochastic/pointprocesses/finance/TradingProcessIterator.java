package stochastic.pointprocesses.finance;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fastmath.Pair;

public class TradingProcessIterator implements Iterator<TradingProcess>, Iterable<TradingProcess>
{
  private Iterator<File> markedPointProcesses;
  private HashSet<String> symbols = new HashSet<>();

  public TradingProcessIterator(File directory, String... symbolList) throws IOException
  {
    symbols.addAll(Arrays.asList(symbolList));
    Stream<File> mppFiles = Files.find(Paths.get(directory.toURI()), 2, (p, bfa) -> {
      String filename = p.getFileName().toString();
      String[] tokens = filename.split("-");
      boolean matchesSymbol = tokens.length > 0 && symbols.contains(tokens[0]);
      int tokenCount = tokens.length;
      boolean isDateSubdir = (bfa.isDirectory() || bfa.isSymbolicLink()) && tokenCount == 3;
      boolean isMPPfile = filename.endsWith(".mpp");
      boolean isMPPArchive = matchesSymbol && isMPPfile;
      return isMPPArchive || isDateSubdir;
    }, FileVisitOption.FOLLOW_LINKS).sorted().map(path -> path.toFile());
    List<File> files = mppFiles.collect(Collectors.toList());
    List<File> mppList = files.stream().filter(file -> file.isFile()).collect(Collectors.toList());
    markedPointProcesses = mppList.iterator();
  }

  public TradingProcessIterator(String dir, String... symbolList) throws IOException
  {
    this(new File(dir), symbolList);
  }

  public static List<TradingProcess> ls(String... symbols) throws IOException
  {
    return new TradingProcessIterator("/data", symbols).stream().collect(Collectors.toList());
  }

  public Stream<TradingProcess> stream()
  {
    return StreamSupport.stream(spliterator(), false);
  }

  @Override
  public Iterator<TradingProcess> iterator()
  {
    return this;
  }

  @Override
  public boolean hasNext()
  {
    return markedPointProcesses.hasNext();
  }

  @Override
  public TradingProcess next()
  {
    Pair<RandomAccessFile, RandomAccessFile> mpp = new Pair<>();
    File mppFile;
    String symbol;
    try
    {
      mppFile = markedPointProcesses.next();
      symbol = mppFile.getName().split("-")[0];
      mpp.left = new RandomAccessFile(mppFile, "r");
      mpp.right = new RandomAccessFile(new File(mppFile.getAbsolutePath() + ".idx"), "r");
    }
    catch (Exception e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
    try
    {
      return new TradingProcess(mpp, mppFile, symbol);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @Override
  public void remove()
  {
    throw new UnsupportedOperationException();
  }

}
