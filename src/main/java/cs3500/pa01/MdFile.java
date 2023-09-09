package cs3500.pa01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Represents a MarkDown File
 */
public class MdFile {
  File file;
  FileTime createdAt;
  FileTime modifiedAt;

  /**
   * Instantiates a MarkDown File for passing in a real file
   *
   * @param f real file
   * @throws IOException for if file is not real
   */
  MdFile(File f) throws IOException {
    file = f;
    BasicFileAttributes attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
    createdAt = attr.creationTime();
    modifiedAt = attr.lastModifiedTime();
  }

  /**
   * Instantiates an example MarkDown File for tests
   *
   * @param c creation time
   * @param m modified time
   */
  MdFile(FileTime c, FileTime m) {
    createdAt = c;
    modifiedAt = m;
  }

  /**
   * Gets the created time
   *
   * @return created time as a FileTime
   */
  public FileTime getCreated() {
    return createdAt;
  }

  /**
   * Gets the modified time
   *
   * @return modified time as a FileTime
   */
  public FileTime getModified() {
    return modifiedAt;
  }

  /**
   * Gets the File
   *
   * @return the file
   */
  public File getFile() {
    return file;
  }

}
