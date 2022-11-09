package com.pack.shopping.cart.api.controller;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.AuthApi;
import com.pack.shopping.cart.api.dto.RefreshTokenDTO;
import com.pack.shopping.cart.api.dto.SignInDTO;
import com.pack.shopping.cart.api.dto.SignedInUserDTO;
import com.pack.shopping.cart.api.dto.UserDTO;
import com.pack.shopping.cart.api.entity.UserEntity;
import com.pack.shopping.cart.api.exception.InvalidRefreshTokenException;
import com.pack.shopping.cart.api.service.UserService;

@RestController
public class AuthRestController implements AuthApi {

    @Autowired
    private UserService service;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<SignedInUserDTO> getAccessToken(@Valid RefreshTokenDTO refreshToken) {
        return ok(service.getAccessToken(refreshToken).orElseThrow(InvalidRefreshTokenException::new));

    }

    @Override
    public ResponseEntity<SignedInUserDTO> signIn(@Valid SignInDTO signInReq) {
        UserEntity userEntity = service.findUserByUsername(signInReq.getUsername());
        if (passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword())) {
            return ok(service.getSignedInUser(userEntity));
        }
        throw new InsufficientAuthenticationException("Unauthorized.");
    }

    @Override
    public ResponseEntity<Void> signOut(@Valid RefreshTokenDTO refreshToken) {
        service.removeRefreshToken(refreshToken);
        return accepted().build();
    }

    @Override
    public ResponseEntity<SignedInUserDTO> signUp(@Valid UserDTO user) {
        return status(HttpStatus.CREATED).body(service.createUser(user).get());

    }

}
