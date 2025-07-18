package com.example.bankcards.dto.request.card;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreateDtoRequest {
    private String cardNumber;
    private long userId;
    private String validityPeriod;
    @DecimalMin(value = "0.00", message = "Баланс не может быть отрицательным")
    @Digits(fraction = 2, message = "Некорректный формат баланса", integer = 10)
    private BigDecimal balance;
}
