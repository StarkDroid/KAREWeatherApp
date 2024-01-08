package com.kare.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.kare.weatherapp.data.WeatherDetails
import com.kare.weatherapp.model.WeatherViewModel
import com.kare.weatherapp.ui.theme.KAREWeatherAppTheme
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

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
    // Local state to hold the user input
    var location by remember { mutableStateOf("") }

    // Call the OpenWeather API when the button is clicked
    var weatherDetails by remember { mutableStateOf<WeatherDetails?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    // UI components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val context = LocalContext.current
        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter location") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Call the OpenWeather API here
                    viewModel.getWeatherDetails(
                        location,
                        onWeatherDetails = { details ->
                            weatherDetails = details
                        },
                        onError = { errorMsg ->
                            error = errorMsg
                        }
                    )
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // Call the OpenWeather API here
                viewModel.getWeatherDetails(
                    location,
                    onWeatherDetails = { details ->
                        weatherDetails = details
                    },
                    onError = { errorMsg ->
                        error = errorMsg
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Get Weather")
        }

        // Display weather details in a card
        weatherDetails?.let { weather ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Location: ${weather.name}")
                    Text("Temperature: ${weather.main?.temp} °C")
                }
            }
        } ?: error?.let { errorMsg ->
            Text(errorMsg, color = Color.Red)
        }
    }
}
