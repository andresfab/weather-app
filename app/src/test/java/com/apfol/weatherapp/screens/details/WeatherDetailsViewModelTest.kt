package com.apfol.weatherapp.screens.details

import androidx.lifecycle.SavedStateHandle
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.apfol.weatherapp.domain.usecases.GetWeatherDetailsUseCase
import com.apfol.weatherapp.utils.Constants
import com.apfol.weatherapp.utils.Resource
import com.apfol.weatherapp.utils.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherDetailsViewModelTest {

    private lateinit var sut: WeatherDetailsViewModel
    private lateinit var savedStateHandleMock: SavedStateHandle
    private lateinit var getWeatherDetailsUseCaseMock: GetWeatherDetailsUseCase

    /**
     * Rule necessary to initialize Main dispatcher.
     */
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        getWeatherDetailsUseCaseMock = mock(GetWeatherDetailsUseCase::class.java)
        savedStateHandleMock = SavedStateHandle().apply {
            set(Constants.PARAM_WEATHER_NAME, "London")
        }
    }

    @Test
    fun `getWeather returns correct weather details value when Resource is Success`() = runTest {
        // Given
        val resourceSuccess = Resource.Success(mock(WeatherDetails::class.java))
        `when`(getWeatherDetailsUseCaseMock.invoke(anyString())).thenReturn(
            flow {
                emit(resourceSuccess)
            }
        )

        // When
        sut = WeatherDetailsViewModel(getWeatherDetailsUseCaseMock, savedStateHandleMock)
        advanceUntilIdle()

        // Then
        assertEquals(
            WeatherDetailsState(
                isLoading = false,
                weatherDetails = resourceSuccess.data
            ),
            sut.weatherDetailState.value
        )
    }

    @Test
    fun `getWeather returns correct weather details value when Resource is Error`() = runTest {
        // Given
        val resourceError: Resource<WeatherDetails> = Resource.Error("Error message")
        `when`(getWeatherDetailsUseCaseMock.invoke(anyString())).thenReturn(
            flow {
                emit(resourceError)
            }
        )

        // When
        sut = WeatherDetailsViewModel(getWeatherDetailsUseCaseMock, savedStateHandleMock)
        advanceUntilIdle()

        // Then
        assertEquals(
            WeatherDetailsState(
                isLoading = false,
                weatherDetails = null,
                error = resourceError.message!!
            ),
            sut.weatherDetailState.value
        )
    }

    @Test
    fun `getWeather returns correct weather details value when Resource is Loading`() = runTest {
        // Given
        val resourceLoading: Resource<WeatherDetails> = Resource.Loading()
        `when`(getWeatherDetailsUseCaseMock.invoke(anyString())).thenReturn(
            flow {
                emit(resourceLoading)
            }
        )

        // When
        sut = WeatherDetailsViewModel(getWeatherDetailsUseCaseMock, savedStateHandleMock)
        advanceUntilIdle()

        // Then
        assertEquals(
            WeatherDetailsState(
                isLoading = true,
                weatherDetails = null,
                error = ""
            ),
            sut.weatherDetailState.value
        )
    }

}
