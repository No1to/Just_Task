package com.example.just_task.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.just_task.databinding.ItemTaskBinding
import com.example.just_task.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val taskList = arrayListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        return holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) = with(binding) {
            tvTitle.text = task.title
            tvDescription.text = task.description
            itemTask.setOnLongClickListener {
                onLongClick(task)
                false
            }
        }
    }

}