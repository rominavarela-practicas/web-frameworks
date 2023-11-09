package org.example;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.avro.generated.TruckCoordinates;
import org.example.truck.TruckConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.truck.TruckConfig.POLL_INTERVAL_SEC;


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
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "OrderGroup");
        props.put("schema.registry.url", "http://localhost:8085");
        props.put("specific.avro.reader", "true");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // Additional Properties
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                // Behavior when a consumer starts reading a partition without a commited offset
                "latest" // Ignore previous records
                // "earliest" // Start from the beggening
        );
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "123455678");
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
                //
                "1MB");
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "200");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,
                // If hearbeat is not sent out for this time window, consumer group will rebalance
                "10000");

        KafkaConsumer<String, TruckCoordinates> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(TruckConfig.TOPIC_NAME));

        try {
            while (true) {
                ConsumerRecords<String, TruckCoordinates> records = consumer.poll(Duration.ofSeconds(POLL_INTERVAL_SEC));
                for(ConsumerRecord<String, TruckCoordinates> record: records) {
                    System.out.println(String.format("Key = %s, Id = %s, Lat = %s, Lon = %s",
                            record.key(), record.value().getId(), record.value().getLatitude(), record.value().getLongitude()));
                }
                consumer.commitSync();
            }
        } catch (Exception ex) {
            consumer.close();
        }
    }
}
