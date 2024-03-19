package com.katas.supermarket.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeMeController {

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
