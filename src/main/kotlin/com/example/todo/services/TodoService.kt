package com.example.todo.services

import com.example.todo.models.DataNotFound
import com.example.todo.models.Todo
import com.example.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TodoService {

    @Autowired
    lateinit var todoRepo : TodoRepository

    fun saveTodo(todoEntity: Todo) : Todo {
        return todoRepo.save(todoEntity);
    }

    fun updateTodo(todoEntity: Todo, todoId: Long) : Todo {
        if (todoEntity.id == todoId) {
            return try {
                val newTodo: Todo = todoRepo.getReferenceById(todoId);
                newTodo.title = todoEntity.title;
                newTodo.description = todoEntity.description;
                newTodo.progress = todoEntity.progress;
                todoRepo.save(newTodo);
            } catch (e: Exception) {
                throw Exception("Unable to update data with id $todoId")
            }
        }
        throw DataNotFound("Id used not valid")
    }

    fun getAll() : List<Todo>? {
        return todoRepo.findAll();
    }

    fun deleteById(todoId: Long): ResponseEntity<Map<String, String>> {
        return try {
            val todo = todoRepo.findById(todoId)
            if (todo.isEmpty) {
                throw DataNotFound("Unable to delete item, id not found")
            }

            todoRepo.deleteById(todoId)

            val dataMessage: MutableMap<String, String> = HashMap()
            dataMessage["data"] = "Property Removed"
            ResponseEntity.ok(dataMessage)
        } catch (e: DataNotFound) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

}