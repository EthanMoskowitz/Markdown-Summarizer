package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for FilesToString
 */
class FilesToStringTest {

  /**
   * Tests the conversion of files to a string
   */
  @Test
  void convert() {
    File file = Path.of("src/test/resources/notes-root/notes-further/examples.md").toFile();
    ArrayList<File> files = new ArrayList<>();
    try {
      files.add(file);
    } catch (Exception e) {
      fail();
    }
    FilesToString fileString = new FilesToString(files);
    String s = """
        # Example md file for testing

        - Example text
        - [[More Examples]]

        ## More Example

        """;
    assertEquals(s, fileString.convert());

    ArrayList<File> failFiles = new ArrayList<>();
    File failure = Path.of("src/test/resources/notes-root/notes").toFile();
    failFiles.add(failure);
    FilesToString failString = new FilesToString(failFiles);
    assertThrows(RuntimeException.class,
        () -> failString.convert());
  }
}