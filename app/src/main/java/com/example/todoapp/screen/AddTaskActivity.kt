package com.example.todoapp.screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.database.ToDoList
import com.example.todoapp.databinding.ActivityAddTaskBinding
import com.example.todoapp.viewmodel.AddToDoViewModel
import com.example.todoapp.viewmodel.ViewModelFactory

class AddTaskActivity : AppCompatActivity() {

    private lateinit var addToDoViewModel: AddToDoViewModel

    private var _activityAddTaskBinding: ActivityAddTaskBinding? = null
    private val binding get() = _activityAddTaskBinding

    private var isEdit = false
    private var toDo: ToDoList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAddTaskBinding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        addToDoViewModel = obtainViewModel(this)

        toDo = intent.getParcelableExtra(EXTRA_TODO)

        val title = binding?.addEdTitle.toString().trim()
        val desc = binding?.addEdDescription.toString().trim()

        when{
            title.isEmpty() -> {
                binding?.addEdTitle?.error = getString(R.string.field_blank)
            }
            desc.isEmpty() -> {
                binding?.addEdDescription?.error = getString(R.string.field_blank)
            }
            else -> {
                toDo.let{ toDo ->
                    toDo?.title = title
                    toDo?.description = desc
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _activityAddTaskBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                addToDoViewModel.insert(toDo as ToDoList)
                finish()
                true
            }
            else -> true
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): AddToDoViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[AddToDoViewModel::class.java]
    }

    companion object{
        const val EXTRA_TODO = "TODO"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}