package com.apfol.weatherapp.domain.usecases

import com.apfol.weatherapp.domain.model.WeatherDetails
import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.dto.toWeatherDetails
import com.apfol.weatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(name: String): Flow<Resource<WeatherDetails>> = flow {
        try {
            emit(Resource.Loading())

            val weatherDetailsDTO = weatherRepository.getWeatherDetails(name)
            val weatherDetails = weatherDetailsDTO.toWeatherDetails()

            emit(Resource.Success(weatherDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error, try again later!"))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(e.message?: "It was not possible to connect to the server!"))
        }
    }
}
