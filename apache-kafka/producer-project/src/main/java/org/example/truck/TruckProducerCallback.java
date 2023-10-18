package org.example.truck;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TruckProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception ex) {
        if (ex != null) {
            ex.printStackTrace();
        } else {
            System.out.printf("Message sent successfully! Partition = %s, Offset = %s%n", recordMetadata.partition(), recordMetadata.offset());
        }
    }
}
