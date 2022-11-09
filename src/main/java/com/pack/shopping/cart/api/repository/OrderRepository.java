package com.pack.shopping.cart.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("select o from OrderEntity o join o.userEntity u where u.userId = :customerId")
    Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
