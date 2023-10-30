package org.example.truck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class TruckCoordinatesSerializer implements Serializer<TruckCoordinates> {

    final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, TruckCoordinates data) {
        byte[] response = null;
        try {
            response = objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
