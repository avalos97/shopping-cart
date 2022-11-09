package com.pack.shopping.cart.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.AuthorizationEntity;

@Repository
public interface AuthorizationRepository extends JpaRepository<AuthorizationEntity, UUID>{
 
    @Query("select a from AuthorizationEntity a join a.orderEntity o where o.orderId = :id")
    AuthorizationEntity findByOrderId(@Param("id") UUID customerId);
}
