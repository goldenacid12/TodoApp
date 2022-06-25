package com.example.todoapp.screen

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.database.ToDoList
import com.example.todoapp.databinding.ActivityDetailBinding
import com.example.todoapp.viewmodel.DetailViewModel
import com.example.todoapp.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    private var _activityDetailBinding : ActivityDetailBinding? = null
    private val binding get() = _activityDetailBinding

    private var todo: ToDoList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.empty_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val title = binding?.detailEdTitle?.text.toString().trim()
        val desc = binding?.detailEdDescription?.text.toString().trim()
        val date = binding?.detailEdDueDate?.text.toString().trim()
        finish()
        return true
    }

    private fun setupAction(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.todo_app)
        detailViewModel = obtainViewModel(this)

        todo = intent.getParcelableExtra(EXTRA_TODO)
        if(todo != null){
            todo?.let { todo ->
                binding?.detailEdTitle?.setText(todo.title)
                binding?.detailEdDescription?.setText(todo.description)
                binding?.detailEdDueDate?.setText(todo.date)
            }
        }

        binding?.delete?.setOnClickListener {
            detailViewModel.delete(todo as ToDoList)
            finish()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    companion object{
        const val EXTRA_TODO = "extra"
    }
}