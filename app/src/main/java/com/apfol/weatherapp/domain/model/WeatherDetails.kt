package com.apfol.weatherapp.domain.model

data class WeatherDetails (
    val location: Location,
    val weathers: List<Weather>
)
