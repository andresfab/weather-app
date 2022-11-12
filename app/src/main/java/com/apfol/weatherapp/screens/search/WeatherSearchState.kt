package com.apfol.weatherapp.screens.search

import com.apfol.weatherapp.domain.model.WeatherSearchResult

data class WeatherSearchState (
    val isLoading: Boolean = false,
    val results: List<WeatherSearchResult> = emptyList(),
    val error: String = ""
)
