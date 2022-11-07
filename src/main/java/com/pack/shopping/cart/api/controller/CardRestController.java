package com.pack.shopping.cart.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.CardApi;
import com.pack.shopping.cart.api.dto.CardDTO;

@RestController
public class CardRestController implements CardApi{

    @Override
    public ResponseEntity<List<CardDTO>> getAllCards() {
        // TODO Auto-generated method stub
        return CardApi.super.getAllCards();
    }

    @Override
    public ResponseEntity<Void> deleteCardById(String id) {
        // TODO Auto-generated method stub
        return CardApi.super.deleteCardById(id);
    }

    @Override
    public ResponseEntity<CardDTO> getCardById(String id) {
        // TODO Auto-generated method stub
        return CardApi.super.getCardById(id);
    }

    @Override
    public ResponseEntity<CardDTO> registerCard(@Valid CardDTO newCard) {
        // TODO Auto-generated method stub
        return CardApi.super.registerCard(newCard);
    }
    
}
