package com.apfol.weatherapp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.apfol.weatherapp.domain.model.Hour
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.domain.model.Location
import com.apfol.weatherapp.domain.model.WeatherDetails

class WeatherDetailsParameterProvider: PreviewParameterProvider<WeatherDetails> {

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

    override val values: Sequence<WeatherDetails>
        get() = sequenceOf(
            WeatherDetails(
                location = Location("Bogota", "Colombia"),
                weathers = listOf(
                    weather.copy(weatherState = "Sunny"),
                    weather.copy(weatherState = "Heavy rain")
                )
            )
        )
}
