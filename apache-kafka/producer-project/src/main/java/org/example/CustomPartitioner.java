package org.example;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.example.avro.generated.TruckCoordinates;
import org.example.truck.TruckConfig;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);

        if (topic.contentEquals(TruckConfig.TOPIC_NAME)) {
            TruckCoordinates truckCoordinates = (TruckCoordinates) value;
            if (isArea51(truckCoordinates)) {
                return 5;
            }
        }

        return Math.abs(Utils.murmur2(keyBytes) % partitions.size() - 1);
    }

    boolean isArea51(TruckCoordinates truckCoordinates) {
        return truckCoordinates.getLatitude() == 37.2431 && truckCoordinates.getLongitude() == 115.793;
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
