from kafka import KafkaConsumer
import json

consumer = KafkaConsumer(
    'iot-sensors',
    bootstrap_servers='localhost:9092',
    auto_offset_reset='earliest',
    value_deserializer=lambda m: json.loads(m.decode('utf-8'))
)

for msg in consumer:
    print(f"Received: {msg.value}")
