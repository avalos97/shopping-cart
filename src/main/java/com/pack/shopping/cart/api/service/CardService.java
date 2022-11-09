package com.pack.shopping.cart.api.service;

import java.util.Optional;

import javax.validation.Valid;

import com.pack.shopping.cart.api.dto.CardDTO;
import com.pack.shopping.cart.api.entity.CardEntity;

public interface CardService {

    public void deleteCardById(String id);

    public Iterable<CardEntity> getAllCards();

    public Optional<CardEntity> getCardById(String id);

    public Optional<CardEntity> registerCard(@Valid CardDTO cardDTO);
}
