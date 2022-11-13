package com.apfol.weatherapp.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.apfol.weatherapp.domain.model.CurrentWeather
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.domain.model.WeatherDetails

class WeatherDetailsParameterProvider: PreviewParameterProvider<WeatherDetails> {

    private val currentWeather = CurrentWeather(
        weatherState = "Moderate or heavy rain with thunder",
        weatherStateAbbr = "//cdn.weatherapi.com/weather/64x64/night/389.png",
        humidity = 88,
        temperature = 14.0F
    )

    override val values: Sequence<WeatherDetails>
        get() = sequenceOf(
            WeatherDetails(
                weather = Weather("Bogota", "Colombia"),
                currentWeather = currentWeather,
                nextWeathers = listOf(
                    currentWeather,
                    currentWeather.copy(weatherState = "Heavy rain")
                )
            )
        )
}
