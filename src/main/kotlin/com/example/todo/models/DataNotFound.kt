package com.example.todo.models

class DataNotFound : Exception {

    constructor() : super() {}

    constructor(message: String) : super(message)

}