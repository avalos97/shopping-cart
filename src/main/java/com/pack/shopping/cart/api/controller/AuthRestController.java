package com.pack.shopping.cart.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.AuthApi;
import com.pack.shopping.cart.api.dto.RefreshTokenDTO;
import com.pack.shopping.cart.api.dto.SignInDTO;
import com.pack.shopping.cart.api.dto.SignedInUserDTO;
import com.pack.shopping.cart.api.dto.UserDTO;

@RestController
public class AuthRestController implements AuthApi{

    @Override
    public ResponseEntity<SignedInUserDTO> getAccessToken(@Valid RefreshTokenDTO refreshToken) {
        // TODO Auto-generated method stub
        return AuthApi.super.getAccessToken(refreshToken);
    }

    @Override
    public ResponseEntity<SignedInUserDTO> signIn(@Valid SignInDTO signInReq) {
        // TODO Auto-generated method stub
        return AuthApi.super.signIn(signInReq);
    }

    @Override
    public ResponseEntity<Void> signOut(@Valid RefreshTokenDTO refreshToken) {
        // TODO Auto-generated method stub
        return AuthApi.super.signOut(refreshToken);
    }

    @Override
    public ResponseEntity<SignedInUserDTO> signUp(@Valid UserDTO user) {
        // TODO Auto-generated method stub
        return AuthApi.super.signUp(user);
    }
    
}
