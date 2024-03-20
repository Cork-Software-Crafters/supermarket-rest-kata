package com.katas.supermarket.api;

import com.katas.supermarket.app.domain.Product;
import com.katas.supermarket.app.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
