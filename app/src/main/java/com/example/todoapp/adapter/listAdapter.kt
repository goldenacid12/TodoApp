package com.example.todoapp.adapter

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.custom.TaskTitleView
import com.example.todoapp.database.ToDoList
import com.example.todoapp.databinding.CheckListBinding
import com.example.todoapp.helper.ToDoDiffCallback
import com.example.todoapp.screen.DetailActivity

class ListAdapter: RecyclerView.Adapter<ListAdapter.ToDoViewHolder>() {
    private val listToDo = ArrayList<ToDoList>()

    fun setListToDo(listToDoList: List<ToDoList>){
        val diffCallback = ToDoDiffCallback(this.listToDo, listToDoList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listToDo.clear()
        this.listToDo.addAll(listToDoList)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = CheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(listToDo[position])
    }

    override fun getItemCount(): Int {
        return listToDo.size
    }

    inner class ToDoViewHolder(private val binding: CheckListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (toDo: ToDoList){
            with (binding) {
                binding.title.text = toDo.title
                binding.taskDate.text = toDo.date
                val title = binding.title
                val check = binding.itemCheckbox
                check.setOnCheckedChangeListener { compoundButton, b ->
                    title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }

                listToDo.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TODO, toDo)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}