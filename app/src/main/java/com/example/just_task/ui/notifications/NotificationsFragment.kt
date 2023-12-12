package com.example.just_task.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.just_task.databinding.FragmentNotificationsBinding
import com.example.just_task.model.Book
import com.example.just_task.ui.notifications.adapter.BookAdapter
import com.example.just_task.utils.makeToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val db = Firebase.firestore
    private val adapter = BookAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvBook.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val list: List<Book> = it.toObjects(Book::class.java)
                adapter.addBooks(list)
            }
            .addOnFailureListener {
                makeToast(it.message.toString())
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}