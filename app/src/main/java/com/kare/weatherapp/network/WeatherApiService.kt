package com.kare.weatherapp.network

import com.kare.weatherapp.data.WeatherDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherDetails
}