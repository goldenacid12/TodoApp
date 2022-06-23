package com.example.todoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoapp.database.ToDoDao
import com.example.todoapp.database.ToDoList
import com.example.todoapp.database.ToDoRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ToDoRepository(application: Application) {
    private val mToDoDao: ToDoDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        val db = ToDoRoomDatabase.getDatabase(application)
        mToDoDao = db.todoDao()
    }

    fun getAllToDo(): LiveData<List<ToDoList>> = mToDoDao.getAllToDo()

    fun insert(toDo: ToDoList){
        executorService.execute{ mToDoDao.insert(toDo) }
    }

    fun delete(toDo: ToDoList){
        executorService.execute { mToDoDao.delete(toDo) }
    }

    fun update(toDo: ToDoList){
        executorService.execute { mToDoDao.update(toDo) }
    }
}