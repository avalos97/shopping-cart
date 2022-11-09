package com.pack.shopping.cart.api.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.OrderApi;
import com.pack.shopping.cart.api.dto.NewOrderDTO;
import com.pack.shopping.cart.api.dto.OrderDTO;
import com.pack.shopping.cart.api.hateoas.OrderRepresentationModelAssembler;
import com.pack.shopping.cart.api.service.OrderService;

@RestController
public class OrderRestController implements OrderApi {

    private final OrderRepresentationModelAssembler assembler;
    private OrderService service;

    public OrderRestController(OrderService service, OrderRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<OrderDTO> addOrder(@Valid NewOrderDTO newOrder) {
        return service.addOrder(newOrder).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<OrderDTO> getByOrderId(String id) {
        return service.getByOrderId(id).map(assembler::toModel).map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@NotNull @Valid String customerId) {
        return ok(assembler.toListModel(service.getOrdersByCustomerId(customerId)));
    }

}
