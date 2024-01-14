package com.kare.weatherapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Temp range: ${it.main?.temp_min?.toInt()}°C - ${it.main?.temp_max?.toInt()}°C",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Pressure: ${it.main?.pressure} hPa",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Humidity: ${it.main?.humidity}%",
                    style = MaterialTheme.typography.bodyMedium
                )
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