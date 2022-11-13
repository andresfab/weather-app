package com.apfol.weatherapp.network.dto

import com.apfol.weatherapp.domain.model.Weather
import com.google.gson.annotations.SerializedName

data class ForecastdayDTO(
    @SerializedName("date") var date: String,
    @SerializedName("date_epoch") var dateEpoch: Int,
    @SerializedName("day") var day: Day,
    @SerializedName("astro") var astro: Astro,
    @SerializedName("hour") var hoursDTO: List<HourDTO> = listOf()
)

fun ForecastdayDTO.toWeather(): Weather {
    return Weather(
        day.condition.text,
        day.condition.icon,
        day.avghumidity,
        day.avgtempC,
        date,
        hoursDTO.map { it.toHour() }
    )
}
