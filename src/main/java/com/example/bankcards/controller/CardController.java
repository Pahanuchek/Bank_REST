package com.example.bankcards.controller;

import com.example.bankcards.dto.request.card.CardCreateDtoRequest;
import com.example.bankcards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCard(@RequestBody CardCreateDtoRequest request) {
        cardService.createCard(request);
    }
}
