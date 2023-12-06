package com.example.just_task.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.makeToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}