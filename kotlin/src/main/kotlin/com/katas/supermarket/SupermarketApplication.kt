package com.katas.supermarket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SupermarketApplication

fun main(args: Array<String>) {
	runApplication<SupermarketApplication>(*args)
}
