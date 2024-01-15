package com.kare.weatherapp.model

data class WeeklyWeatherDetails(
 val list: List<WeeklyWeatherForecast>
)

data class WeeklyWeatherForecast(
    val main: MainDetails,
    val weather: List<WeatherCondition>
)
