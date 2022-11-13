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

fun List<Hour>.toLineChartData(): LineChartData {
    var count = -1
    val weatherPoints = this.map {
        count++
        LineChartData.SeriesData.Point(
            count,
            it.tempC
        )
    }.toList()

    return LineChartData(
        series = listOf(
            LineChartData.SeriesData(
                title = "Line A",
                points = weatherPoints,
                Blue500,
                gradientFill = true
            ),
        ),
        yLabels = listOf(
            AxisLabel(0f, "0°C"),
            AxisLabel(5f, "5°C"),
            AxisLabel(10f, "10°C"),
            AxisLabel(15f, "15°C"),
            AxisLabel(20f, "20°C"),
            AxisLabel(25f, "25°C"),
            AxisLabel(30f, "30°C"),
            AxisLabel(35f, "35°C")
        ),
        xLabels = listOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
        ),
        horizontalLines = true,
        xAxisTypeface = TextStyle(
            fontSize = 8.sp,
            color = Color.Gray,
            fontWeight = FontWeight.ExtraBold
        ),
        yAxisTypeface = TextStyle(
            fontSize = 8.sp,
            color = Color.Gray,
            fontWeight = FontWeight.ExtraBold
        )
    )
}
