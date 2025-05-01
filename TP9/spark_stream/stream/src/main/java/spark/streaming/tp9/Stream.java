package spark.streaming.tp9;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.Row;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class Stream {
        public static void main(String[] args) throws StreamingQueryException, TimeoutException {
                SparkSession spark = SparkSession
                                .builder()
                                .appName("NetworkWordCount")
                                .master("local[*]")
                                .getOrCreate();

                Dataset<String> lines = spark
                                .readStream()
                                .format("socket")
                                .option("host", "localhost")
                                .option("port", 9999)
                                .load()
                                .as(Encoders.STRING());

                Dataset<String> words = lines.flatMap(
                                (String x) -> Arrays.asList(x.split(" ")).iterator(),
                                Encoders.STRING())
                                .filter((String word) -> word != null && !word.trim().isEmpty());

                Dataset<org.apache.spark.sql.Row> wordCounts = words.groupBy("value").count();

                // Add logging
                System.out.println("Starting streaming query...");
                System.out.println("Waiting for input on localhost:9999...");

                StreamingQuery query = wordCounts.writeStream()
                                .outputMode("complete")
                                .format("console")
                                .option("truncate", "false")
                                .option("numRows", "100") // Show more rows
                                .trigger(Trigger.ProcessingTime("2 seconds"))
                                .start();

                try {
                        query.awaitTermination();
                } catch (StreamingQueryException e) {
                        System.err.println("Error in streaming query: " + e.getMessage());
                        e.printStackTrace();
                } catch (Exception e) {
                        System.err.println("Unexpected error: " + e.getMessage());
                        e.printStackTrace();
                }
        }
}
