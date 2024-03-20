package com.katas.supermarket.app.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String>
