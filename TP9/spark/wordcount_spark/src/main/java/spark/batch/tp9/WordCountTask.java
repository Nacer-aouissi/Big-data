package spark.batch.tp9;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;
import java.util.Arrays;
import static com.google.common.base.Preconditions.checkArgument;

public class WordCountTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordCountTask.class);

    public static void main(String[] args) {
        checkArgument(args.length > 1, "Please provide the path of input file and output dir as parameters.");
        new WordCountTask().run(args[0], args[1]);
    }

    public void run(String inputFilePath, String outputDir) {
        // Spark Configuration
        SparkConf conf = new SparkConf()
                .setAppName(WordCountTask.class.getName());
        // .setMaster("local[*]"); // ➜ لا تضفها عند تشغيل الكود على الكلاستر

        // Spark Context
        JavaSparkContext sc = new JavaSparkContext(conf);

        // قراءة الملف من HDFS
        JavaRDD<String> textFile = sc.textFile(inputFilePath);

        // تقسيم النصوص إلى كلمات
        JavaPairRDD<String, Integer> counts = textFile
                .flatMap(s -> Arrays.asList(s.split("\\s+")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum);

        // حفظ النتائج في HDFS
        counts.saveAsTextFile(outputDir);

        sc.close();
    }
}
