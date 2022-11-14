package com.apfol.weatherapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apfol.weatherapp.domain.model.Hour
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.ui.theme.Blue500
import hu.ma.charts.line.data.AxisLabel
import hu.ma.charts.line.data.LineChartData
import org.threeten.bp.LocalDate
import java.util.Locale

/**
 * Returns the weekday name from a date [String].
 */
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

/**
 * Converts the hours list to a series data points list
 * to be used in Line Chart.
 */
fun List<Hour>.toSeriesDataPointList(): List<LineChartData.SeriesData.Point> {
    var count = -1
    val weatherPoints = this.map {
        count++
        LineChartData.SeriesData.Point(
            count,
            it.tempC
        )
    }.toList()
    return weatherPoints
}
