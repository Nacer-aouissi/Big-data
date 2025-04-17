from kafka import KafkaProducer
import time, json, random

producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

while True:
    data = {
        'sensor_id': 'sensor-1',
        'temperature': round(random.uniform(20.0, 40.0), 2),
        'status': 'OK'
    }
    producer.send('iot-sensors', data)
    print(f"Sent: {data}")
    time.sleep(2)
