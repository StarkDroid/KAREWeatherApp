package com.kare.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.kare.weatherapp.ui.component.SearchBar
import com.kare.weatherapp.ui.component.WeatherCard
import com.kare.weatherapp.ui.theme.KAREWeatherAppTheme
import com.kare.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KAREWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val weatherDetails by viewModel.weatherDetails.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            onSearch = { locationName ->
                viewModel.viewModelScope.launch {
                    viewModel.getWeatherDetails(locationName)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (weatherDetails != null) {
            WeatherCard(weatherDetails = weatherDetails)
        }
    }

}

@Composable
@Preview(showBackground = true)
fun WeatherAppPreview() {
    WeatherApp(viewModel = WeatherViewModel())
}
