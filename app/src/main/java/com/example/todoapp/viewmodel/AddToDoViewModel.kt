package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.todoapp.database.ToDoList
import com.example.todoapp.repository.ToDoRepository

class AddToDoViewModel(application: Application): ViewModel() {
    private val mToDoRepository: ToDoRepository = ToDoRepository(application)

    fun insert(todo: ToDoList){
        mToDoRepository.insert(todo)
    }

    fun delete(todo: ToDoList){
        mToDoRepository.delete(todo)
    }

    fun update(todo: ToDoList){
        mToDoRepository.update(todo )
    }
}