package com.example.just_task.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.just_task.databinding.ItemTaskBinding
import com.example.just_task.model.TaskModel

class TaskAdapter : Adapter<TaskAdapter.TaskViewHolder>() {

    private val taskList = arrayListOf<TaskModel>()

    fun addTask(task: TaskModel) {
        taskList.add(0, task)
        notifyItemChanged(0)
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

        fun bind(task: TaskModel) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description
        }
    }

}