package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 */
public class Driver {
  private static final String fileType = ".md";

  /**
   * Project entry point
   *
   * @param args should be three arguments: a valid path, a valid string,
   *             and another valid path
   */
  public static void main(String[] args) {
    checkInputs(args);
    final Path input = Path.of(args[0]);
    final String flag = args[1];
    final Path output = Path.of(args[2]);
    continueMain(input, flag, output);
  }

  /**
   * Checks if the given inputs are valid for the program
   *
   * @param args given input strings
   */
  private static void checkInputs(String[] args) {
    // makes sure only three inputs are given
    if (args.length != 3) {
      throw new IllegalArgumentException("Only 3 Inputs Allowed");
    }

    // checks if second input is a valid flag
    String tempFlag = args[1];
    if (!tempFlag.equals("filename") && !tempFlag.equals("created")
        && !tempFlag.equals("modified")) {
      throw new IllegalArgumentException("Invalid Flag");
    }

    // checks if third input is a valid file in a valid directory
    Path p = Path.of(args[2]);
    File temp = p.toFile();
    if (!temp.toString().endsWith(fileType)) {
      throw new IllegalArgumentException("Invalid File");
    }

  }

  /**
   * Carries out the program
   *
   * @param input input path to directory
   * @param flag which way to sort the files
   * @param output where the study guide will be created
   */
  private static void continueMain(Path input, String flag, Path output) {
    // visits files and makes list of markdown files
    MarkDownFile mark = new MarkDownFile(fileType);
    try {
      Files.walkFileTree(input, mark);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ArrayList<MdFile> fileList = mark.getList();

    // sorts files depending on given flag
    Sorter sort = new Sorter(flag, fileList);
    fileList = sort.sortList();

    // turns MdFiles back into list of normal files
    ArrayList<File> files = new ArrayList<>();
    for (MdFile f : fileList) {
      files.add(f.getFile());
    }

    // converts file content to string
    FilesToString fileString = new FilesToString(files);
    String guide = fileString.convert();

    // formats string into markdown file format
    StringFormat formatter = new StringFormat(guide);
    guide = formatter.formatString();

    // writes the text into the new file
    File outputFile = new File(output.toString());
    FileOutput outFile = new FileOutput();
    try {
      outFile.writeFile(outputFile, guide);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}