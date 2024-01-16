package com.kare.weatherapp.utils

import com.kare.weatherapp.R
import java.time.LocalDate
import java.time.LocalTime

fun getDayOfWeek(): String {
    val currentDayOfWeek = LocalDate.now().dayOfWeek
    return currentDayOfWeek.toString()
}

fun getGreeting(): String {
    val currentTime = LocalTime.now()
    return when {
        currentTime.isAfter(LocalTime.MIDNIGHT) && currentTime.isBefore(LocalTime.NOON) -> "Good Morning,"
        currentTime.isAfter(LocalTime.NOON) && currentTime.isBefore(LocalTime.of(18, 0)) -> "Good Afternoon,"
        currentTime.isAfter(LocalTime.of(18,0)) && currentTime.isBefore(LocalTime.MIDNIGHT) -> "Good Evening,"
        else -> "Good Night,"
    }

}

fun getWeatherIconResourceId(iconCode: String): Int {
    return when (iconCode) {
        "01d", "01n" -> R.drawable.clear_sky
        "02d", "02n" -> R.drawable.few_clouds
        "03d", "03n" -> R.drawable.scattered_clouds
        "04d", "04n" -> R.drawable.broken_clouds
        "09d", "09n" -> R.drawable.shower_rain
        "10d", "10n" -> R.drawable.rain
        "11d", "11n" -> R.drawable.thunderstorm
        "13d", "13n" -> R.drawable.snow
        "50d", "50n" -> R.drawable.mist
        else -> 0
    }
}