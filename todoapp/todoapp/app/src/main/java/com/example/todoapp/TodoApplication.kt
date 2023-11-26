package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.TodoRoomDatabase

class TodoApplication : Application() {
    val database: TodoRoomDatabase by lazy { TodoRoomDatabase.getDatabase(this)}
}
