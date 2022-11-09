package com.pack.shopping.cart.api.service;

import java.util.List;

import com.pack.shopping.cart.api.dto.ProductDTO;

public interface ProductService {
    
    public List<ProductDTO> getAllProducts();
    
    public ProductDTO getASingleProduct(Integer id);
    
}
