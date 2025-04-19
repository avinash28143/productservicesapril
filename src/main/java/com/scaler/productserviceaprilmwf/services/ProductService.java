package com.scaler.productserviceaprilmwf.services;


import com.scaler.productserviceaprilmwf.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
    Product addNewProduct(Product product);
}
