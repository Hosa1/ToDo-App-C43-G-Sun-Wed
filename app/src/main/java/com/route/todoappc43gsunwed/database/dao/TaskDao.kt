package com.route.todoappc43gsunwed.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todoappc43gsunwed.database.Task
import java.util.Date

//
@Dao
interface TaskDao { //        Room
    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Task WHERE date = :date")
    fun getTasksByDate(date: Date): List<Task>
}
