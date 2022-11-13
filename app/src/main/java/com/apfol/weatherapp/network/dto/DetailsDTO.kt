package com.apfol.weatherapp.network.dto

import com.avility.weatherboldapp.data.remote.dto.LocationDTO
import com.google.gson.annotations.SerializedName

data class DetailsDTO(
    @SerializedName("location") var locationDTO: LocationDTO,
    @SerializedName("current") var current: Current,
    @SerializedName("forecast") var forecast: Forecast
)
