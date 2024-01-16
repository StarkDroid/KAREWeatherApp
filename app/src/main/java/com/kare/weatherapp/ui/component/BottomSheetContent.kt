package com.kare.weatherapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kare.weatherapp.R
import com.kare.weatherapp.model.MainDetails
import com.kare.weatherapp.model.WeatherCondition
import com.kare.weatherapp.model.WeatherDetails

@Composable
fun BottomSheetContent(weatherDetails: WeatherDetails?) {
    weatherDetails?.let {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp),
            ) {
                WeatherDetailItem(
                    iconResId = R.drawable.temp_range,
                    label = "Temp range",
                    value = "${it.main?.temp_min?.toInt()}°C - ${it.main?.temp_max?.toInt()}°C"
                )

                Spacer(modifier = Modifier.height(16.dp))

                WeatherDetailItem(
                    iconResId = R.drawable.pressure,
                    label = "Pressure",
                    value = "${it.main?.pressure} hPa"
                )

                Spacer(modifier = Modifier.height(16.dp))

                WeatherDetailItem(
                    iconResId = R.drawable.humidity,
                    label = "Humidity",
                    value = "${it.main?.humidity}%"
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomSheetContentPreview() {
    val dummyWeatherDetail = WeatherDetails(
        name = "Chennai",
        main = MainDetails(45.0, 40.0, 23.34,45.0,12,90),
        weather = listOf(WeatherCondition(description = "Partly cloudy", icon = "50n"))
    )
    BottomSheetContent(weatherDetails = dummyWeatherDetail)
}