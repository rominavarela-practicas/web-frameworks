package org.example;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.avro.generated.TruckCoordinates;
import org.example.truck.TruckConfig;
import org.example.truck.TruckProducerCallback;

import java.util.Properties;

public class App
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        props.put("schema.registry.url", "http://localhost:8085");

        KafkaProducer<String, TruckCoordinates> producer = new KafkaProducer<>(props);
        TruckCoordinates randomTruck = generateRandomTruck();
        ProducerRecord<String, TruckCoordinates> record = new ProducerRecord<>(TruckConfig.TOPIC_NAME, randomTruck.getId().toString(), randomTruck);
        try {
            producer.send(record, new TruckProducerCallback());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static TruckCoordinates generateRandomTruck() {
        TruckCoordinates truck = new TruckCoordinates();
        truck.setId("truck-" + ((int) (Math.random() * 10)));
        truck.setLatitude((int) Math.random() * 100);
        truck.setLongitude((int) Math.random() * 100);
        return truck;
    }
}
