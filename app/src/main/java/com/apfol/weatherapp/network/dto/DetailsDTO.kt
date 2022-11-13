package com.apfol.weatherapp.network.dto

import com.avility.weatherboldapp.data.remote.dto.Location
import com.google.gson.annotations.SerializedName

data class DetailsDTO(
    @SerializedName("location") var location: Location,
    @SerializedName("current") var current: Current,
    @SerializedName("forecast") var forecast: Forecast
)
