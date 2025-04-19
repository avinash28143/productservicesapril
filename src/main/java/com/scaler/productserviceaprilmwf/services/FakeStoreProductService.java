package com.scaler.productserviceaprilmwf.services;

import com.scaler.productserviceaprilmwf.dtos.FakeStoreProductDto;
import com.scaler.productserviceaprilmwf.models.Category;
import com.scaler.productserviceaprilmwf.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProduct){
        Product product = new Product();

        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setImageUrl(fakeStoreProduct.getImage());

        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {

        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();

        fakeStoreProduct.setId(product.getId());
        fakeStoreProduct.setTitle(product.getTitle());
        fakeStoreProduct.setDescription(product.getDescription());
        fakeStoreProduct.setPrice(product.getPrice());
        fakeStoreProduct.setImage(product.getImageUrl());
        fakeStoreProduct.setCategory(product.getCategory().getName());

        return fakeStoreProduct;
    }


    @Override
    public Product getSingleProduct(Long id){

        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

//        Product product = convertFakeStoreProductDtoToProduct(fakeStoreProduct);

        return convertFakeStoreProductDtoToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        for (FakeStoreProductDto fakeStoreProduct : fakeStoreProducts) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProduct));
        }

        return products;
    }

    public Product replaceProduct(Long id, Product product){

        RequestCallback requestCallback = restTemplate.httpEntityCallback(convertProductToFakeStoreProductDto(product), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);
    }


    public Product addNewProduct(Product product){

        RequestCallback requestCallback = restTemplate.httpEntityCallback(convertProductToFakeStoreProductDto(product), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());

        FakeStoreProductDto response =  restTemplate.execute("https://fakestoreapi.com/products/", HttpMethod.PUT, requestCallback, responseExtractor);

         return convertFakeStoreProductDtoToProduct(response);
    }
}



