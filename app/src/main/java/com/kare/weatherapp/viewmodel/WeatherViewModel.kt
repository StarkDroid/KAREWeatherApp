package com.kare.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kare.weatherapp.model.WeatherDetails
import com.kare.weatherapp.network.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {

    private val _weatherDetails = MutableLiveData<WeatherDetails>()
    val weatherDetails: LiveData<WeatherDetails> = _weatherDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApiService = retrofit.create(WeatherApiService::class.java)

    suspend fun getWeatherDetails(location: String) {
        _isLoading.value = true
        try {
            val apiKey = "9ba82bf7644896ddbe0678eccc64089f"
            val response = withContext(Dispatchers.IO) {
                weatherApiService.getWeather(location, apiKey)
            }
            _weatherDetails.value = response
            println(response)
        } catch (e: Exception) {
            println("Exception: $e")
        } finally {
            _isLoading.value = false
        }
    }
}