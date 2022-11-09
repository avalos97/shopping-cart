package com.pack.shopping.cart.api.service;

import java.util.List;

import javax.validation.Valid;

import com.pack.shopping.cart.api.dto.ItemDTO;
import com.pack.shopping.cart.api.entity.CartEntity;

public interface CartService {

    public List<ItemDTO> addCartItemsByCustomerId(String customerId, @Valid ItemDTO item);

    public List<ItemDTO> addOrReplaceItemsByCustomerId(String customerId, @Valid ItemDTO item);

    public void deleteCart(String customerId);

    public void deleteItemFromCart(String customerId, Integer itemId);

    public CartEntity getCartByCustomerId(String customerId);

    public List<ItemDTO> getCartItemsByCustomerId(String customerId);

    public ItemDTO getCartItemsByItemId(String customerId, Integer itemId);
}
