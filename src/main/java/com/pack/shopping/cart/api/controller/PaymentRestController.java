package com.pack.shopping.cart.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.PaymentApi;
import com.pack.shopping.cart.api.dto.AuthorizationDTO;
import com.pack.shopping.cart.api.hateoas.PaymentRepresentationModelAssembler;
import com.pack.shopping.cart.api.service.PaymentService;

import io.swagger.annotations.Authorization;

@RestController
public class PaymentRestController implements PaymentApi {

    private final PaymentService paymentService;
    private final PaymentRepresentationModelAssembler assembler;

    public PaymentRestController(PaymentService paymentService, PaymentRepresentationModelAssembler assembler) {
        this.paymentService = paymentService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Authorization> getOrdersPaymentAuthorization(@NotNull @Valid String orderId) {
        // TODO Auto-generated method stub
        return PaymentApi.super.getOrdersPaymentAuthorization(orderId);
    }

    @Override
    public ResponseEntity<AuthorizationDTO> authorize(@Valid AuthorizationDTO authPayment) {
        return ok(this.assembler.toModel(paymentService.addauthorization(authPayment).get()));
    }

}
