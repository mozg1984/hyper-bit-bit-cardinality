package com.cardinality;

public class HyperBitBit {
  int lgN;
  long sketch;
  long sketch2;

  public HyperBitBit() {
    lgN = 5;
    sketch = 0;
    sketch2 = 0;
  }

  public void add(String string) {
    final long x = MurmurHash.hash64(string);
    long k = (x << 58) >> 58;
    // Calculate the position of the leftmost 1-bit.
    int r = Long.numberOfLeadingZeros(x >> 6) - 6;

    if (r > lgN) {
      sketch = sketch | 1L << k;
    }

    if (r > lgN + 1) {
      sketch2 = sketch2 | 1L << k;
    }

    if (Long.bitCount(sketch) > 31) {
      sketch = sketch2;
      sketch2 = 0;
      ++lgN;
    }
  }

  public long cardinality() {
    double exponent = lgN + 5.4 + Long.bitCount(sketch) / 32.0;
    return (long) Math.pow(2, exponent);
  }
}