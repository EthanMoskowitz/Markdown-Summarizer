package cs3500.pa01;


import static java.nio.file.FileVisitResult.CONTINUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for MarkDownFile
 */
class MarkDownFileTest {

  MarkDownFile mark;
  Path path;
  BasicFileAttributes attr;
  IOException exc;
  ArrayList<MdFile> files;

  /**
   * Initializes a MarkDownFile and parameters involving it
   */
  @BeforeEach
  void setup() {
    mark = new MarkDownFile(".md");
    path = Path.of("src/test/resources/notes-root");
    try {
      attr = Files.readAttributes(path, BasicFileAttributes.class);
    } catch (Exception e) {
      fail();
    }
    exc = new IOException("Wrong File");
    files = new ArrayList<>();
  }

  /**
   * Tests if file visitor will continue after it comes
   * across a new directory
   */
  @Test
  void preVisitDirectory() {
    assertEquals(CONTINUE, mark.preVisitDirectory(path, attr));
  }

  /**
   * Tests if it will continue after encountering a file and
   * that it will add the file to the list if it is a markdown file
   */
  @Test
  void visitFile() {
    try {
      assertEquals(CONTINUE, mark.visitFile(path, attr));
    } catch (Exception e) {
      fail();
    }

    path = Path.of("src/test/resources/notes-root/arrays.md");
    MdFile file = null;
    try {
      attr = Files.readAttributes(path, BasicFileAttributes.class);
      file = new MdFile(path.toFile());
      mark.visitFile(path, attr);
    } catch (Exception e) {
      fail();
    }
    files.add(file);
    mark.postVisitDirectory(path, exc);
    assertEquals(files.get(0).getFile(), mark.getList().get(0).getFile());

    path = Path.of("src/test/resources/notes-root/nonmd.pdf");
    try {
      attr = Files.readAttributes(path, BasicFileAttributes.class);
      mark.visitFile(path, attr);
      files.add(new MdFile(path.toFile()));
    } catch (Exception e) {
      fail();
    }
    assertNotEquals(files, mark.getList());
  }

  /**
   * Tests if it will continue after failing to visit a file
   */
  @Test
  void visitFileFailed() {
    assertThrows(IOException.class,
        () -> mark.visitFileFailed(path, exc));
  }

  /**
   * Tests if it will continue after it's finished searching a
   * whole directory
   */
  @Test
  void postVisitDirectory() {
    assertEquals(CONTINUE, mark.postVisitDirectory(path, exc));
  }

  /**
   * Tests if it will get the list of markdown files if file visitor
   * has run, otherwise test if it will throw an exception
   */
  @Test
  void getList() {
    assertThrows(IllegalStateException.class,
        () -> mark.getList());
    MdFile file = null;
    MdFile file2 = null;
    MdFile file3 = null;
    try {
      Files.walkFileTree(path, mark);
      file = new MdFile(Path.of("src/test/resources/notes-root/arrays.md").toFile());
      file2 = new MdFile(Path.of("src/test/resources/notes-root/vectors.md").toFile());
      file3 = new MdFile(Path.of(
          "src/test/resources/notes-root/notes-further/examples.md").toFile());
    } catch (Exception e) {
      fail();
    }
    files.add(file3);
    files.add(file);
    files.add(file2);
    ArrayList<MdFile> marksList = mark.getList();
    ArrayList<File> list = new ArrayList<>();
    for (MdFile f : marksList) {
      list.add(f.getFile());
    }
    for (MdFile f : files) {
      assertTrue(list.contains(f.getFile()));
    }
  }
}