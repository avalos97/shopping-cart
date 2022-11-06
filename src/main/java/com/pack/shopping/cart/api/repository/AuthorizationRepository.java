package com.pack.shopping.cart.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.AuthorizationEntity;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, UUID>{
    
}
