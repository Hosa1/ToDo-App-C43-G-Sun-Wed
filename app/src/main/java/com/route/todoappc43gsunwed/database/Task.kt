package com.route.todoappc43gsunwed.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
     var id: Int? = null,
     var title: String? = null,
     var date: Date? = null,
     var isDone: Boolean? = false,
     var description: String? = null
) : Serializable


