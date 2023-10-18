package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.order.OrderProducerCallback;

import java.util.Properties;

public class App 
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);
        ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Macbook Pro", 10);
        try {
            producer.send(record, new OrderProducerCallback());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
