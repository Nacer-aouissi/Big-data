# 🚀 Apache Kafka Project Guide

![Kafka](https://img.shields.io/badge/Kafka-3.5+-blue.svg)
![Java](https://img.shields.io/badge/Java-17+-red.svg)
![Python](https://img.shields.io/badge/Python-3.8+-green.svg)

## 📝 Overview

This guide covers setting up and working with Apache Kafka, including:

- 🚀 Installation and setup
- 📊 Topic creation
- 🔄 Producer/Consumer setup
- 🌐 Multi-broker configuration
- 🔌 IoT simulation

## 🚀 Step-by-Step Guide

### 1️⃣ Installation & Launch

#### 📥 Prerequisites

1. Download required binaries:

   - [Kafka Binary](https://kafka.apache.org/downloads)
   - [JDK](https://www.oracle.com/th/java/technologies/downloads/)

2. ⚙️ Configuration:

   - Modify `zookeeper.properties` and `server.properties`
   - Set path to `C:/kafka`

3. 🚀 Launch Services:

```bash
# Start Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Start Kafka Server
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

### 2️⃣ Topic Creation

#### 📊 Create Kafka Topic

```bash
# Create new topic
.\bin\windows\kafka-topics.bat --create --topic Mytopic --bootstrap-server localhost:9092
```

### 3️⃣ Producer and Consumer

#### 🔄 Console Tools

```bash
# Start Producer
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Mytopic

# Start Consumer
.\bin\windows\kafka-console-consumer.bat --broker-list localhost:9092 --topic Mytopic
```

### 4️⃣ Multi-broker Setup

#### 🌐 Configuration

1. 📁 Create server copies:

   - `server1.properties`
   - `server2.properties`

2. ⚙️ Modify each server:

```properties
# Server 1
broker.id=1
log.dirs=/tmp/kafka-logs-1
listeners=PLAINTEXT://:9093

# Server 2
broker.id=2
log.dirs=/tmp/kafka-logs-2
listeners=PLAINTEXT://:9094
```

### 5️⃣ IoT Simulation

#### 🔌 Python Implementation

1. 📦 Install dependencies:

```bash
pip install kafka-python
```

2. 🐍 Create Python files:

   - `iot_producer.py`
   - `iot_consumer.py`

3. 🚀 Run simulation:

```bash
# Start consumer
python iot_consumer.py

# Start producer
python iot_producer.py
```

## 📚 Additional Resources

- 📖 [Kafka Documentation](https://kafka.apache.org/documentation/)
- 🐍 [Kafka-Python](https://kafka-python.readthedocs.io/)
- 🔧 [Kafka Configuration](https://kafka.apache.org/documentation/#configuration)

---

<div align="center">
  <sub>Built with ❤️ by Your Name</sub>
</div>
