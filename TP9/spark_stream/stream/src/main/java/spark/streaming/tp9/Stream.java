package spark.streaming.tp9;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;

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
                Encoders.STRING());

        Dataset<org.apache.spark.sql.Row> wordCounts = words.groupBy("value").count();

        StreamingQuery query = wordCounts.writeStream()
                .outputMode("complete")
                .format("text") // حفظ النتائج كنص
                .option("path", "hdfs://hadoop-master:9000/user/root/result")
                .option("checkpointLocation", "hdfs://hadoop-master:9000/user/root/result/checkpoint")
                .trigger(Trigger.ProcessingTime("1 second"))
                .start();

        query.awaitTermination();
    }
}
