package com.kare.weatherapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kare.weatherapp.R
import com.kare.weatherapp.model.MainDetails
import com.kare.weatherapp.model.WeatherCondition
import com.kare.weatherapp.model.WeatherDetails
import com.kare.weatherapp.utils.getWeatherIconResourceId

@Composable
fun WeatherCard(weatherDetails: WeatherDetails?, onClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (weatherDetails != null && !weatherDetails.hasError()) {

                Text(
                    weatherDetails.name ?: stringResource(id = R.string.no_data),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                val iconCode = weatherDetails.weather.firstOrNull()?.icon
                if (iconCode != null) {
                    val iconResourceId = getWeatherIconResourceId(iconCode)
                    Icon(
                        painter = painterResource(id = iconResourceId),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(180.dp),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "${weatherDetails.main?.temp?.toInt() ?: stringResource(id = R.string.no_data)}°C",
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 100.sp)
                )

                Text(
                    "Feels like ${weatherDetails.main?.feels_like?.toInt() ?: stringResource(id = R.string.no_data)} °C",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
                    modifier = Modifier
                        .offset(y = (-35).dp)
                )

            } else {
                Icon(
                    painterResource(id = R.drawable.no_results_icon),
                    contentDescription = "No result found",
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.Unspecified
                )

                Text(
                    text = "Sorry, No results found",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeatherCardPreview() {
    val dummyWeatherDetail = WeatherDetails(
        name = "Chennai",
        main = MainDetails(45.0, 40.0, 23.34,45.0,12,90),
        weather = listOf(WeatherCondition(description = "Partly cloudy", icon = "50n"))
    )
    WeatherCard(weatherDetails = dummyWeatherDetail, onClick = {})
}