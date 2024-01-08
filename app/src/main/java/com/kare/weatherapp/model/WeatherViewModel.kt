package com.kare.weatherapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kare.weatherapp.data.WeatherDetails
import com.kare.weatherapp.network.fetchWeatherDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    fun getWeatherDetails(
        location: String,
        onWeatherDetails: (WeatherDetails) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherDetails = fetchWeatherDetails(location)
                onWeatherDetails(weatherDetails)
            } catch (e: Exception) {
                println("Exception: $e")
                onError("Failed to fetch weather details")
            }
        }
    }
}