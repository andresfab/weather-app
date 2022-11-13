package com.apfol.weatherapp.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("forecastday") var forecastdaysDTO: List<ForecastdayDTO> = listOf()
)
