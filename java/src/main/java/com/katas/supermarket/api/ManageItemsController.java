package com.katas.supermarket.api;

import com.katas.supermarket.app.domain.Item;
import com.katas.supermarket.app.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageItemsController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> listItems() {
        List<Item> items = itemRepository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
}
