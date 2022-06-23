package com.example.todoapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.database.ToDoList

class ToDoDiffCallback (private val mOldToDoList: List<ToDoList>, private val mNewToDoList: List<ToDoList>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldToDoList.size
    }

    override fun getNewListSize(): Int {
        return mNewToDoList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldToDoList[oldItemPosition].id == mNewToDoList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldToDoList[oldItemPosition]
        val newEmployee = mNewToDoList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.description == newEmployee.description
    }

}