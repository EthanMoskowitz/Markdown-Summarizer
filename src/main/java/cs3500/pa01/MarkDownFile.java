package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Represents a MarkDownFile which implements FileVisitor
 */
public class MarkDownFile implements FileVisitor<Path> {
  private ArrayList<MdFile> files;
  private final String fileType;
  private boolean isVisited;

  /**
   * Instantiates a MarkDownFIle
   *
   * @param f the type of file that we want to process
   */
  MarkDownFile(String f) {
    files = new ArrayList<>();
    fileType = f;
    isVisited = false;
  }

  /**
   * Invoked for a directory before entries are visited in the directory
   *
   * @param dir a reference to the directory
   * @param attrs the directory's basic attributes
   *
   * @return CONTINUE to visit entries in the directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * Invoked for a file in a directory
   *
   * @param file a reference to the file
   * @param attrs the file's basic attributes
   *
   * @return CONTINUE to move on to the next entry in the directory
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    String name = file.getFileName().toString();
    if (name.endsWith(fileType)) {
      MdFile md = new MdFile(file.toFile());
      files.add(md);
    }
    return CONTINUE;
  }

  /**
   * Invoked for a file that could not be visited
   *
   * @param file a reference to the file
   * @param exc the I/O exception that prevented the file from being visited
   *
   * @return CONTINUE to move on to next entry in directory
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    MarkDownFile.handleException(exc);
    return CONTINUE;
  }

  /**
   * Throws the exception for visiting failed file
   *
   * @param exc exception to throw
   * @throws IOException exception for visitFileFailed
   */
  private static void handleException(IOException exc) throws IOException {
    throw new IOException(exc);
  }

  /**
   * Invoked for a directory after entries in the directory,
   * and all of their descendants, have been visited.
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *                        an error; otherwise the I/O exception that caused the iteration
   *                        of the directory to complete prematurely
   *
   * @return CONTINUE to move on to next directory
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    isVisited = true;
    return CONTINUE;
  }

  /**
   * Gets the list of MD-files if file visitor has been used
   *
   * @return list of MD-files
   */
  public ArrayList<MdFile> getList() {
    if (!isVisited) {
      throw new IllegalStateException("File Visitor has not been used yet");
    }
    return files;
  }
}
