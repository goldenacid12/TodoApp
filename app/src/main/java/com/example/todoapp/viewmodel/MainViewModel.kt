package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.database.ToDoList
import com.example.todoapp.repository.ToDoRepository

class MainViewModel(application: Application):ViewModel() {
    private val mToDoRepository: ToDoRepository = ToDoRepository(application)

    fun getAllToDo(): LiveData<List<ToDoList>> = mToDoRepository.getAllToDo()
}