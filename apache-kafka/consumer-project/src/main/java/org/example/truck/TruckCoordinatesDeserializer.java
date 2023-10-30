package org.example.truck;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class TruckCoordinatesDeserializer implements Deserializer<TruckCoordinates> {

    final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TruckCoordinates deserialize(String topic, byte[] data) {
        TruckCoordinates response = null;
        try {
            response = objectMapper.readValue(data, TruckCoordinates.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
