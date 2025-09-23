package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)
        rvTasks = findViewById(R.id.rvTasks)

        rvTasks.layoutManager = LinearLayoutManager(this)
        loadTasks()

        val btnAdd = findViewById<FloatingActionButton>(R.id.btnAddTask)
        btnAdd.setOnClickListener {
            startActivity(Intent(this, activity_create_task::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {
        val tasks = db.getAllTasks()
        rvTasks.adapter = TaskAdapter(tasks)
    }
}
