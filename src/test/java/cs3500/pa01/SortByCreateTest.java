package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for SortByCreate
 */
class SortByCreateTest {

  /**
   * Tests compare method
   */
  @Test
  void compare() {
    SortByCreate sort;
    sort = new SortByCreate();

    FileTime create1 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    FileTime modify1 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    FileTime create2 = FileTime.from(Instant.parse("2023-05-12T12:00:01Z"));
    FileTime modify2 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));
    FileTime create3 = FileTime.from(Instant.parse("2023-05-12T13:00:00Z"));
    FileTime modify3 = FileTime.from(Instant.parse("2023-05-12T12:00:00Z"));

    MdFile file = new MdFile(create1, modify1);
    MdFile file2 = new MdFile(create2, modify2);
    MdFile file3 = new MdFile(create3, modify3);

    assertEquals(-1, sort.compare(file, file2));
    assertEquals(1, sort.compare(file2, file));

    assertEquals(-1, sort.compare(file2, file3));
    assertEquals(1, sort.compare(file3, file2));

    assertEquals(-1, sort.compare(file, file3));
    assertEquals(1, sort.compare(file3, file));

  }
}