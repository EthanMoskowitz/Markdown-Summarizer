package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for Sorter
 */
class SorterTest {

  /**
   * Tests the sorting of the MD-files
   */
  @Test
  void sortList() {
    File file = Path.of("src/test/resources/notes-root/arrays.md").toFile();
    File file2 = Path.of("src/test/resources/notes-root/vectors.md").toFile();
    File file3 = Path.of("src/test/resources/notes-root/notes-further/examples.md").toFile();

    BasicFileAttributes attr1 = null;
    BasicFileAttributes attr2 = null;
    BasicFileAttributes attr3 = null;
    try {
      attr1 = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
      attr2 = Files.readAttributes(file2.toPath(), BasicFileAttributes.class);
      attr3 = Files.readAttributes(file3.toPath(), BasicFileAttributes.class);
    } catch (Exception e) {
      fail();
    }

    MdFile mdfile1;
    mdfile1 = new MdFile(attr1.creationTime(), attr1.lastModifiedTime());
    MdFile mdfile2;
    mdfile2 = new MdFile(attr2.creationTime(), attr2.lastModifiedTime());
    MdFile mdfile3;
    mdfile3 = new MdFile(attr3.creationTime(), attr3.lastModifiedTime());

    ArrayList<MdFile> unsortedNames = new ArrayList<>();
    try {
      unsortedNames.add(new MdFile(file3));
      unsortedNames.add(new MdFile(file2));
      unsortedNames.add(new MdFile(file));
    } catch (Exception e) {
      fail();
    }

    final Sorter sortName = new Sorter("filename", unsortedNames);
    ArrayList<MdFile> sortedName = sortName.sortList();
    assertTrue(sortedName.get(0).getFile().getName().compareTo(
        sortedName.get(1).getFile().getName()) < 0);
    assertTrue(sortedName.get(1).getFile().getName().compareTo(
        sortedName.get(2).getFile().getName()) < 0);

    ArrayList<MdFile> unsorted = new ArrayList<>();
    unsorted.add(mdfile3);
    unsorted.add(mdfile2);
    unsorted.add(mdfile1);

    final Sorter sortCreate = new Sorter("created", unsorted);
    ArrayList<MdFile> sortedCreate = sortCreate.sortList();
    assertTrue(sortedCreate.get(0).getCreated().compareTo(
        sortedCreate.get(1).getCreated()) <= 0);
    assertTrue(sortedCreate.get(1).getCreated().compareTo(
        sortedCreate.get(2).getCreated()) <= 0);

    final Sorter sortModify = new Sorter("modified", unsorted);
    ArrayList<MdFile> sortedModify = sortModify.sortList();
    assertTrue(sortedModify.get(0).getModified().compareTo(
        sortedModify.get(1).getModified()) <= 0);
    assertTrue(sortedModify.get(1).getModified().compareTo(
        sortedModify.get(2).getModified()) <= 0);

    Sorter failSort = new Sorter("failure", unsorted);
    assertThrows(IllegalArgumentException.class,
        () -> failSort.sortList());
  }
}