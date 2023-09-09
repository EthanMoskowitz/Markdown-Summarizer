package cs3500.pa01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an object that converts a list of file's content to
 * a string
 */
public class FilesToString {
  private ArrayList<File> list;

  /**
   * Instantiates a FileToString
   *
   * @param l list of MD-files
   */
  FilesToString(ArrayList<File> l) {
    list = l;
  }

  /**
   * Converts the list of MD-files into a string
   *
   * @return the string of the content of all the files
   */
  public String convert() {
    StringBuilder builder = new StringBuilder();
    Scanner scanner;

    for (File f : list) {
      try {
        scanner = new Scanner(new FileInputStream(f));
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }

      StringBuilder content = new StringBuilder();
      while (scanner.hasNextLine()) {
        content.append(scanner.nextLine()).append("\n");
      }
      content.append("\n");
      builder.append(content);
    }

    return builder.toString();
  }
}
