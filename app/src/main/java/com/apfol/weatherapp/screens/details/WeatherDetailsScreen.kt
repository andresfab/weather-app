package com.apfol.weatherapp.screens.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.apfol.weatherapp.R
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.apfol.weatherapp.screens.details.composables.ChartContainer
import com.apfol.weatherapp.utils.WeatherDetailsParameterProvider
import com.apfol.weatherapp.utils.getNextDaysWeathers
import com.apfol.weatherapp.utils.LINE_CHART_DATA
import com.apfol.weatherapp.utils.toLineChartData
import com.apfol.weatherapp.utils.weekDayStringFromDateString
import hu.ma.charts.line.LineChart
import hu.ma.charts.line.data.LineChartData

@Composable
fun WeatherDetailsScreen(
    navController: NavController, viewModel: WeatherDetailsViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Weather details")
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow"
                )
            }
        })
    }) { padding ->
        val weatherDetailState = viewModel.weatherDetailState.value
        if (weatherDetailState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        weatherDetailState.weatherDetails?.let {
            LazyColumn(
                Modifier.padding(padding)
            ) {
                item { ActualWeatherView(it) }
                item {
                    HoursTemperatureChart(
                        it.weathers.first().hours.toLineChartData()
                    )
                }
                item { NextWeatherForecastView(it) }
            }
        }
        if (weatherDetailState.error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = weatherDetailState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ActualWeatherView(
    @PreviewParameter(WeatherDetailsParameterProvider::class) weatherDetails: WeatherDetails
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = rememberAsyncImagePainter(
                model = "https:${weatherDetails.weathers.first().weatherStateImageURL}",
                placeholder = painterResource(R.drawable.weather_image_example)
            ),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
        Row(
            modifier = Modifier.padding(
                horizontal = 32.dp
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "${weatherDetails.weathers.first().temperature}°C",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 40.sp, fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(Modifier.width(16.dp))
            Text(
                weatherDetails.weathers.first().weatherState,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun HoursTemperatureChart(
    data: LineChartData = LINE_CHART_DATA
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            LineChart(
                data = data
            )
        }
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "Hours",
            style = TextStyle(
                fontSize = 8.sp,
                color = Color.Gray,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}

@Preview
@Composable
fun NextWeatherForecastView(
    @PreviewParameter(WeatherDetailsParameterProvider::class) weatherDetails: WeatherDetails
) {
    val nextWeathers = weatherDetails.weathers.getNextDaysWeathers()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier.padding(
                start = 20.dp, top = 20.dp
            )
        ) {
            Text(
                text = "Next ${nextWeathers.size} days",
                style = MaterialTheme.typography.h6
            )
        }
        Column(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            nextWeathers.forEach { nextWeather ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ), horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier.weight(2F),
                        text = nextWeather.date.weekDayStringFromDateString(),
                        style = MaterialTheme.typography.body1
                    )
                    Row(
                        modifier = Modifier.weight(3F), horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier.padding(
                                end = 8.dp
                            ), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = "https:${nextWeather.weatherStateImageURL}",
                                    placeholder = painterResource(R.drawable.weather_image_example)
                                ),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                            )
                        }
                        Text(
                            text = nextWeather.weatherState,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1F),
                        textAlign = TextAlign.End,
                        text = "${nextWeather.temperature}°C",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
