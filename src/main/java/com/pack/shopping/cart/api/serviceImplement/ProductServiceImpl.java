package com.pack.shopping.cart.api.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.shopping.cart.api.clients.ProductServiceClient;
import com.pack.shopping.cart.api.dto.ProductDTO;
import com.pack.shopping.cart.api.exception.ItemNotFoundException;
import com.pack.shopping.cart.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productServiceClient.getAllProducts();
    }

    @Override
    public ProductDTO getASingleProduct(Integer id) {
        return Optional.ofNullable(productServiceClient.getASingleProduct(id)).orElseThrow(() -> new ItemNotFoundException(String.format(" - %s", id)));
    }
    
}
