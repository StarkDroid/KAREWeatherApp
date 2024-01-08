package com.kare.weatherapp.network

import com.google.gson.Gson
import com.kare.weatherapp.data.WeatherDetails
import okhttp3.OkHttpClient
import okhttp3.Request

fun fetchWeatherDetails(location: String): WeatherDetails {
    val apiKey = "9ba82bf7644896ddbe0678eccc64089f"
    val baseUrl = "https://api.openweathermap.org/data/2.5/weather"

    val client = OkHttpClient()

    // We make a request to the API to get the weather data
    val request = Request.Builder()
        .url("$baseUrl?q=$location&appid=$apiKey")
        .build()

    val response = client.newCall(request).execute()

    return if (response.isSuccessful) {
        val responseBody = response.body?.string()
        println("response: $responseBody")
        Gson().fromJson(responseBody, WeatherDetails::class.java)
    } else {
        throw Exception("Failed to fetch weather details")
    }
}
