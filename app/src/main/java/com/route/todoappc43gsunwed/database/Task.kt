package com.route.todoappc43gsunwed.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val date: Date? = null,
    val isDone: Boolean? = false
//    @ColumnInfo
) {
    @Ignore
    val description: String? = null
}

// DAO (Data Access Object )
