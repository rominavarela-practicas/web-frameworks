package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.truck.TruckConfig;
import org.example.truck.TruckCoordinates;
import org.example.truck.TruckCoordinatesSerializer;
import org.example.truck.TruckProducerCallback;

import java.util.Properties;

public class App
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TruckCoordinatesSerializer.class.getName());

        KafkaProducer<String, TruckCoordinates> producer = new KafkaProducer<>(props);
        TruckCoordinates randomTruck = generateRandomTruck();
        ProducerRecord<String, TruckCoordinates> record = new ProducerRecord<>(TruckConfig.TOPIC_NAME, randomTruck.getId(), randomTruck);
        try {
            producer.send(record, new TruckProducerCallback());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static TruckCoordinates generateRandomTruck() {
        return TruckCoordinates.builder()
                .id("truck-" + ((int) (Math.random() * 10)))
                .latitude((int) Math.random() * 100)
                .longitude((int) Math.random() * 100)
                .build();
    }
}
