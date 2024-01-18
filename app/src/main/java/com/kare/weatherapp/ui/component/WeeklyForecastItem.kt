package com.kare.weatherapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kare.weatherapp.model.MainDetails
import com.kare.weatherapp.model.WeeklyWeatherForecast
import com.kare.weatherapp.utils.getDayfromDate
import com.kare.weatherapp.utils.getWeatherIconResourceId

@Composable
fun WeeklyForecastItem(
    forecastItem: WeeklyWeatherForecast,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .width(120.dp)
            .height(120.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val dayOfWeek = getDayfromDate(forecastItem.dt_txt)
            if (dayOfWeek != null) {
                Text(
                    text = dayOfWeek,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            val iconCode = forecastItem.weather.firstOrNull()?.icon
            if (iconCode != null) {
                Icon(
                    painter = painterResource(getWeatherIconResourceId(iconCode)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.Unspecified
                )
            }

            Text(
                text = "${forecastItem.main.temp?.toInt()}°C",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeeklyForecastItemPreview() {
    WeeklyForecastItem(
        forecastItem = WeeklyWeatherForecast(
            main = MainDetails(
                25.0, 26.0, 24.0, 26.0, 1013, 71
            ),
            weather = listOf(),
            dt_txt = "2024-01-16 00:00:00"
        )
    )
}
