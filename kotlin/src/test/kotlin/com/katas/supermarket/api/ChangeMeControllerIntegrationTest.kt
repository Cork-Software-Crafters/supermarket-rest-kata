package com.katas.supermarket.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ChangeMeController::class)
class ChangeMeControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should not calculate anything if the cart is empty of products`() {
        val result = mockMvc.perform(
                post("/checkout").content("""{ "skus": [] }""")
        )

        result.andExpect(status().isNoContent)
    }
}
