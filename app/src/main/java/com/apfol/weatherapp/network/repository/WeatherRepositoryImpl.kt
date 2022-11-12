package com.apfol.weatherapp.network.repository

import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.WeatherAPI
import com.apfol.weatherapp.network.dto.WeatherSearchResultDTO
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
): WeatherRepository {

    override suspend fun getResultsForSearch(query: String): List<WeatherSearchResultDTO> {
        return api.getResultOfSearch(query)
    }

}
