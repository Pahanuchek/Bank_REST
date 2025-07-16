package com.example.bankcards.service;

import com.example.bankcards.dto.request.CardCreateDtoRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final EncryptionUtil encryptionUtil;

    @Transactional
    public void createCard(CardCreateDtoRequest request) {
        String cardNumber = encryptionUtil.encrypt(request.getCardNumber());
        Card card = Card.builder()
                .cardNumber(cardNumber)
                .owner(new User("alex", "petrov"))
                .validityPeriod(LocalDate.now())
                .balance(new BigDecimal(300))
                .build();
        cardRepository.save(card);
    }
}
