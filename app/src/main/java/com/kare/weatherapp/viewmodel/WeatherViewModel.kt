package com.kare.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kare.weatherapp.model.WeatherDetails
import com.kare.weatherapp.model.WeeklyWeatherDetails
import com.kare.weatherapp.model.WeeklyWeatherForecast
import com.kare.weatherapp.network.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  WeatherViewModel : ViewModel() {

    private val _weatherDetails = MutableLiveData<WeatherDetails>()
    val weatherDetails: LiveData<WeatherDetails> = _weatherDetails

    private val _weeklyForecast = MutableLiveData<WeeklyWeatherDetails>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApiService = retrofit.create(WeatherApiService::class.java)

    private val apiKey = "9ba82bf7644896ddbe0678eccc64089f"

    suspend fun getWeatherDetails(location: String) {
        _isLoading.value = true
        try {
            val currentWeatherResponse = withContext(Dispatchers.IO) {
                weatherApiService.getCurrentWeather(location, apiKey)
            }
            val weeklyWeatherResponse = withContext(Dispatchers.IO) {
                weatherApiService.getWeeklyForecast(location, apiKey)
            }

            if (currentWeatherResponse.isSuccessful && weeklyWeatherResponse.isSuccessful) {
                _weatherDetails.value = currentWeatherResponse.body()
                _weeklyForecast.value = weeklyWeatherResponse.body()
                println("CurrentWeatherResponse: $currentWeatherResponse")
                println("WeeklyWeatherResponse: $weeklyWeatherResponse")
            } else {
               handleErrorResponse()
            }
        } catch (e: Exception) {
            handleErrorResponse()
            println("Exception: $e")
        } finally {
            _isLoading.value = false
        }
    }

    fun extractDailyForecast(): List<WeeklyWeatherForecast> {
        return _weeklyForecast.value?.list.orEmpty()
            .filterIndexed { index, _ -> index % 8 == 0 }
    }

    private fun handleErrorResponse() {
        _weatherDetails.value = WeatherDetails.errorState()
        _weeklyForecast.value = WeeklyWeatherDetails.errorState()
    }
}