package com.example.todoapp.data

import android.app.Application
import androidx.lifecycle.LiveData

class TodoRepository(private val application: Application) {
    private val todoDao: TodoDao = TodoRoomDatabase.getDatabase(application).todoDao()

    val getAllTodos: LiveData<List<TodoEntity>> = todoDao.getAllTodos()

    suspend fun addTodo(todo: TodoEntity) {
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: TodoEntity) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoEntity) {
        todoDao.deleteTodo(todo)
    }
}
