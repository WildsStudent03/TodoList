package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class activity_update_task : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private var taskId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        db = DatabaseHelper(this)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDesc = findViewById<EditText>(R.id.etDescription)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // Ambil data dari intent
        taskId = intent.getIntExtra("taskId", 0)
        etTitle.setText(intent.getStringExtra("taskTitle"))
        etDesc.setText(intent.getStringExtra("taskDesc"))

        btnUpdate.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (title.isNotEmpty()) {
                db.updateTask(Task(id = taskId, title = title, description = desc))
                Toast.makeText(this, "Task diupdate", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "judul tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener {
            db.deleteTask(taskId)
            Toast.makeText(this, "Task dihapus", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
