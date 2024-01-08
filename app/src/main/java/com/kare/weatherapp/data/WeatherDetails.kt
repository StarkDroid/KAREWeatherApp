package com.kare.weatherapp.data

data class WeatherDetails(
    val name: String?,
    val main: MainDetails?
)

data class MainDetails (
    val temp: Double?,
    val feels_like: Double?,
    val temp_min: Double?,
    val temp_max: Double?,
    val pressure: Int?,
    val humidity: Int?
)
