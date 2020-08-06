package com.options;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsTest {
  @DisplayName("It parses incorrect options: p a1 a2 a3")
  @Test
  void testOfParsingIncorrectOptions1() {
    Options options = new Options();
    String consoleOptions = "p a1 a2 a3";

    assertThrows(IllegalArgumentException.class, () -> {
      options.parse(consoleOptions.split("\\s+"));
    });
  }

  @DisplayName("It parses incorrect options: - p a1 a2 a3")
  @Test
  void testOfParsingIncorrectOptions2() {
    Options options = new Options();
    String consoleOptions = "- p a1 a2 a3";

    assertThrows(IllegalArgumentException.class, () -> {
      options.parse(consoleOptions.split("\\s+"));
    });
  }

  @DisplayName("It parses incorrect options: -p1 a1 a2 a3 -- p2 b1 b2 b3")
  @Test
  void testOfParsingIncorrectOptions3() {
    Options options = new Options();
    String consoleOptions = "-p1 a1 a2 a3 -- p2 b1 b2 b3";

    assertThrows(IllegalArgumentException.class, () -> {
      options.parse(consoleOptions.split("\\s+"));
    });
  }

  @DisplayName("It parses correct options: -p1 a1 a2 a3 -p2 b1 b2 b3 --p3 c1 c2 c3 --p4 d1 d2 d3")
  @Test
  void testOfParsingCorrectOptions() {
    Options options = new Options();
    String consoleOptions = "";

    String optKey1 = "p1";
    String optValues1 = "a1 a2 a3";
    consoleOptions += String.format("-%s %s", optKey1, optValues1);

    String optKey2 = "p2";
    String optValues2 = "b1 b2 b3";
    consoleOptions += String.format(" -%s %s", optKey2, optValues2);

    String optKey3 = "p3";
    String optValues3 = "c1 c2 c3";
    consoleOptions += String.format(" --%s %s", optKey3, optValues3);

    String optKey4 = "p4";
    String optValues4 = "d1 d2 d3";
    consoleOptions += String.format(" --%s %s", optKey4, optValues4);

    assertEquals(
      Arrays.asList(optValues1.split("\\s+")),
      options.parse(consoleOptions.split("\\s+")).getAll(optKey1)
    );

    assertEquals(
      Arrays.asList(optValues2.split("\\s+")),
      options.parse(consoleOptions.split("\\s+")).getAll(optKey2)
    );

    assertEquals(
      Arrays.asList(optValues3.split("\\s+")),
      options.parse(consoleOptions.split("\\s+")).getAll(optKey3)
    );

    assertEquals(
      Arrays.asList(optValues4.split("\\s+")),
      options.parse(consoleOptions.split("\\s+")).getAll(optKey4)
    );
  }
}