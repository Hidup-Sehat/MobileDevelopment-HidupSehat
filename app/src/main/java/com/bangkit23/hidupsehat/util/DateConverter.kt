package com.bangkit23.hidupsehat.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateConverter {
    fun convertMillisToString(timeMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMillis
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(calendar.time)
    }
}