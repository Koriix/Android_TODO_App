package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): LiveData<List<TodoEntity>>

    @Insert
    suspend fun addTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todoId: TodoEntity)
}
