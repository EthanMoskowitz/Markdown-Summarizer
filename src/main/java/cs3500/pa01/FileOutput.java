package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Represents an object that writes text into a file
 */
public class FileOutput {

  /**
   * Writes the given string into the given file
   *
   * @param outputFile file that is written into
   * @param guide text that goes into the file
   * @throws FileNotFoundException if given file does not exist
   */
  public void writeFile(File outputFile, String guide) throws FileNotFoundException {
    FileWriter writer;

    try {
      writer = new FileWriter(outputFile);

      for (int i = 0; i < guide.length(); i++) {
        writer.write(guide.charAt(i));
      }

      writer.close();

    } catch (Exception e) {
      throw new FileNotFoundException("Invalid Directory");
    }
  }
}
