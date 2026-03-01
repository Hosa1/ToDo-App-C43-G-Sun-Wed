package com.route.todoappc43gsunwed.extension

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Calendar.HOUR
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MILLISECOND
import java.util.Calendar.MINUTE
import java.util.Calendar.SECOND


fun Calendar.clearTime() {
    set(HOUR_OF_DAY, 0)
    set(HOUR, 0)
    set(MINUTE, 0)
    set(SECOND, 0)
    set(MILLISECOND, 0)
}

fun LocalDate.toCalendarInstant(): Instant {
    return atStartOfDay(ZoneId.systemDefault()).toInstant()
}
