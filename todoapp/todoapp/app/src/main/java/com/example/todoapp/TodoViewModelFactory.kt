package com.example.todoapp

import androidx.lifecycle.ViewModelProvider
import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.TodoRepository

class TodoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel>create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            val repository = TodoRepository(application)//application)
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}