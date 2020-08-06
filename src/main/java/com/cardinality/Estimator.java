package com.cardinality;

import com.options.Options;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Estimator {
  public static void main(String args[]) {
    String filePath = (new Options()).parse(args).get("file-path");

    System.out.println("File: " + filePath);

    HyperBitBit hbb = new HyperBitBit();

    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();

      long start = System.currentTimeMillis();
      while (line != null) {
        hbb.add(line);
        line = reader.readLine();
      }

      System.out.println(
        "time: " + (System.currentTimeMillis() - start) +
        ", cardinality: " + hbb.cardinality()
      );

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}