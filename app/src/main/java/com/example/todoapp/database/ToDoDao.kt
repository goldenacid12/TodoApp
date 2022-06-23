package com.example.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(toDo: ToDoList)

    @Update
    fun update(toDo: ToDoList)

    @Delete
    fun delete(toDo: ToDoList)

    @Query("SELECT * from ToDoList ORDER BY id ASC")
    fun getAllToDo(): LiveData<List<ToDoList>>
}