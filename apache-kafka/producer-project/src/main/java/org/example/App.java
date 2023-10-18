package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.truck.TruckConfig;
import org.example.truck.TruckProducerCallback;

import java.util.Properties;
import java.util.UUID;

public class App
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(TruckConfig.TOPIC_NAME, randomID(), randomCoordinates());
        try {
            producer.send(record, new TruckProducerCallback());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }

    public static String randomID() {
        return "truck-" + ((int) (Math.random() * 10));
    }

    public static String randomCoordinates() {
        return String.format("%s N,%s E", Math.random() * 100, Math.random() * 100);
    }
}
