package com.example.bankcards.service;

import com.example.bankcards.dto.request.card.CardCreateDtoRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.ERole;
import com.example.bankcards.entity.enums.StatusCard;
import com.example.bankcards.exception.services.ServiceException;
import com.example.bankcards.exception.services.enums.ServiceExceptionCode;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.util.EncryptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final EncryptionUtils encryptionUtil;
    private final UserService userService;

    @Transactional
    public void createCard(CardCreateDtoRequest request) {
        User user = userService.getUserById(request.getUserId());
        String cardNumber = encryptionUtil.encrypt(request.getCardNumber());
        LocalDate validatePeriod = request.getValidityPeriod();
        checkedActiveCard(validatePeriod);
        Card card = Card.builder()
                .cardNumber(cardNumber)
                .owner(user)
                .validityPeriod(validatePeriod)
                .balance(request.getBalance())
                .statusCard(StatusCard.CREATED)
                .build();
        cardRepository.save(card);
    }

    private void checkedActiveCard(LocalDate validityPeriod, StatusCard statusCard) {
        if (!(statusCard == StatusCard.ACTIVE && validityPeriod.isBefore(LocalDate.now()))) {
            throw new ServiceException(ServiceExceptionCode. INVALID_DATE);
        }
    }
}
