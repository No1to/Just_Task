package com.example.just_task.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.just_task.R
import com.example.just_task.databinding.FragmentHomeBinding
import com.example.just_task.model.TaskModel
import com.example.just_task.ui.home.adapter.TaskAdapter
import com.example.just_task.ui.task.TaskFragment.Companion.SAVE_RESULT_KEY
import com.example.just_task.ui.task.TaskFragment.Companion.TASK_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTask.adapter = adapter
        setFragmentResultListener(SAVE_RESULT_KEY) { _, bundle ->
            val data = bundle.getSerializable(TASK_KEY) as TaskModel
            adapter.addTask(data)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}