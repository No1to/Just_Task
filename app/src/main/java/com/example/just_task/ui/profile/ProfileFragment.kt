package com.example.just_task.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.just_task.data.local.Pref
import com.example.just_task.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        saveNickName()
        saveUserPicture()

    }

    private fun saveUserPicture() {


    }

    private fun saveNickName() {
        binding.etName.setText(pref.getNickName())

        binding.etName.addTextChangedListener {
            pref.setNickName(binding.etName.text.toString())
        }
    }
}