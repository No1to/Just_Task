package com.example.just_task.model

import java.io.Serializable

data class OnBoarding(
    val image: String? = "",
    val title: String? = "",
    val description: String? = ""
) : Serializable
