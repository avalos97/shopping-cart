package com.pack.shopping.cart.api.hateoas;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pack.shopping.cart.api.dto.CardDTO;
import com.pack.shopping.cart.api.entity.CartEntity;

public class CartRepresentationModelAssembler{
    
}

// @Component
// public class CartRepresentationModelAssembler extends RepresentationModelAssemblerSupport<CartEntity, CardDTO>{

//     public CartRepresentationModelAssembler(Class<?> controllerClass, Class<CardDTO> resourceType) {
//         super(controllerClass, resourceType);
//         //TODO Auto-generated constructor stub
//     }

//     @Override
//     public CardDTO toModel(CartEntity entity) {
//         // TODO Auto-generated method stub
//         return null;
//     }
    
// }
