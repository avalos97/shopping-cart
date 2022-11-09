package com.pack.shopping.cart.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "select count(u.*) from ecomm.user u where u.username = :username or u.email = :email", nativeQuery = true)
    Integer findByUsernameOrEmail(String username, String email);
}
