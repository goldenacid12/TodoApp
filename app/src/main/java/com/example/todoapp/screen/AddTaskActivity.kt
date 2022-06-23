package com.example.todoapp.screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.database.ToDoList
import com.example.todoapp.databinding.ActivityAddTaskBinding
import com.example.todoapp.viewmodel.AddToDoViewModel
import com.example.todoapp.viewmodel.ViewModelFactory
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var addToDoViewModel: AddToDoViewModel

    private var _activityAddTaskBinding: ActivityAddTaskBinding? = null
    private val binding get() = _activityAddTaskBinding

    private var toDo: ToDoList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAddTaskBinding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        addToDoViewModel = obtainViewModel(this)

        toDo = intent.getParcelableExtra(EXTRA_TODO)


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
                val title = binding?.addEdTitle?.text.toString().trim()
                val desc = binding?.addEdDescription?.text.toString().trim()
                val date = binding?.dateView?.text.toString().trim()

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
                            toDo?.date = date
                        }
                    }
                }
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

    fun showDatePicker(view: View){
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            "$dayOfMonth/${monthOfYear + 1}/$year".also { binding?.dateView?.text = it }
        }, y, m, d)
        dpd.datePicker.minDate = System.currentTimeMillis()
        dpd.show()
    }

    companion object{
        const val EXTRA_TODO = "TODO"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}