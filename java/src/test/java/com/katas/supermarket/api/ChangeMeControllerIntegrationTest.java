package com.katas.supermarket.api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@WebMvcTest(ChangeMeController.class)
public class ChangeMeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotCalculateAnythingIfCartIsEmpty() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/checkout").content("{ \"skus\": [] }")
        ).andExpect(status().isNoContent());
    }
}
