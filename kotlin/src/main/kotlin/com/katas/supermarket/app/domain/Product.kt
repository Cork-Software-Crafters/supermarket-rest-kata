package com.katas.supermarket.app.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class Product(
        @Id
        val sku: String,
        val price: BigDecimal,
        val description: String
)
