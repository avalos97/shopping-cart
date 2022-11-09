package com.pack.shopping.cart.api.serviceImplement;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.pack.shopping.cart.api.dto.CardDTO;
import com.pack.shopping.cart.api.entity.CardEntity;
import com.pack.shopping.cart.api.entity.UserEntity;
import com.pack.shopping.cart.api.exception.GenericAlreadyExistsException;
import com.pack.shopping.cart.api.exception.ResourceNotFoundException;
import com.pack.shopping.cart.api.repository.CardRepository;
import com.pack.shopping.cart.api.repository.UserRepository;
import com.pack.shopping.cart.api.service.CardService;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository repository;
    private UserRepository userRepo;

    public CardServiceImpl(CardRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    @Override
    public void deleteCardById(String id) {
        if (id == null || !repository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Iterable<CardEntity> getAllCards() {
        return repository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardById(String id) {
        if (id == null || !repository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(String.format("Card with id %s not found:", id));
        }
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<CardEntity> registerCard(@Valid CardDTO cardDTO) {
        if (repository.existsByNumber(cardDTO.getCardNumber())) {
            throw new GenericAlreadyExistsException(String.format("the card with number %s already exists", cardDTO.getCardNumber()));
        }
        return Optional.of(repository.save(toEntity(cardDTO)));
    }

    private CardEntity toEntity(CardDTO card) {
        CardEntity entity = new CardEntity();
        Optional<UserEntity> user = userRepo.findById(UUID.fromString(card.getUserId()));
        user.ifPresent(u -> entity.setUser(u));
        return entity.setNumber(card.getCardNumber()).setCvv(card.getCvv()).setExpires(card.getExpires()).setCardId(UUID.fromString(card.getCardId()));
    }
}
