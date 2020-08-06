package com.cardinality;

import java.util.HashMap;
import java.util.Random;
import java.lang.Math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HyperBitBitTest {
  @DisplayName("It counts cardinality")
  @Test
  @Disabled /* Due to this test case is not stable */ 
  void testCardinality() {
    HyperBitBit hbb = new HyperBitBit();

    HashMap<String, Boolean> unique = new HashMap<String, Boolean>();
    String str;
    double expectedError = 0.1;

    while (unique.size() <= 10000000) {
      str = getRandomString();
      hbb.add(str);
      unique.put(str, true);
    }

    long exact = Long.valueOf(unique.size());
    long res = hbb.cardinality();
    double ratio = 100 * Math.abs(res - exact) / exact;

    String msg = "ratio: " + ratio + ", exact = " + exact + ", res = " + res;

    assertTrue(res > exact - (exact * expectedError), msg);
    assertTrue(res < exact + (exact * expectedError), msg);
  }

  String getRandomString() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 32;
    Random random = new Random();
 
    return random
            .ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
  }
}