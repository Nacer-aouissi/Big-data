# Hadoop Setup and Word Count Implementation Guide

## Table of Contents

- [Prerequisites](#prerequisites)
- [Step 1: Creating Docker Network](#step-1-creating-docker-network)
- [Step 2: Launching Containers](#step-2-launching-containers)
- [Step 3: Setting Up Hadoop and Uploading Files](#step-3-setting-up-hadoop-and-uploading-files)
- [Step 4: Creating Maven Project Files](#step-4-creating-maven-project-files)
- [Step 5: Building the Project](#step-5-building-the-project)
- [Step 6: Uploading JAR to Docker](#step-6-uploading-jar-to-docker)
- [Step 7: Launching MapReduce Job](#step-7-launching-mapreduce-job)
- [Step 8: Checking Results](#step-8-checking-results)
- [Step 9: Monitoring Your Job](#step-9-monitoring-your-job)

## Prerequisites

Before starting, ensure you have the following installed and configured:

1. Docker installed and running
2. Java (JDK 1.8)
3. Visual Studio Code with Java & Maven extensions
4. Basic understanding of terminal commands

## Step 1: Creating Docker Network

Create a Docker network for Hadoop cluster communication:

```bash
docker network create hadoop
```

## Step 2: Launching Containers

Launch the Hadoop master and worker nodes:

```bash
# Launch master node
docker run -itd --net=hadoop -p 9870:9870 -p 8088:8088 --name hadoop-master --hostname hadoop-master liliasfaxi/hadoop-cluster:latest

# Launch worker nodes
docker run -itd -p 8040:8042 --net=hadoop --name hadoop-worker1 --hostname hadoop-worker1 liliasfaxi/hadoop-cluster:latest
docker run -itd -p 8041:8042 --net=hadoop --name hadoop-worker2 --hostname hadoop-worker2 liliasfaxi/hadoop-cluster:latest
```

## Step 3: Setting Up Hadoop and Uploading Files

Initialize Hadoop and prepare the input directory:

```bash
# Access the master node
docker exec -it hadoop-master bash

# Start Hadoop services
./start-hadoop.sh

# Create input directory and upload data
hdfs dfs -mkdir -p /user/root/input
hdfs dfs -put words.txt input
hdfs dfs -ls input
hdfs dfs -tail input/words.txt
```

## Step 4: Creating Maven Project Files

Create the following Java files for the Word Count MapReduce job:

### TokenizerMapper.java

```java
package hadoop.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            context.write(word, one);
        }
    }
}
```

### IntSumReducer.java

```java
package hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
```

### WordCount.java

```java
package hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");

        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

### Modifying pom.xml

Add the following to your `pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-assembly-plugin</artifactId>
      ...
    </plugin>
  </plugins>
</build>
```

## Step 5: Building the Project

Build the project using Maven:

```bash
mvn clean compile assembly:single
```

## Step 6: Uploading JAR to Docker

Copy the generated JAR file to the Hadoop master container:

```bash
docker cp target/wordcount-1.0-SNAPSHOT-jar-with-dependencies.jar hadoop-master:/root/wordcount.jar
```

## Step 7: Launching MapReduce Job

Execute the Word Count MapReduce job:

```bash
hadoop jar wordcount.jar input output
```

## Step 8: Checking Results

View the output files and their contents:

```bash
# List output files
hdfs dfs -ls output

# View results
hdfs dfs -cat output/part-r-00000
```

## Step 9: Monitoring Your Job

Access the following web interfaces to monitor your Hadoop cluster:

- **Namenode UI**: [http://localhost:9870](http://localhost:9870)

  - View HDFS file system
  - Monitor cluster health
  - Browse files and directories

- **YARN Resource Manager UI**: [http://localhost:8088](http://localhost:8088)

  - Monitor running applications
  - View cluster metrics
  - Check resource usage

- **Worker Nodes**:
  - Worker 1: [http://localhost:8041](http://localhost:8041)
  - Worker 2: [http://localhost:8042](http://localhost:8042)
  - View node-specific metrics and logs
