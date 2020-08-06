package com.options;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Options {
  private Map<String, List<String>> storage = null;

  public Options() {
    storage = new HashMap<>();
  }

  public Options parse(final String options[]) throws IllegalArgumentException {
    String optionKey = "";
    List<String> optionValues = null;

    storage.clear();

    for (final String option : options) {
      // -opt
      if (option.charAt(0) == '-') {
        if (option.length() < 2) {
          throw new IllegalArgumentException("Not a valid option: " + option);
        }

        optionKey = option.substring(1);

        // --opt
        if (optionKey.charAt(0) == '-') {
          if (option.length() < 3) {
            throw new IllegalArgumentException("Not a valid option: " + option);
          }

          optionKey = optionKey.substring(1);
        }

        optionValues = new ArrayList<>();
        storage.put(optionKey, optionValues);
      } else if (optionValues != null) {
        optionValues.add(option);
      } else {
        throw new IllegalArgumentException("Illegal option usage: " + option);
      }
    }

    return this;
  }

  public List<String> getAll(String optKey) {
    return storage.get(optKey);
  }

  public String get(String optKey) {
    List<String> optValues = storage.get(optKey);
    return optValues != null ? optValues.get(0) : "";
  }
}