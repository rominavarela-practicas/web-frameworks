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

        // Additional Properties
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,
                // "none"
                // "snappy" // By Google, Less CPU Consumption
                "gzip" // Higher Compresion Ratio, Less Network Bandwith
        );
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,
                // The total bytes of memory a Kafka producer can use to buffer records waiting to be sent to the server.
                // If records are sent faster than they can be delivered to the server the producer will block for MAX_BLOCK_MS_CONFIG
                // after which it will throw an exception.
                String.valueOf(32 * 1024 * 1024)
        );
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,
                // Batch size (in bytes)
                // A Kafka producer will attempt to batch records together into fewer requests whenever multiple records are being sent to the same partition.
                // Must be at least 0. No attempt will be made to batch records larger than this size.
                "16384"
        );
        props.put(ProducerConfig.LINGER_MS_CONFIG,
                // The producer will wait for up to the given delay to allow other records to be sent batched together.
                // Default is 0.
                "1000"
        );
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,
                // The producer will wait for up to the given delay to allow other records to be sent batched together.
                // Default is 30 * 1000.
                "10000"
        );
        props.put(ProducerConfig.ACKS_CONFIG,
                // "all", The leader will wait for the full set of in-sync replicas to acknowledge the record. This guarantees that the record will not be lost.
                // "0", The producer will not wait for any acknowledgment. No guarantee can be made that the server has received the record.
                "1" // The leader will write the record to its local log but will respond without awaiting full acknowledgement from all followers.
        );
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                // When enabled (true), a producer will ensure that exactly one copy of a message is written to the stream.
                "false"
        );
        props.put(ProducerConfig.RETRIES_CONFIG, "3");
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "500");

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
