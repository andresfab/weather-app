package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.Location
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.avility.weatherboldapp.data.remote.dto.LocationDTO
import com.google.gson.annotations.SerializedName

data class WeatherDetailsDTO(
    @SerializedName("location") var locationDTO: LocationDTO,
    @SerializedName("current") var current: Current,
    @SerializedName("forecast") var forecast: Forecast
)

fun WeatherDetailsDTO.toWeatherDetails(): WeatherDetails {
    return WeatherDetails(
        Location(locationDTO.name, locationDTO.country),
        forecast.forecastday.map { it.toWeather() }
    )
}
