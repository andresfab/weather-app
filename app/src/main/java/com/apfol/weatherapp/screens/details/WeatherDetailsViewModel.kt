package com.apfol.weatherapp.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apfol.weatherapp.domain.usecases.GetWeatherDetailsUseCase
import com.apfol.weatherapp.utils.Constants
import com.apfol.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weatherDetailState = mutableStateOf(WeatherDetailsState())
    val weatherDetailState: State<WeatherDetailsState> = _weatherDetailState

    init {
        savedStateHandle.get<String>(Constants.PARAM_WEATHER_NAME)?.let { weatherName ->
            getWeather(weatherName)
        }
    }

    private fun getWeather(weatherName: String) {
        getWeatherDetailsUseCase(weatherName).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _weatherDetailState.value = WeatherDetailsState(
                        isLoading = false,
                        weatherDetails = result.data
                    )
                }
                is Resource.Error -> {
                    _weatherDetailState.value = WeatherDetailsState(
                        isLoading = false,
                        weatherDetails = null,
                        error = result.message ?: "Error inesperado"
                    )
                }
                is Resource.Loading -> {
                    _weatherDetailState.value = WeatherDetailsState(
                        isLoading = true,
                        weatherDetails = null,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
