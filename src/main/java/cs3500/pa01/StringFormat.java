package cs3500.pa01;

/**
 * Represents an object that formats a string to be like a MarkDown file
 */
public class StringFormat {
  String text;

  /**
   * Instantiates a StringFormat
   *
   * @param s string of file content
   */
  StringFormat(String s) {
    text = s;
  }

  /**
   * Formats the string into Markdown format
   *
   * @return formatted text
   */
  public String formatString() {
    StringBuilder builder = new StringBuilder();
    boolean header = false;
    boolean brackets = false;

    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      Character next = getNext(i);
      Character prev = getPrev(i);
      if (c.equals('#')) {
        header = true;
        if (!prev.equals(' ') && !prev.equals('#')) {
          builder.append('\n');
        }
      }
      if (header && c.equals('\n')) {
        builder.append(c);
        header = false;
      }
      if (c.equals('[') && next.equals('[')) {
        builder.append('-');
        builder.append(' ');
        brackets = true;
      }
      if (brackets && c.equals(']') && next.equals(']')) {
        builder.append('\n');
        brackets = false;
      }
      if (header || brackets && !c.equals('\n') && !(c.equals('[') && (prev.equals('[')
          || next.equals('[')))) {
        builder.append(c);
      }
    }
    return builder.toString();
  }

  /**
   * Gets previous character from text
   *
   * @param i index of current character
   * @return previous character
   */
  private char getPrev(int i) {
    if (i != 0) {
      return text.charAt(i - 1);
    } else {
      return ' ';
    }
  }

  /**
   * Gets next character
   *
   * @param i index of current character
   * @return next character
   */
  private char getNext(int i) {
    if (i != text.length() - 1) {
      return text.charAt(i + 1);
    } else {
      return ' ';
    }
  }
}
