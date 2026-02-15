package com.route.todoappc43gsunwed.database.typeConverter

import androidx.room.TypeConverter
import java.util.Date

class TaskDateConverter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(date: Long): Date {
        return Date(date)
    }
}
