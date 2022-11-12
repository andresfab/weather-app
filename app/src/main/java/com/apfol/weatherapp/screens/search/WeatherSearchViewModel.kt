package com.apfol.weatherapp.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apfol.weatherapp.domain.usecases.GetSearchResultUseCase
import com.apfol.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val SEARCH_DELAY_MILLIS = 500L

@HiltViewModel
class WeatherSearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WeatherSearchState())
    val state: State<WeatherSearchState> = _state

    fun search(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY_MILLIS)

            if(query.isNotBlank()) {
                withContext(Dispatchers.IO) {
                    getSearchResultUseCase(query).onEach { results ->
                        when(results) {
                            is Resource.Success -> {
                                _state.value = WeatherSearchState(
                                    results = results.data ?: emptyList(),
                                    isLoading = false,
                                    error = ""
                                )
                            }
                            is Resource.Error -> {
                                _state.value = WeatherSearchState(
                                    results = results.data ?: emptyList(),
                                    isLoading = false,
                                    error = results.message ?: "Unknown error"
                                )
                            }
                            is Resource.Loading -> {
                                _state.value = WeatherSearchState(
                                    results = emptyList(),
                                    isLoading = true,
                                    error = ""
                                )
                            }
                        }
                    }.launchIn(this)
                }
            } else {
                _state.value = WeatherSearchState(
                    results = emptyList(),
                    isLoading = false,
                    error = ""
                )
            }
        }
    }
}
