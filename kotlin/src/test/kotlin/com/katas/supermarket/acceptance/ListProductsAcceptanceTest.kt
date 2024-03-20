package com.katas.supermarket.acceptance

import com.katas.supermarket.app.domain.Product
import com.katas.supermarket.app.domain.ProductRepository
import net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListProductsAcceptanceTest {
    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun `should list products in stock`() {
        // Given
        productRepository.save(Product("A", BigDecimal("2.00"), "Milk"))
        productRepository.save(Product("B", BigDecimal("4.00"), "Orange Juice"))

        // When
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val responseEntity = restTemplate.exchange(
                "http://localhost:$port/products",
                HttpMethod.GET,
                HttpEntity<Any>(headers),
                String::class.java
        )

        // Then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThatJson(responseEntity.body).isEqualTo("""
                [
                  {
                    "sku": "A",
                    "price": 2.00,
                    "description": "Milk"
                  },
                  {
                    "sku": "B",
                    "price": 4.00,
                    "description": "Orange Juice"
                  }
                ]
                
                """.trimIndent())
    }
}
