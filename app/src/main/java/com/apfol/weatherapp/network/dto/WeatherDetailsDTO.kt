package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.CurrentWeather
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.avility.weatherboldapp.data.remote.dto.Location
import com.google.gson.annotations.SerializedName

data class WeatherDetailsDTO(
    @SerializedName("location") var location: Location,
    @SerializedName("current") var current: Current,
    @SerializedName("forecast") var forecast: Forecast
)

fun WeatherDetailsDTO.toWeatherDetails(): WeatherDetails {
    return WeatherDetails(
        Weather(location.name, location.country),
        CurrentWeather(
            current.condition.text,
            current.condition.icon,
            current.humidity,
            current.tempC
        ),
        forecast.forecastday.map { it.toSpecificWeather() }
    )
}
