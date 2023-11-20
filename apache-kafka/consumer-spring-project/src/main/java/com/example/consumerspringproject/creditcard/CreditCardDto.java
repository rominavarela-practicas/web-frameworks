package com.example.consumerspringproject.creditcard;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
