package com.apfol.weatherapp.domain.model

data class WeatherDetails (
    val weather: Weather,
    val currentWeather: CurrentWeather,
    val nextWeathers: List<CurrentWeather>
)
