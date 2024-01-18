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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.kare.weatherapp.ui.component.BottomSheetContent
import com.kare.weatherapp.ui.component.SearchBar
import com.kare.weatherapp.ui.component.WeatherCard
import com.kare.weatherapp.ui.component.WeeklyForecastItem
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val weatherDetails by viewModel.weatherDetails.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    var isInputError by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
            },
            onInputError = {
                isInputError = true
            }
        )

        if (isLoading == true) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
        } else {
            if (weatherDetails != null) {
                WeatherCard(
                    weatherDetails = weatherDetails,
                    onClick = {
                        if (weatherDetails!!.hasError()) return@WeatherCard
                        showBottomSheet = true
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Daily Forecast",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow {
                    items(viewModel.extractDailyForecast()) { weeklyForecastItem ->
                        WeeklyForecastItem(
                            forecastItem = weeklyForecastItem
                        )
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            BottomSheetContent(weatherDetails = weatherDetails)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeatherAppPreview() {
    WeatherApp(viewModel = WeatherViewModel())
}
