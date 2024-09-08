package com.example.dependaBotTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DependaBotTestApplication

fun main(args: Array<String>) {
	runApplication<DependaBotTestApplication>(*args)
	println("Hello")
}
