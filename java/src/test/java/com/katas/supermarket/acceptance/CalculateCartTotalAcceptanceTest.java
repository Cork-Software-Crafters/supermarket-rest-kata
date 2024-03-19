package com.katas.supermarket.acceptance;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("acceptance")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculateCartTotalAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void shouldCalculateTotalOfCartGivenListOfSkus() {
        String requestBody =
                """
                    {
                        "skus": ["A","B","A","B","A","A","A"]
                    }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/checkout",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                String.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isEqualTo("{\"total\": 18.00}");
    }
}
