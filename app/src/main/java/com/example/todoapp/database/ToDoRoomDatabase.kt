package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoList::class], version = 1)
abstract class ToDoRoomDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object{
        @Volatile
        private var INSTANCE: ToDoRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ToDoRoomDatabase{
            if (INSTANCE == null){
                synchronized(ToDoRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ToDoRoomDatabase::class.java, "todolist_database").build()
                }
            }
            return INSTANCE as ToDoRoomDatabase
        }
    }
}