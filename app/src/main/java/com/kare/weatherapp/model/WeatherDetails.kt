package com.kare.weatherapp.model

data class WeatherDetails(
    val name: String?,
    val main: MainDetails?,
    val weather: List<WeatherCondition>
)

data class MainDetails (
    val temp: Double?,
    val feels_like: Double?,
    val temp_min: Double?,
    val temp_max: Double?,
    val pressure: Int?,
    val humidity: Int?
)

data class WeatherCondition (
    val description: String?,
    val icon: String?
)



