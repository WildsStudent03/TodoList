package com.example.todolist

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    var isDone: Boolean = false
)
