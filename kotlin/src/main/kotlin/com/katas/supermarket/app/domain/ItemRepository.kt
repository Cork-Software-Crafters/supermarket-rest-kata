package com.katas.supermarket.app.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, String>