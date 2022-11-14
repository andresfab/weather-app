package com.apfol.weatherapp.domain.usecases

import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.dto.WeatherDetailsDTO
import com.apfol.weatherapp.utils.Resource
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherDetailsUseCaseTest {

    private lateinit var sut: GetWeatherDetailsUseCase
    private lateinit var weatherRepositoryMock: WeatherRepository

    @Before
    fun setUp() {
        weatherRepositoryMock = mock(WeatherRepository::class.java)
    }

    @Test
    fun `invoke returns resource success correctly`() = runTest {
        // Given
        val weatherDetailsDTOMock = mockk<WeatherDetailsDTO>(relaxed = true)

        `when`(
            weatherRepositoryMock.getWeatherDetails(
                anyString()
            )
        ).thenReturn(weatherDetailsDTOMock)

        // When
        sut = GetWeatherDetailsUseCase(weatherRepositoryMock)
        val resource = sut.invoke("").last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Success)
    }

    @Test
    fun `invoke returns resource error correctly with HTTPException`() = runTest {
        // Given
        `when`(
            weatherRepositoryMock.getWeatherDetails(
                anyString()
            )
        ).thenThrow(HttpException::class.java)

        // When
        sut = GetWeatherDetailsUseCase(weatherRepositoryMock)
        val resource = sut.invoke("").last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Error)
    }
}
