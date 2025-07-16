package com.example.bankcards.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreateDtoRequest {
    private String cardNumber;
    private String validityPeriod;
    private String balance;
}
