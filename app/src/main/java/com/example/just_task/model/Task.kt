package com.example.just_task.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = null,
    val title: String? = "",
    val description: String? = ""
) : Serializable
