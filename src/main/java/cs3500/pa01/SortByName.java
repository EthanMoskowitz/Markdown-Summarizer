package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a comparator that sorts files by their names
 */
public class SortByName implements Comparator<MdFile> {

  /**
   * Compares two file names
   *
   * @param o1 the first MD-file to be compared.
   * @param o2 the second MD-file to be compared.
   * @return negative or positive number depending on which filename is first
   */
  @Override
  public int compare(MdFile o1, MdFile o2) {
    return o1.getFile().getName().compareTo(o2.getFile().getName());
  }
}
