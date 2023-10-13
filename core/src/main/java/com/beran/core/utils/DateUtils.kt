package com.beran.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun getTimeAgo(timeStamp: String): String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val parsedTime: Date = dateFormat.parse(timeStamp)
            val currentDate = Date()
            val timeDifferenceMillis: Long = currentDate.time - parsedTime.time
            // ** calculate
            val seconds = timeDifferenceMillis / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            return when{
                days > 0 -> "$days hari lalu"
                hours > 0 -> "$hours jam lalu"
                minutes > 0 -> "$minutes menit lalu"
                else -> "baru saja"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Undefined"
        }
    }

    fun getCurrentDayDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, MMMM dd yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}