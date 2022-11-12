package com.apfol.weatherapp.domain.usecases

import com.apfol.weatherapp.domain.model.WeatherSearchResult
import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.dto.toWeatherSearchResult
import com.apfol.weatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<WeatherSearchResult>>>
    = flow {
        try {
            emit(Resource.Loading())
            val searchResults = weatherRepository
                .getResultsForSearch(query).map { resultDTO ->
                    resultDTO.toWeatherSearchResult()
            }
            emit(Resource.Success(searchResults))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "UnExpected error, try again later!"))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(e.message ?: "It was not possible to connect to the server!"))
        }
    }
}
