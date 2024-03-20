package com.katas.supermarket.api

import com.katas.supermarket.app.domain.Product
import com.katas.supermarket.app.domain.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListProductsController {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @GetMapping("/products")
    fun listProducts(): ResponseEntity<List<Product>> {
        val products = productRepository.findAll()
        return ResponseEntity(products, HttpStatus.OK)
    }

}
