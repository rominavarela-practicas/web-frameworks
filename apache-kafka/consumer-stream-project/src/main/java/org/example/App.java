package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;


/**
 * Hello world!
 *
 */
public class App 
{
    public static final String CONSUMER_TOPIC = "NumbersStreamDataflowTopic0";

    public static final String PRODUCER_TOPIC = "NumbersStreamDataflowTopic1";

    public static final String CONSUMER_GROUP = "consumer-stream-project";

    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, CONSUMER_GROUP);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Integer> inputStream = builder.stream(CONSUMER_TOPIC);
        KStream<String, Integer> outputStream = inputStream.map((key, value) -> {
            System.out.println(String.format("Processing Key = %s, Value = %s", key, value));
            return new KeyValue<>(key, value * value);
        });
        outputStream.to(PRODUCER_TOPIC);

        Topology topology = builder.build();
        System.out.println(topology.describe());
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.cleanUp();
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
