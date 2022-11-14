package com.apfol.weatherapp.screens.details

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.apfol.weatherapp.MainActivity
import com.apfol.weatherapp.di.AppModule
import com.apfol.weatherapp.domain.model.Hour
import com.apfol.weatherapp.domain.model.Location
import com.apfol.weatherapp.domain.model.Weather
import com.apfol.weatherapp.domain.model.WeatherDetails
import com.apfol.weatherapp.ui.theme.WeatherAppTheme
import com.apfol.weatherapp.utils.DetailsScreenTestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private val weather = Weather(
    weatherState = "Moderate or heavy rain with thunder",
    weatherStateImageURL = "//cdn.weatherapi.com/weather/64x64/night/389.png",
    humidity = 88,
    temperature = 14.0F,
    date = "2022-11-15",
    hours = listOf(
        Hour("00:00", 23F),
        Hour("00:00", 18F)
    )
)
private val weatherDetails = WeatherDetails(
    location = Location("Bogota", "Colombia"),
    weathers = listOf(
        weather.copy(weatherState = "Sunny"),
        weather.copy(weatherState = "Heavy rain")
    )
)

@HiltAndroidTest
@UninstallModules(AppModule::class)
class WeatherDetailsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun contentContainer_showsLoadingContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ContentContainer(
                    WeatherDetailsState(
                        isLoading = true
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.LOADING_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.DETAILS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.ERROR_CONTAINER)
            .assertDoesNotExist()
    }

    @Test
    fun contentContainer_showsWeatherDetailsContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ContentContainer(
                    WeatherDetailsState(
                        isLoading = false,
                        weatherDetails = weatherDetails
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.DETAILS_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.LOADING_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.ERROR_CONTAINER)
            .assertDoesNotExist()
    }

    @Test
    fun contentContainer_showsErrorContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ContentContainer(
                    WeatherDetailsState(
                        isLoading = false,
                        error = "Heeelp!"
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.ERROR_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.DETAILS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(DetailsScreenTestTags.LOADING_CONTAINER)
            .assertDoesNotExist()
    }
}
