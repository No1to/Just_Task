package com.example.just_task.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.just_task.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query(" SELECT * FROM task ORDER BY taskId DESC ")
    fun getAll(): List<Task>
}