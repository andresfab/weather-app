package com.apfol.weatherapp.domain.repository

import com.apfol.weatherapp.network.dto.WeatherDetailsDTO
import com.apfol.weatherapp.network.dto.WeatherSearchResultDTO

interface WeatherRepository {
    suspend fun getResultsForSearch(query: String): List<WeatherSearchResultDTO>
    suspend fun getWeatherDetails(name: String): WeatherDetailsDTO
}
