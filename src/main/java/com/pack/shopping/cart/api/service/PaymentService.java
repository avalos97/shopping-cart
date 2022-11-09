package com.pack.shopping.cart.api.service;

import java.util.Optional;

import com.pack.shopping.cart.api.dto.AuthorizationDTO;
import com.pack.shopping.cart.api.entity.AuthorizationEntity;

public interface PaymentService {
    
    public Optional<AuthorizationEntity> addauthorization(AuthorizationDTO authorize); 
}
