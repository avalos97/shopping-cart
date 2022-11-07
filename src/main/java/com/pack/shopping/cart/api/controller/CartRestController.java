package com.pack.shopping.cart.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.CartApi;
import com.pack.shopping.cart.api.dto.CartDTO;
import com.pack.shopping.cart.api.dto.ItemDTO;

@RestController
public class CartRestController implements CartApi {

    @Override
    public ResponseEntity<CartDTO> getCartByCustomerId(String customerId) {
        // TODO Auto-generated method stub
        return CartApi.super.getCartByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getCartItemsByCustomerId(String customerId) {
        // TODO Auto-generated method stub
        return CartApi.super.getCartItemsByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<ItemDTO> getCartItemsByItemId(String customerId, String itemId) {
        // TODO Auto-generated method stub
        return CartApi.super.getCartItemsByItemId(customerId, itemId);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> addCartItemsByCustomerId(String customerId, @Valid ItemDTO item) {
        // TODO Auto-generated method stub
        return CartApi.super.addCartItemsByCustomerId(customerId, item);
    }

    @Override
    public ResponseEntity<List<ItemDTO>> addOrReplaceItemsByCustomerId(String customerId, @Valid ItemDTO item) {
        // TODO Auto-generated method stub
        return CartApi.super.addOrReplaceItemsByCustomerId(customerId, item);
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) {
        // TODO Auto-generated method stub
        return CartApi.super.deleteCart(customerId);
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) {
        // TODO Auto-generated method stub
        return CartApi.super.deleteItemFromCart(customerId, itemId);
    }

}
