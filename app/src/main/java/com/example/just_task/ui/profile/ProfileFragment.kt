package com.example.just_task.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.just_task.data.local.Pref
import com.example.just_task.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveName()
        passwordFocusListener()
    }


    private fun saveName() {
        binding.etName.setText(pref.getName())
        binding.etPassword.setText(pref.getPassword())

        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
        binding.etPassword.addTextChangedListener {
            pref.savePassword(binding.etPassword.text.toString())
        }
    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val password = binding.etPassword.text.toString()
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        return null
    }

}