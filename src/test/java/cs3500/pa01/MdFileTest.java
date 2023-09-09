package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents test for MdFile
 */
class MdFileTest {
  File tempFile;
  MdFile file;
  FileTime create;
  FileTime modify;
  MdFile attrFile;

  /**
   * Instantiates variables for testing
   */
  @BeforeEach
  void setup() {
    tempFile = Path.of("src/test/resources/notes-root/arrays.md").toFile();
    try {
      file = new MdFile(tempFile);
    } catch (Exception e) {
      fail();
    }
    create = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    modify = FileTime.from(Instant.parse("2023-05-12T12:00:01Z"));
    attrFile = new MdFile(create, modify);
  }

  /**
   * Tests the getCreated method
   */
  @Test
  void getCreated() {
    assertEquals(create, attrFile.getCreated());
  }

  /**
   * Tests the getModified method
   */
  @Test
  void getModified() {
    assertEquals(modify, attrFile.getModified());
  }

  /**
   * Tests the getFile method
   */
  @Test
  void getFile() {
    assertEquals(tempFile, file.getFile());
  }
}