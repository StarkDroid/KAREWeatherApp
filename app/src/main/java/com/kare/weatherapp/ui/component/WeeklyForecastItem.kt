package com.kare.weatherapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kare.weatherapp.R
import com.kare.weatherapp.model.WeeklyWeatherForecast

@Composable
fun WeeklyForecastItem(
    forecastItem: WeeklyWeatherForecast,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(120.dp)
            .height(150.dp)
            .background(Color.Gray)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the date, assuming it's available in your WeeklyForecastItem
            Text(
                text = "${forecastItem.main.pressure ?: R.string.no_data}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            // Display the weather icon (assuming you have icons available)
            Image(
                painter = painterResource(id = R.drawable.few_clouds), // Replace with your actual weather icon
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
            )

            // Display the temperature
            Text(
                text = "${forecastItem.main.temp?.toInt()}°C", // Assuming temperature is a Double
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
