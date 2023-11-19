package com.example.just_task.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.just_task.databinding.FragmentTaskBinding
import com.example.just_task.model.TaskModel

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

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isEmpty() && description.isEmpty()) {
                Toast.makeText(requireContext(), "Все поля пустые!", Toast.LENGTH_SHORT).show()
            } else {
                val data = TaskModel(title = title, description = description)
                setFragmentResult(SAVE_RESULT_KEY, bundleOf(TASK_KEY to data))
                findNavController().navigateUp()
            }
        }
    }

    companion object {
        const val SAVE_RESULT_KEY = "task.result.key"
        const val TASK_KEY = "task.key"
    }
}