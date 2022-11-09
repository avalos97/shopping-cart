package com.pack.shopping.cart.api.service;

import java.util.Optional;

import com.pack.shopping.cart.api.dto.RefreshTokenDTO;
import com.pack.shopping.cart.api.dto.SignedInUserDTO;
import com.pack.shopping.cart.api.dto.UserDTO;
import com.pack.shopping.cart.api.entity.CardEntity;
import com.pack.shopping.cart.api.entity.UserEntity;

public interface UserService {

    void deleteCustomerById(String id);

    Iterable<UserEntity> getAllCustomers();

    Optional<CardEntity> getCardByCustomerId(String id);

    Optional<UserEntity> getCustomerById(String id);

    Optional<SignedInUserDTO> createUser(UserDTO user);

    UserEntity findUserByUsername(String username);

    SignedInUserDTO getSignedInUser(UserEntity userEntity);

    Optional<SignedInUserDTO> getAccessToken(RefreshTokenDTO refreshToken);

    void removeRefreshToken(RefreshTokenDTO refreshToken);
}
