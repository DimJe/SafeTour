package com.example.domain.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    fun getDateForNetwork(): String {
        var date = LocalDate.now()
        val format = "yyyyMMdd"
        date = date.minusDays(isWeekend(date).toLong())

        return date.format(DateTimeFormatter.ofPattern(format))
    }
    fun getDateForUi(): String {
        var date = LocalDate.now()
        val format = "yyyy.MM.dd"
        date = date.minusDays(isWeekend(date).toLong())

        return date.format(DateTimeFormatter.ofPattern(format))
    }
    private fun isWeekend(date: LocalDate): Int {
        val dayOfWeek = date.dayOfWeek
        return when (dayOfWeek) {
            DayOfWeek.SATURDAY -> 1
            DayOfWeek.SUNDAY -> 2
            else -> 0
        }
    }
}