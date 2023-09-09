package cs3500.pa01;

import java.util.ArrayList;

/**
 * Represents a class that sorts a list of MD-files
 */
public class Sorter {
  private String flag;
  private ArrayList<MdFile> list;

  /**
   * Instantiates a Sorter class
   *
   * @param f flag to determine which way to sort the files
   * @param l list of MD-files
   */
  Sorter(String f, ArrayList<MdFile> l) {
    flag = f;
    list = l;
  }

  /**
   * Sorts the files depending on which flag is given
   *
   * @return the sorted list of MD-files
   */
  public ArrayList<MdFile> sortList() {
    switch (flag) {
      case "filename" -> {
        SortByName nameS = new SortByName();
        list.sort(nameS);
      }
      case "created" -> {
        SortByCreate createS = new SortByCreate();
        list.sort(createS);
      }
      case "modified" -> {
        SortByModify modifyS = new SortByModify();
        list.sort(modifyS);
      }
      default -> throw new IllegalArgumentException("Invalid Flag");
    }

    return list;
  }

}
