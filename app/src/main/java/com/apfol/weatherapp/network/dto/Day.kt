package com.apfol.weatherapp.network.dto

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("maxtemp_c") var maxtempC: Float,
    @SerializedName("maxtemp_f") var maxtempF: Float,
    @SerializedName("mFloatemp_c") var mFloatempC: Float,
    @SerializedName("mFloatemp_f") var mFloatempF: Float,
    @SerializedName("avgtemp_c") var avgtempC: Float,
    @SerializedName("avgtemp_f") var avgtempF: Float,
    @SerializedName("maxwind_mph") var maxwindMph: Float,
    @SerializedName("maxwind_kph") var maxwindKph: Float,
    @SerializedName("totalprecip_mm") var totalprecipMm: Float,
    @SerializedName("totalprecip_in") var totalprecipIn: Float,
    @SerializedName("totalsnow_cm") var totalsnowCm: Float,
    @SerializedName("avgvis_km") var avgvisKm: Float,
    @SerializedName("avgvis_miles") var avgvisMiles: Float,
    @SerializedName("avghumidity") var avghumidity: Int,
    @SerializedName("daily_will_it_rain") var dailyWillItRain: Float,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain: Float,
    @SerializedName("daily_will_it_snow") var dailyWillItSnow: Float,
    @SerializedName("daily_chance_of_snow") var dailyChanceOfSnow: Float,
    @SerializedName("condition") var condition: Condition,
    @SerializedName("uv") var uv: Float
)
