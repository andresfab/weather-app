package com.apfol.weatherapp.network

import com.apfol.weatherapp.network.dto.WeatherDetailsDTO
import com.apfol.weatherapp.network.dto.WeatherSearchResultDTO
import com.apfol.weatherapp.utils.Constants.API_KEY
import com.apfol.weatherapp.utils.Constants.WEATHER_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("search.json")
    suspend fun getResultOfSearch(
        @Query("q") query: String,
        @Query("key") key: String = API_KEY
    ): List<WeatherSearchResultDTO>

    @GET("forecast.json")
    suspend fun getWeatherDetails(
        @Query("q") woeid: String,
        @Query("days") days: Int = WEATHER_DAYS,
        @Query("key") key: String = API_KEY
    ): WeatherDetailsDTO
}
