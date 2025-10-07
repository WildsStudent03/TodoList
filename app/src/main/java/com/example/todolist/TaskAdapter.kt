package com.example.todolist

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.tvTitle.text = task.title
        holder.tvDescription.text = task.description
        holder.checkBoxDone.isChecked = task.isDone

        fun applyStrikeThrough(isDone: Boolean) {
            if (isDone) {
                holder.tvTitle.paintFlags = holder.tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.tvDescription.paintFlags = holder.tvDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.tvTitle.paintFlags = holder.tvTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.tvDescription.paintFlags = holder.tvDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        applyStrikeThrough(task.isDone)


        holder.checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
            task.isDone = isChecked
            applyStrikeThrough(isChecked)
        }


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, activity_update_task::class.java).apply {
                putExtra("taskId", task.id)
                putExtra("taskTitle", task.title)
                putExtra("taskDesc", task.description)
                putExtra("taskDone", task.isDone)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = tasks.size
}
