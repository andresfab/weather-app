package com.apfol.weatherapp.utils

import com.apfol.weatherapp.domain.model.Hour
import com.apfol.weatherapp.domain.model.Weather
import hu.ma.charts.line.data.LineChartData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

private val weather = Weather(
    weatherState = "Moderate or heavy rain with thunder",
    weatherStateImageURL = "//cdn.weatherapi.com/weather/64x64/night/389.png",
    humidity = 88,
    temperature = 14.0F,
    date = "2022-11-15",
    hours = listOf(
        Hour("00:00", 23F),
        Hour("00:00", 18F)
    )
)

class ExtensionsKtTest {

    @Test
    fun `weekDayStringFromDateString returns week day correctly`() {
        // Given
        val expectedweekDayDay = "Tuesday"

        // When
        val weekDay = weather.date.weekDayStringFromDateString()

        // Then
        assertEquals(expectedweekDayDay, weekDay)
    }

    @Test
    fun `getNextDaysWeathers removes first list item`() {
        // Given
        val weatherList = listOf(
            weather,
            weather.copy(
                humidity = 20
            )
        )

        // When
        val list = weatherList.getNextDaysWeathers()

        // Then
        assertTrue(list.size == weatherList.size - 1)
        assertTrue(weatherList[1] == list.first())
    }

    @Test
    fun `getSeriesDataPointList returns series data points with hours temperature`() {
        // Given
        val hours = weather.hours

        // When
        val seriesDataPoints = hours.toSeriesDataPointList()

        // Then
        assertEquals(hours.size, seriesDataPoints.size)
        assertEquals(
            LineChartData.SeriesData.Point(
                0, hours.first().tempC
            ),
            seriesDataPoints.first()
        )
    }
}
