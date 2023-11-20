package com.example.producerspringproject.creditcard;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CreditCardDto {
    @NonNull
    private String fullName;
    @NonNull
    private String cardNumber;
    @NonNull
    private String expDate;
    @NonNull
    private String secCode;
}
