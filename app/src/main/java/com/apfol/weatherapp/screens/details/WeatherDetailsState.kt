package com.apfol.weatherapp.screens.details

import com.apfol.weatherapp.domain.model.WeatherDetails

data class WeatherDetailsState(
    val isLoading: Boolean = false,
    val weatherDetails: WeatherDetails? = null,
    val error: String = ""
)
