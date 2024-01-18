package com.kare.weatherapp.network

import com.kare.weatherapp.model.WeatherDetails
import com.kare.weatherapp.model.WeeklyWeatherDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherDetails>

    @GET("forecast")
    suspend fun getWeeklyForecast(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeeklyWeatherDetails>
}