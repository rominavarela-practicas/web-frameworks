package com.example.producerspringproject.creditcard;

import com.example.producerspringproject.creditcard.CreditCardDto;
import com.example.producerspringproject.creditcard.CreditCardProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

    @Autowired
    CreditCardProducerService creditCardProducerService;

    @PostMapping
    public void sendCreditCard(@RequestBody @Validated CreditCardDto creditCardDto) {
        System.out.println("Sending credit card data...");
        creditCardProducerService.sendCreditCardData(creditCardDto);
    }
}
