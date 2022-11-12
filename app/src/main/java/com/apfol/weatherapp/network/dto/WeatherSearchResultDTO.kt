package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.WeatherSearchResult
import com.google.gson.annotations.SerializedName

data class WeatherSearchResultDTO(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double,
    @SerializedName("url")
    var url: String
)

fun WeatherSearchResultDTO.toWeatherSearchResultModel() = WeatherSearchResult(
    name,
    country
)
