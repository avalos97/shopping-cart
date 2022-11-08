package com.pack.shopping.cart.api.hateoas;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pack.shopping.cart.api.dto.OrderDTO;
import com.pack.shopping.cart.api.entity.OrderEntity;

public class OrderRepresentationModelAssembler{
    
}

// @Component
// public class OrderRepresentationModelAssembler extends RepresentationModelAssemblerSupport<OrderEntity, OrderDTO> {

//     public OrderRepresentationModelAssembler(Class<?> controllerClass, Class<OrderDTO> resourceType) {
//         super(controllerClass, resourceType);
//         //TODO Auto-generated constructor stub
//     }

//     @Override
//     public OrderDTO toModel(OrderEntity entity) {
//         // TODO Auto-generated method stub
//         return null;
//     }
    
// }
