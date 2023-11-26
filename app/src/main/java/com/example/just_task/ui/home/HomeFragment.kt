package com.example.just_task.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.just_task.App
import com.example.just_task.R
import com.example.just_task.databinding.FragmentHomeBinding
import com.example.just_task.model.Task
import com.example.just_task.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClick)
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
        // Настройка RecyclerView и слушателей
        binding.rvTask.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    // Загрузка данных из базы данных и обновление RecyclerView
    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    // Обработчик долгого нажатия для элементов списка
    private fun onLongClick(task: Task) {
        // Создание диалогового окна для подтверждения удаления
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить?")
        alertDialog.setMessage("Вы уверены, что хотите удалить задачу?")

        // Обработчик кнопки "Нет"
        alertDialog.setNegativeButton("Нет") { dialog, _ ->
            dialog.cancel()
        }

        // Обработчик кнопки "Да"
        alertDialog.setPositiveButton("Да") { dialog, _ ->
            // Удаление задачи из базы данных и обновление RecyclerView
            App.db.taskDao().delete(task)
            setData()
            dialog.dismiss()
        }

        // Отображение диалогового окна
        alertDialog.create().show()
    }

    // Очистка привязки при уничтожении фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}