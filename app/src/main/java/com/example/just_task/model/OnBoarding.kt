package com.example.just_task.model

import java.io.Serializable

data class OnBoarding(
    val animation: Int? = null,
    val title: String? = null,
    val description: String? = null
) : Serializable