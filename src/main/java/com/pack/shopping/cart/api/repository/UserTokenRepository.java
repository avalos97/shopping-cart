package com.pack.shopping.cart.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pack.shopping.cart.api.entity.UserTokenEntity;

@Repository
public interface UserTokenRepository extends CrudRepository<UserTokenEntity, UUID> {

    Optional<UserTokenEntity> findByRefreshToken(String refreshToken);

    @Modifying
    @Query(value = "delete from ecomm.user_token u where u.user_id = :userId", nativeQuery = true)
    public void deleteByUserId(UUID userId);
}
