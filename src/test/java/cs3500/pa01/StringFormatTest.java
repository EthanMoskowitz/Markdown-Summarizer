package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for StringFormat
 */
class StringFormatTest {

  /**
   * Tests if the string is properly formatted
   */
  @Test
  void formatString() {
    String string = """
        # Example md file for testing

        - Example text
        - [[More Examples]]
        - [[Brackets Example[] Here]]

        ## More Example
        - [[Line Break Example Here
         Still Going]]

        """;
    StringFormat format = new StringFormat(string);

    String toMd = """
        # Example md file for testing
        - More Examples
        - Brackets Example[] Here
        
        ## More Example
        - Line Break Example Here Still Going
        """;

    assertEquals(toMd, format.formatString());
  }

}