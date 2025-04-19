package com.scaler.productserviceaprilmwf.controllers;


import com.scaler.productserviceaprilmwf.models.Product;
import com.scaler.productserviceaprilmwf.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    @Autowired
    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){

        ResponseEntity response = new ResponseEntity(
                productService.getAllProducts(),
                HttpStatus.OK);

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id ){

        ResponseEntity response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.FORBIDDEN
        );

        return response;
    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){

        ResponseEntity response = new ResponseEntity<>(
                productService.addNewProduct(product),
                HttpStatus.OK
        );
        return response;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product ){
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return new Product();
    }
}
