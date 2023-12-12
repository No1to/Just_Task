package com.example.just_task.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.just_task.App
import com.example.just_task.R
import com.example.just_task.databinding.FragmentHomeBinding
import com.example.just_task.model.Task
import com.example.just_task.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClick, this::onClick)
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
        setData()
        binding.fabTask.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить?")
        alertDialog.setMessage("Вы уверены, что хотите удалить задачу?")
        alertDialog.setNegativeButton("Нет") { dialog, _ ->
            dialog.cancel()
        }
        alertDialog.setPositiveButton("Да") { dialog, _ ->
            App.db.taskDao().delete(task)
            setData()
            dialog.dismiss()
        }
        alertDialog.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_EDIT_KEY to task))
    }

    companion object{
        const val TASK_EDIT_KEY = "task.edit.key"
    }

}