package cs3500.pa01;

import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Represents a comparator that sorts files by their modified dates
 */
public class SortByModify implements Comparator<MdFile> {

  /**
   * Compares two file modified dates
   *
   * @param o1 the first MD-file to be compared.
   * @param o2 the second MD-file to be compared.
   * @return -1 or 1 depending on which modified date is earlier
   */
  @Override
  public int compare(MdFile o1, MdFile o2) {
    FileTime f1 = o1.getModified();
    FileTime f2 = o2.getModified();

    return f1.compareTo(f2);
  }
}
