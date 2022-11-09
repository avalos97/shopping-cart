package com.pack.shopping.cart.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    @Query("select c from CartEntity c join c.user u where u.userId = :customerId")
    Optional<CartEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
