package com.apfol.weatherapp.domain.model

data class Weather (
    val weatherState: String,
    val weatherStateImageURL: String,
    val humidity: Int,
    val temperature: Float,
    val date: String,
    val hours: List<Hour>
)
