package com.apfol.weatherapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apfol.weatherapp.ui.theme.Blue200
import hu.ma.charts.line.data.AxisLabel
import hu.ma.charts.line.data.LineChartData

private val pointsList = listOf(
    LineChartData.SeriesData.Point(0, 0f),
    LineChartData.SeriesData.Point(1, 10.0f),
    LineChartData.SeriesData.Point(2, 20.0f),
    LineChartData.SeriesData.Point(3, 30.0f),
    LineChartData.SeriesData.Point(4, 50.0f),
    LineChartData.SeriesData.Point(5, 35.0f),
)

fun getLineChartData(
    seriesDataPoints: List<LineChartData.SeriesData.Point> = pointsList
) = LineChartData(
    series = listOf(
        LineChartData.SeriesData(
            title = "Line A",
            points = seriesDataPoints,
            Blue200,
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
        AxisLabel(35f, "35°C"),
        AxisLabel(40f, "40°C"),
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
