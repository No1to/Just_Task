package com.example.just_task.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.just_task.App
import com.example.just_task.R
import com.example.just_task.databinding.FragmentTaskBinding
import com.example.just_task.model.Task
import com.example.just_task.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = arguments?.getSerializable(HomeFragment.TASK_EDIT_KEY) as? Task
        if (task != null) {
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(task.title)
            binding.etDescription.setText(task.description)
        }

        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text!!.isNotEmpty()) {
                if (task != null) {
                    update(task)
                } else save()
                findNavController().navigateUp()
            } else binding.etTitle.error = getString(R.string.title_empty_error)
        }

    }

    private fun update(task: Task) {
        App.db.taskDao().update(
            task.copy(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
        )
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

}