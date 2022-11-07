package com.pack.shopping.cart.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.PaymentApi;
import com.pack.shopping.cart.api.dto.AuthorizationDTO;
import com.pack.shopping.cart.api.dto.PaymentDetailDTO;

import io.swagger.annotations.Authorization;

@RestController
public class PaymentRestController implements PaymentApi{

    @Override
    public ResponseEntity<AuthorizationDTO> authorize(@Valid PaymentDetailDTO paymentReq) {
        // TODO Auto-generated method stub
        return PaymentApi.super.authorize(paymentReq);
    }

    @Override
    public ResponseEntity<Authorization> getOrdersPaymentAuthorization(@NotNull @Valid String orderId) {
        // TODO Auto-generated method stub
        return PaymentApi.super.getOrdersPaymentAuthorization(orderId);
    }
    
}
