package com.katas.supermarket.api

import com.katas.supermarket.app.domain.Item
import com.katas.supermarket.app.domain.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ManageItemsController {

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @GetMapping("/items")
    fun listItems(): ResponseEntity<List<Item>> {
        val items = itemRepository.findAll()
        return ResponseEntity(items, HttpStatus.OK)
    }

    @PostMapping("/items")
    fun addItem(@RequestBody item: Item): ResponseEntity<Item> {
        val savedItem = itemRepository.save(item)
        return ResponseEntity(savedItem, HttpStatus.CREATED)
    }

}