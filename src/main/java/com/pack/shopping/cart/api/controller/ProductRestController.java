package com.pack.shopping.cart.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import com.pack.shopping.cart.api.apiInterface.ProductApi;
import com.pack.shopping.cart.api.dto.ProductDTO;
import com.pack.shopping.cart.api.service.ProductService;

@RestController
public class ProductRestController implements ProductApi {

    private ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ProductDTO> getProduct(Integer id) {
        return ok(this.service.getASingleProduct(id));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> queryProducts() {
        return ok(this.service.getAllProducts());
    }

}
