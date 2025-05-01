# 🔥 Spark Requirements and Instructions

## 📋 Requirements

- 🐘 Hadoop 3.3.6
- ⚡ Spark 3.5.0
- ☕ Java 8
- 🐳 Docker
- 📦 Maven
- 💻 VS Code (or any IDE)
- 🐧 Linux or similar system

## 1️⃣ Testing

### 🚀 Launching Hadoop Containers

```bash
docker start hadoop-master hadoop-worker1 hadoop-worker2
docker exec -it hadoop-master bash
./start-hadoop.sh
```

### 📁 Creating Input Folder and Random Text File

```bash
hdfs dfs -mkdir -p input
hdfs dfs -put words.txt input
```

### 💻 Opening Spark Shell

```bash
spark-shell
```

### ⚙️ Running Process

```scala
val lines = sc.textFile("file1.txt")
val words = lines.flatMap(_.split("\\s+"))
val wc = words.map(w => (w, 1)).reduceByKey(_ + _)
wc.saveAsTextFile("file1.count")
```

## 2️⃣ Operating on Cluster

### 📁 Creating Maven Project

Project structure:

```
wordcount-spark/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── spark/
                └── batch/
                    └── tp21/
                        └── WordCountTask.java
```

### 📤 Building and Uploading to Docker

```bash
docker cp target/wordcount-spark-1.0-SNAPSHOT.jar hadoop-master:/root/wordcount-spark.jar
```

### 🚀 Launching the Operation

```bash
spark-submit \
  --class spark.batch.tp9.WordCountTask \
  --master yarn \
  --deploy-mode cluster \
  wordcount-spark.jar input/words.txt out-spark2
```

## 3️⃣ Using Spark Streaming

### 📁 Creating Maven Project

Project structure:

```
stream/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── spark/
                └── streaming/
                    └── tp9/
                        └── Stream.java
```

### 📤 Building JAR File and Uploading to Docker

```bash
docker cp target/stream-1.0-SNAPSHOT.jar hadoop-master:/root/wordcount-spark.jar
```

### 🔄 Starting Streaming Process

1. 🔑 Access Hadoop master bash:

```bash
docker exec -it hadoop-master bash
```

2. 📥 Install netcat:

```bash
apt update
apt install netcat
```

3. 🔌 Start entry port:

```bash
nc -lk 9999
```

4. 🚀 Start Spark streaming:

```bash
spark-submit --class spark.streaming.tp9.Stream --master local wordcount-spark.jar > out
```

5. ✍️ Write some words in entry port and track the process in the 2nd port

6. 📊 View results:

```bash
cat out
```
