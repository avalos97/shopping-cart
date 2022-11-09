package com.pack.shopping.cart.api.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pack.shopping.cart.api.dto.ProductDTO;


@FeignClient(name = "products", url = "https://fakestoreapi.com", path = "/products")
public interface ProductServiceClient {
    
    @GetMapping
    List<ProductDTO> getAllProducts();

    @GetMapping("/{id}")
    ProductDTO getASingleProduct(@PathVariable(name = "id") Integer id);
}
