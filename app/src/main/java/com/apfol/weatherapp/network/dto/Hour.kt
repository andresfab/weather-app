package com.apfol.weatherapp.network.dto

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("time_epoch") var timeEpoch: Int,
    @SerializedName("time") var time: String,
    @SerializedName("temp_c") var tempC: Double,
    @SerializedName("temp_f") var tempF: Double,
    @SerializedName("is_day") var isDay: Int,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("wind_mph") var windMph: Float,
    @SerializedName("wind_kph") var windKph: Double,
    @SerializedName("wind_degree") var windDegree: Int,
    @SerializedName("wind_dir") var windDir: String,
    @SerializedName("pressure_mb") var pressureMb: Float,
    @SerializedName("pressure_in") var pressureIn: Double,
    @SerializedName("precip_mm") var precipMm: Double,
    @SerializedName("precip_in") var precipIn: Double,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("cloud") var cloud: Int,
    @SerializedName("feelslike_c") var feelslikeC: Double,
    @SerializedName("feelslike_f") var feelslikeF: Double,
    @SerializedName("windchill_c") var windchillC: Double,
    @SerializedName("windchill_f") var windchillF: Double,
    @SerializedName("heatindex_c") var heatindexC: Double,
    @SerializedName("heatindex_f") var heatindexF: Double,
    @SerializedName("dewpoFloat_c") var dewpoFloatC: Double,
    @SerializedName("dewpoFloat_f") var dewpoFloatF: Double,
    @SerializedName("will_it_rain") var willItRain: Int,
    @SerializedName("chance_of_rain") var chanceOfRain: Int,
    @SerializedName("will_it_snow") var willItSnow: Int,
    @SerializedName("chance_of_snow") var chanceOfSnow: Int,
    @SerializedName("vis_km") var visKm: Float,
    @SerializedName("vis_miles") var visMiles: Float,
    @SerializedName("gust_mph") var gustMph: Double,
    @SerializedName("gust_kph") var gustKph: Double,
    @SerializedName("uv") var uv: Float
)
