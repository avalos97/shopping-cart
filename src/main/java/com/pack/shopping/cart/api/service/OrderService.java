package com.pack.shopping.cart.api.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.pack.shopping.cart.api.dto.NewOrderDTO;
import com.pack.shopping.cart.api.entity.OrderEntity;

public interface OrderService {

    public Optional<OrderEntity> addOrder(@Valid NewOrderDTO newOrder);

    public Iterable<OrderEntity> getOrdersByCustomerId(@NotNull @Valid String customerId);

    public Optional<OrderEntity> getByOrderId(String id);
}
