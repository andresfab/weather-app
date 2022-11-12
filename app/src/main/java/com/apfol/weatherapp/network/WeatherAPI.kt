package com.apfol.weatherapp.network

import com.apfol.weatherapp.network.dto.WeatherSearchResultDTO
import com.apfol.weatherapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("search.json")
    suspend fun getResultOfSearch(
        @Query("q") query: String,
        @Query("key") key: String = API_KEY
    ): List<WeatherSearchResultDTO>
}
