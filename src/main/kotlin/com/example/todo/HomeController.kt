package com.example.todo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {
    @GetMapping
    fun home(): String {
        return "Hello, Spring Boot is running!"
    }
}
