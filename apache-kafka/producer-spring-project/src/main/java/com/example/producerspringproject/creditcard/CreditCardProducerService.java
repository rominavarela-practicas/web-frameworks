package com.example.producerspringproject.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreditCardProducerService {

    private final String CREDIT_CARD_TOPIC = "CreditCardTopic";

    @Autowired
    private KafkaTemplate<String, CreditCardDto> kafkaTemplate;

    public void sendCreditCardData(CreditCardDto creditCardDto) {
        kafkaTemplate.send(CREDIT_CARD_TOPIC, creditCardDto.getCardNumber(), creditCardDto);
    }
}
