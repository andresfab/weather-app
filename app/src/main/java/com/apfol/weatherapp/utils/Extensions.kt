package com.apfol.weatherapp.utils

import com.apfol.weatherapp.domain.model.Weather
import org.threeten.bp.LocalDate
import java.util.Locale

fun String.weekDayStringFromDateString(): String {
    val date = LocalDate.parse(this)
    return date.dayOfWeek.name.lowercase(Locale.ROOT)
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
}

/**
 * First weather is removed because is the current day.
 */
fun List<Weather>.getNextDaysWeathers(): List<Weather> {
    val mutableList = this.toMutableList()
    mutableList.removeFirst()
    return mutableList.toList()
}
