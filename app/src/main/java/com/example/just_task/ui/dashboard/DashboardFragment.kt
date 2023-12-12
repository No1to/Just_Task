package com.example.just_task.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.just_task.R
import com.example.just_task.databinding.FragmentDashboardBinding
import com.example.just_task.model.Book
import com.example.just_task.utils.makeToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAdd.setOnClickListener {
                val data = Book(
                    name = etName.text.toString(),
                    author = etAuthor.text.toString()
                )
                db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .add(data)
                    .addOnSuccessListener {
                        etName.text?.clear()
                        etAuthor.text?.clear()
                        makeToast(getString(R.string.success_book_saved))
                    }
                    .addOnFailureListener {
                        makeToast(it.message.toString())
                    }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}