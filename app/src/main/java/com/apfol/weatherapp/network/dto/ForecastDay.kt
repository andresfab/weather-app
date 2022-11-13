package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.CurrentWeather
import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("date") var date: String,
    @SerializedName("date_epoch") var dateEpoch: Int,
    @SerializedName("day") var day: Day,
    @SerializedName("astro") var astro: Astro,
    @SerializedName("hour") var hour: ArrayList<Hour> = arrayListOf()
)

fun Forecastday.toSpecificWeather(): CurrentWeather {
    return CurrentWeather(
        day.condition.text,
        day.condition.icon,
        day.avghumidity,
        day.avgtempC
    )
}
