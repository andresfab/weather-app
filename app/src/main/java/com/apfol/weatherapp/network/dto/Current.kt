package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.network.dto.Condition
import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("last_updated_epoch") var lastUpdatedEpoch: Int,
    @SerializedName("last_updated") var lastUpdated: String,
    @SerializedName("temp_c") var tempC: Float,
    @SerializedName("temp_f") var tempF: Float,
    @SerializedName("is_day") var isDay: Int,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("wind_mph") var windMph: Float,
    @SerializedName("wind_kph") var windKph: Float,
    @SerializedName("wind_degree") var windDegree: Int,
    @SerializedName("wind_dir") var windDir: String,
    @SerializedName("pressure_mb") var pressureMb: Float,
    @SerializedName("pressure_in") var pressureIn: Float,
    @SerializedName("precip_mm") var precipMm: Float,
    @SerializedName("precip_in") var precipIn: Float,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("cloud") var cloud: Int,
    @SerializedName("feelslike_c") var feelslikeC: Float,
    @SerializedName("feelslike_f") var feelslikeF: Float,
    @SerializedName("vis_km") var visKm: Float,
    @SerializedName("vis_miles") var visMiles: Float,
    @SerializedName("uv") var uv: Float,
    @SerializedName("gust_mph") var gustMph: Float,
    @SerializedName("gust_kph") var gustKph: Float
)
