package com.katas.supermarket.acceptance;

import com.katas.supermarket.app.domain.Product;
import com.katas.supermarket.app.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListProductsAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_list_products_in_stock() {
        // Given
        productRepository.save(new Product("A", new BigDecimal("2.00"), "Milk"));
        productRepository.save(new Product("B", new BigDecimal("4.00"), "Orange Juice"));

        // When
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/products",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThatJson(responseEntity.getBody()).isEqualTo("""
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
                """);
    }
}
