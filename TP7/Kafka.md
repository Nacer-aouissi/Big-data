# Kafka progject

## step 1 : intallation & Lanching :

- downloading jdk & kafak binary
- modifyng zookeeper & server.properties in config :
  setting the path : C:/kafka
- running the commands :
  - zookeeper cmd : .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
  - server cmd : .\bin\windows\kafka-server-start.bat .\config\server.properties

## Step 2 : creating kafka topic :

- in new terminal :
  .\bin\windows\kafka-topics.bat --create --topic Mytopic --bootstrap-server localhost:9092

## Step 3 : creating Producer and Consumer :

- in new terminal :
  .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Mytopic

## Step 4 : Multi-brokers :

- we need 1st to create copies from server.properties server1/server2 and change each config :
  - broker.id (each server has different one)
  - log.dirs (each server has different one)
  - listeners (changing port :9093/9094)
- than starting each broker

## Step 4 : Iot stimulation

- run zookeeper & server
- create new topic
- install kafka python client :  
  `pip install kafka-python`
- than create poducer and consumer files using python
- run them using :
  `python iot_consumer.py`
  `python iot_producer.py`
