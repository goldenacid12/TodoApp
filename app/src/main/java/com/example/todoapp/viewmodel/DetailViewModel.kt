package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.todoapp.database.ToDoList
import com.example.todoapp.repository.ToDoRepository

class DetailViewModel(application: Application): ViewModel() {
    private val mToDoRepository: ToDoRepository = ToDoRepository(application)

    fun update(todo: ToDoList){
        mToDoRepository.update(todo )
    }

    fun delete(todo: ToDoList){
        mToDoRepository.delete(todo)
    }
}