package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KGroupedTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;


/**
 * Hello world!
 *
 */
public class App 
{
    public static final String CONSUMER_TOPIC = "NumbersStreamDataflowTopic0";

    public static final String PRODUCER_TOPIC_1 = "NumbersStreamDataflowTopic1";

    public static final String PRODUCER_TOPIC_2 = "NumbersStreamDataflowTopic2";

    public static final String CONSUMER_GROUP = "consumer-stream-project";

    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, CONSUMER_GROUP);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> inputStream = builder.stream(CONSUMER_TOPIC);

        /*
        // Map (banana -> banana)
        KStream<String, String> outputStream = inputStream.map((key, value) -> {
            System.out.println(String.format("Processing Key = %s, Value = %s", key, value));
            return new KeyValue<>(value.toLowerCase(), value.toLowerCase());
        });
        */

        // Flat Map (banana -> banana, BANANA)
        KStream<String, String> outputStream = inputStream.flatMap((key, value) -> {
            System.out.println(String.format("Processing Key = %s, Value = %s", key, value));
            return Arrays.asList(
                    new KeyValue<>(value.toLowerCase(), value.toLowerCase()),
                    new KeyValue<>(value.toUpperCase(), value.toUpperCase())
            );
        });
        outputStream.to(PRODUCER_TOPIC_1);

        // Map aggregation
        KTable<String,Long> countsTable = outputStream.groupByKey().count();
        countsTable.toStream().to(PRODUCER_TOPIC_2, Produced.with(Serdes.String(), Serdes.Long()));

        Topology topology = builder.build();
        System.out.println(topology.describe());
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.cleanUp();
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
