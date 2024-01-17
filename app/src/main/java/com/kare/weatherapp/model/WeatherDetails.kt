package com.kare.weatherapp.model

data class WeatherDetails(
    val name: String?,
    val main: MainDetails?,
    val weather: List<WeatherCondition>,
    val error: Boolean = false
) {
    companion object {
        fun errorState(): WeatherDetails {
            return WeatherDetails(null, null, emptyList(), error = true)
        }
    }

    fun hasError(): Boolean {
        return error
    }
}

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

data class WeeklyWeatherDetails(
    val list: List<WeeklyWeatherForecast>,
    val error: Boolean = false
) {
    companion object {
        fun errorState(): WeeklyWeatherDetails {
            return WeeklyWeatherDetails(emptyList(), error = true)
        }
    }
}

data class WeeklyWeatherForecast(
    val main: MainDetails,
    val weather: List<WeatherCondition>,
    val dt_txt: String
)



