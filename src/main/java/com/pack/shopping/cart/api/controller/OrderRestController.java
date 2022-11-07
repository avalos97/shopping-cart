package com.pack.shopping.cart.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.OrderApi;
import com.pack.shopping.cart.api.dto.NewOrderDTO;
import com.pack.shopping.cart.api.dto.OrderDTO;

@RestController
public class OrderRestController implements OrderApi{

    @Override
    public ResponseEntity<OrderDTO> addOrder(@Valid NewOrderDTO newOrder) {
        // TODO Auto-generated method stub
        return OrderApi.super.addOrder(newOrder);
    }

    @Override
    public ResponseEntity<OrderDTO> getByOrderId(String id) {
        // TODO Auto-generated method stub
        return OrderApi.super.getByOrderId(id);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        // TODO Auto-generated method stub
        return OrderApi.super.getOrdersByCustomerId(customerId);
    }
    
}
