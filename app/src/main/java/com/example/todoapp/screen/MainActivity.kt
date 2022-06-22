package com.example.todoapp.screen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.ListAdapter
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
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

    private fun setupAction(){
        binding.recycler.layoutManager = LinearLayoutManager(this)
        val listAdapter = ListAdapter()

        binding.fab.setOnClickListener {
            val add = Intent(this, AddTaskActivity::class.java)
            startActivity(add)
        }
    }
}