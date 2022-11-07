package com.pack.shopping.cart.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.CustomerApi;
import com.pack.shopping.cart.api.dto.CardDTO;
import com.pack.shopping.cart.api.dto.UserDTO;

@RestController
public class CustomerRestContoller implements CustomerApi{

    @Override
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        // TODO Auto-generated method stub
        return CustomerApi.super.getAllCustomers();
    }

    @Override
    public ResponseEntity<UserDTO> getCustomerById(String id) {
        // TODO Auto-generated method stub
        return CustomerApi.super.getCustomerById(id);
    }
    
    @Override
    public ResponseEntity<Void> deleteCustomerById(String id) {
        // TODO Auto-generated method stub
        return CustomerApi.super.deleteCustomerById(id);
    }

    @Override
    public ResponseEntity<CardDTO> getCardByCustomerId(String cardId) {
        // TODO Auto-generated method stub
        return CustomerApi.super.getCardByCustomerId(cardId);
    }

    
}
