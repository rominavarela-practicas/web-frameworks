package com.example.consumerspringproject.creditcard;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

@Service
public class CreditCardConsumerService {

    private final String CREDIT_CARD_TOPIC = "CreditCardTopic";

    @KafkaListener(topics = CREDIT_CARD_TOPIC)
    public void consumeCreditCardData(CreditCardDto creditCardDto) {
        System.out.println("Received Credit Card Message: " + creditCardDto.getCardNumber());
    }
}
