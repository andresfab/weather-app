package com.apfol.weatherapp.di

import com.apfol.weatherapp.domain.repository.WeatherRepository
import com.apfol.weatherapp.network.WeatherAPI
import com.apfol.weatherapp.network.repository.WeatherRepositoryImpl
import com.apfol.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideWeatherAPI(): WeatherAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}
