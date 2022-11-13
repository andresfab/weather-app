package com.apfol.weatherapp.domain.model

data class CurrentWeather (
    val weatherState: String,
    val weatherStateAbbr: String,
    val humidity: Int,
    val temperature: Float
)
