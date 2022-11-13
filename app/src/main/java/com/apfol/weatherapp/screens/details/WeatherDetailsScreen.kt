package com.apfol.weatherapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.apfol.weatherapp.R
import com.apfol.weatherapp.domain.model.CurrentWeather
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.apfol.weatherapp.utils.WeatherDetailsParameterProvider

@Composable
fun WeatherDetailsScreen(
    viewModel: WeatherDetailsViewModel = hiltViewModel()
) {
    val weatherDetailState = viewModel.weatherDetailState.value

    if (weatherDetailState.isLoading) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    weatherDetailState.weatherDetails?.let {
        Column {
            ActualWeatherView(it)
            NextWeatherView(it)
        }
    }
    if (weatherDetailState.error.isNotBlank()) {
        Text(
            text = weatherDetailState.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun ActualWeatherView(
    @PreviewParameter(WeatherDetailsParameterProvider::class) weatherDetails: WeatherDetails
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 24.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherDetails.weather.name,
                style = MaterialTheme.typography.h4
            )
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                painter = rememberAsyncImagePainter(
                    model = "https:${ weatherDetails.currentWeather.weatherStateAbbr }",
                    placeholder = painterResource(R.drawable.weather_image_example)
                ),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
            Text(
                weatherDetails.currentWeather.weatherState,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
fun NextWeatherView(
    @PreviewParameter(WeatherDetailsParameterProvider::class) weatherDetails: WeatherDetails
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.padding(
                start = 20.dp,
                top = 32.dp
            )
        ) {
            Text(
                text = "Next weather forecasts",
                style = MaterialTheme.typography.h6
            )
        }
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(weatherDetails.nextWeathers) { nextWeather ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = nextWeather.weatherState,
                            style = MaterialTheme.typography.body1
                        )
                        Box(
                            modifier = Modifier.weight(1F),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp),
                                painter = rememberAsyncImagePainter(
                                    model = "https:${ weatherDetails.currentWeather.weatherStateAbbr }",
                                    placeholder = painterResource(R.drawable.weather_image_example)
                                ),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                            )
                        }
                    }
                }
            }
        }
    }
}
