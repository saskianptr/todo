package com.example.todo.config

import com.example.todo.models.DataNotFound
import com.example.todo.models.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomController {

    @ExceptionHandler(Exception::class)
    fun handleExceptions (e : Exception) : ResponseEntity<ErrorResponse> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR

        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                status,
                e.message,
            ),
            status
        )
    }

    @ExceptionHandler(DataNotFound::class)
    fun handleNotFoundException(e: DataNotFound): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        return ResponseEntity(
            ErrorResponse(
                status,
                e.message,
            ),
            status
        )
    }

}