package com.al4apps.courses.utils

import android.content.Context
import com.al4apps.courses.R
import com.al4apps.courses.presentation.viewmodels.CustomDate

class CustomDateFormatter(private val context: Context) {
    fun getFormattedDate(date: String): String {
        val customDate = getCustomDate(date)
        val monthName = getMonthName(customDate.month)
        return context.getString(R.string.date_with_month, customDate.day, monthName, customDate.year)
    }

    private fun getMonthName(month: Int): String {
        val monthStringId = when (month) {
            1 -> R.string.month_january
            2 -> R.string.month_february
            3 -> R.string.month_march
            4 -> R.string.month_april
            5 -> R.string.month_may
            6 -> R.string.month_june
            7 -> R.string.month_july
            8 -> R.string.month_august
            9 -> R.string.month_september
            10 -> R.string.month_october
            11 -> R.string.month_november
            12 -> R.string.month_december
            else -> throw IllegalArgumentException("Invalid month number: $month")
        }
        return context.getString(monthStringId)
    }

    private fun getCustomDate(date: String): CustomDate {
        val year = date.substringBefore("-")
        val month = date.substringAfter("-").substringBefore("-")
        val day = date.substringAfterLast("-")
        return CustomDate(day.toInt(), month.toInt(), year.toInt())
    }
}