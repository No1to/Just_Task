package com.example.just_task.ui.profile

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.just_task.R
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
        saveData()
        passwordFocusListener()
        binding.ibLogOut.setOnClickListener {
            val logOutDialog = AlertDialog.Builder(requireContext())
            logOutDialog.setTitle("Выйти из аккаунта")
            logOutDialog.setMessage("Вы уверены, что хотите выйти?")
            logOutDialog.setPositiveButton("Да") { _, _ ->
                findNavController().navigate(R.id.phoneFragment)
            }
            logOutDialog.setNegativeButton("Нет") { _, _ -> }
            .show()
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
            /* ImagePicker.with(this)
                 .crop()                    //Crop image(Optional), Check Customization for more option
                 .compress(1024)            //Final image size will be less than 1 MB(Optional)
                 .maxResultSize(
                     1080,
                     1080
                 )    //Final image resolution will be less than 1080 x 1080(Optional)
                 .start()
             pref.getPicture()*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data

            pref.setPicture(imageUri.toString())
            val savedImagePath = pref.getPicture()
            if (savedImagePath != null) {
                if (savedImagePath.isNotEmpty()) {
                    binding.profileImage.setImageURI(Uri.parse(savedImagePath))
                }
            }
          //pref.getPicture(binding.profileImage.setImageURI(imageUri))
          //binding.profileImage.setImageURI(imageUri)
        }
    }
    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (resultCode == Activity.RESULT_OK) {
             //Image Uri will not be null for RESULT_OK
             val imageUri: Uri? = data?.data

             // Use Uri object instead of File to avoid storage permissions
             pref.setPicture(imageUri.toString())
             binding.profileImage.setImageURI(imageUri)
         } else if (resultCode == ImagePicker.RESULT_ERROR) {
             makeToast(ImagePicker.getError(data))
         } else {
             makeToast("Task Cancelled")
         }
     }*/

    private fun saveData() {
        binding.etName.setText(pref.getName())
        binding.etPassword.setText(pref.getPassword())

        binding.etName.addTextChangedListener {
            pref.setName(binding.etName.text.toString())
        }
        binding.etPassword.addTextChangedListener {
            pref.setPassword(binding.etPassword.text.toString())
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

    companion object {
        const val PICK_IMAGE = 1

    }
}