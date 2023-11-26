package com.example.just_task.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.just_task.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}