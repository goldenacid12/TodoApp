package com.example.todoapp.screen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.ListAdapter
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.viewmodel.MainViewModel
import com.example.todoapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupAction()
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter -> {
                true
            }
            R.id.settings -> {
                val settings = Intent(this, SettingActivity::class.java)
                startActivity(settings)
                true
            }
            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    private fun setupAction(){
        supportActionBar?.title = getString(R.string.todo_app)

        adapter = ListAdapter()
        binding?.recycler?.layoutManager = LinearLayoutManager(this)
        binding?.recycler?.setHasFixedSize(true)
        binding?.recycler?.adapter = adapter

        binding?.fab?.setOnClickListener {
            val add = Intent(this, AddTaskActivity::class.java)
            startActivity(add)
        }
    }

    private fun setupViewModel(){
        val mainViewModel = obtainViewModel(this)
        mainViewModel.getAllToDo().observe(this) { ToDoList ->
            if (ToDoList != null) {
                adapter.setListToDo(ToDoList)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}