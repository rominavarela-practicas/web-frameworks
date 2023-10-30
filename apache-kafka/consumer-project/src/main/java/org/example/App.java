package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.truck.TruckConfig;
import org.example.truck.TruckCoordinates;
import org.example.truck.TruckCoordinatesDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TruckCoordinatesDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "OrderGroup");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        KafkaConsumer<String, TruckCoordinates> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TruckConfig.TOPIC_NAME));
        ConsumerRecords<String, TruckCoordinates> records = consumer.poll(Duration.ofSeconds(20));
        for(ConsumerRecord<String, TruckCoordinates> record: records) {
            System.out.println(String.format("Key = %s, Id = %s, Lat = %s, Lon = %s",
                    record.key(), record.value().getId(), record.value().getLatitude(), record.value().getLongitude()));
        }
    }
}
