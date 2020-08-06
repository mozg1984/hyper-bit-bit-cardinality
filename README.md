# HyperBitBit cardinality

Console utility for an approximate (estimation) calculation of unique lines in a given large file based on HyperBitBit algorithm. 

### Installation

Console utility requires [Apache Maven](https://maven.apache.org/) v3.6.0+ and Java v11 to run.

```sh
$ mvn clean install
```

Once done, run the utility by passing the path to the file through the key `-file-path`:

```sh
$ java -cp target/HyperBitBitCardinality-1.0.0.jar com.cardinality.Estimator -file-path <Your_file_with_ip_addresses>
```