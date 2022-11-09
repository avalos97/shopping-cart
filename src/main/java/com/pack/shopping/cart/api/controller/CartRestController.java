package com.pack.shopping.cart.api.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.CartApi;
import com.pack.shopping.cart.api.dto.CartDTO;
import com.pack.shopping.cart.api.dto.ItemDTO;
import com.pack.shopping.cart.api.hateoas.CartRepresentationModelAssembler;
import com.pack.shopping.cart.api.service.CartService;

@RestController
public class CartRestController implements CartApi {

    private CartService service;
    private final CartRepresentationModelAssembler assembler;

    public CartRestController(CartService service, CartRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<CartDTO> getCartByCustomerId(String customerId) {
        return ok(assembler.toModel(service.getCartByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getCartItemsByCustomerId(String customerId) {
        return ok(service.getCartItemsByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<ItemDTO> getCartItemsByItemId(String customerId, Integer itemId) {
        return ok(service.getCartItemsByItemId(customerId, itemId));
    }

    @Override
    public ResponseEntity<List<ItemDTO>> addCartItemsByCustomerId(String customerId, @Valid ItemDTO item) {
        return new ResponseEntity<List<ItemDTO>>(service.addCartItemsByCustomerId(customerId, item), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> addOrReplaceItemsByCustomerId(String customerId, @Valid ItemDTO item) {
        return ok(service.addOrReplaceItemsByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) {
        service.deleteCart(customerId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, Integer itemId) {
        service.deleteItemFromCart(customerId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
