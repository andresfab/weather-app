package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.Hour
import com.google.gson.annotations.SerializedName

data class HourDTO(
    @SerializedName("time_epoch") var timeEpoch: Int,
    @SerializedName("time") var time: String,
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
    @SerializedName("windchill_c") var windchillC: Float,
    @SerializedName("windchill_f") var windchillF: Float,
    @SerializedName("heatindex_c") var heatindexC: Float,
    @SerializedName("heatindex_f") var heatindexF: Float,
    @SerializedName("dewpoFloat_c") var dewpoFloatC: Float,
    @SerializedName("dewpoFloat_f") var dewpoFloatF: Float,
    @SerializedName("will_it_rain") var willItRain: Int,
    @SerializedName("chance_of_rain") var chanceOfRain: Int,
    @SerializedName("will_it_snow") var willItSnow: Int,
    @SerializedName("chance_of_snow") var chanceOfSnow: Int,
    @SerializedName("vis_km") var visKm: Float,
    @SerializedName("vis_miles") var visMiles: Float,
    @SerializedName("gust_mph") var gustMph: Float,
    @SerializedName("gust_kph") var gustKph: Float,
    @SerializedName("uv") var uv: Float
)

fun HourDTO.toHour(): Hour = Hour(time, tempC)
