package com.example.todo.controller

import com.example.todo.models.Todo
import com.example.todo.services.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class TodoController {

    @Autowired
    lateinit var todoService: TodoService

    @PostMapping("/todo")
    fun saveTodo(@Validated @RequestBody todo: Todo) : Todo {
        return todoService.saveTodo(todo)
    }

    @GetMapping("/todo")
    fun getAll() : List<Todo>? {
        return todoService.getAll();
    }

    @PutMapping("/todo/{id}")
    fun updateTodo(
        @PathVariable("id") todoId : Long,
        @Validated @RequestBody todo: Todo
    ) : Todo {
        return todoService.updateTodo(todo, todoId);
    }

    @DeleteMapping("/todo/{id}")
    fun deleteTodoById(
        @PathVariable("id") todoId : Long
    ) : ResponseEntity<Map<String, String>> {
        return todoService.deleteById(todoId);
    }
}