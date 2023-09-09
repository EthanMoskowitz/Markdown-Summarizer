package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for SortByName
 */
class SortByNameTest {

  /**
   * Tests compare method
   */
  @Test
  void compare() {
    SortByName sort = new SortByName();
    MdFile file = null;
    MdFile file2 = null;
    MdFile file3 = null;
    try {
      file = new MdFile(Path.of("src/test/resources/notes-root/arrays.md").toFile());
      file2 = new MdFile(Path.of("src/test/resources/notes-root/vectors.md").toFile());
      file3 = new MdFile(
          Path.of("src/test/resources/notes-root/notes-further/examples.md").toFile());
    } catch (Exception e) {
      fail();
    }

    assertEquals(-21, sort.compare(file, file2));
    assertEquals(21, sort.compare(file2, file));

    assertEquals(17, sort.compare(file2, file3));
    assertEquals(-17, sort.compare(file3, file2));

    assertEquals(-4, sort.compare(file, file3));
    assertEquals(4, sort.compare(file3, file));
  }
}