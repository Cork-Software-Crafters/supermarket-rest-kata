package com.katas.supermarket.acceptance


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

@Tag("acceptance")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculateCartTotalAcceptanceTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Test
    fun `should calculate the total of the cart given a list of skus`() {
        val requestBody =
                """
					{ 
						"skus": ["A","B","A","B","A","A","A"] 
					}
				"""
        val headers = HttpHeaders().apply {
            contentType = org.springframework.http.MediaType.APPLICATION_JSON
        }

        val responseEntity = restTemplate.exchange(
                "http://localhost:$port/checkout",
                HttpMethod.POST,
                HttpEntity(requestBody, headers),
                String::class.java
        )

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(responseEntity.body).isEqualTo(
                """
					{
					  "total": 18.00
					}
				""")
    }
}
