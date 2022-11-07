package com.pack.shopping.cart.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pack.shopping.cart.api.apiInterface.ProductApi;
import com.pack.shopping.cart.api.dto.ProductDTO;

@RestController
public class ProductRestController implements ProductApi {

    @Override
    public ResponseEntity<ProductDTO> getProduct(String id) {
        // TODO Auto-generated method stub
        return ProductApi.super.getProduct(id);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> queryProducts(@Valid String name, @Valid Integer page,
            @Valid Integer size) {
        // TODO Auto-generated method stub
        return ProductApi.super.queryProducts(name, page, size);
    }

}
