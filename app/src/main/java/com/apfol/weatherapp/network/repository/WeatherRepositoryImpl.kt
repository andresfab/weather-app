package com.apfol.weatherapp.network.repository

import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.WeatherAPI
import com.apfol.weatherapp.network.dto.WeatherDetailsDTO
import com.apfol.weatherapp.network.dto.WeatherSearchResultDTO
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
): WeatherRepository {

    override suspend fun getResultsForSearch(query: String): List<WeatherSearchResultDTO> =
        api.getResultOfSearch(query)

    override suspend fun getWeatherDetails(name: String): WeatherDetailsDTO =
        api.getWeatherDetails(name)

}
