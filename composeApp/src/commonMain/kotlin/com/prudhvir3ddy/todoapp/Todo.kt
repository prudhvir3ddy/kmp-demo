package com.prudhvir3ddy.todoapp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class Todo(
    val id: Long,
    val name: String,
    val description: String,
    val isDone: Boolean,
) {

    companion object {
        private val _todos = MutableStateFlow(
            listOf(
                Todo(1, "🍲 Cook rice", "Get that carbs in", false),
                Todo(2, "🏋️‍♀️ Hit gym", "Pump the muscle", true)
            )
        )

        val todos: StateFlow<List<Todo>> = _todos

        fun toggleTodo(id: Long) {
            val todo = _todos.value.first { it.id == id }
            val updatedTodo = todo.copy(isDone = todo.isDone.not())
            val indexToUpdate = _todos.value.indexOf(todo)
            val newList = _todos.value.toMutableList()
            newList[indexToUpdate] = updatedTodo
            _todos.update {
                newList
            }
        }
    }
}

