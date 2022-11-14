package com.apfol.weatherapp.screens.search

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apfol.weatherapp.MainActivity
import com.apfol.weatherapp.di.AppModule
import com.apfol.weatherapp.domain.model.WeatherSearchResult
import com.apfol.weatherapp.navigation.WeatherScreens
import com.apfol.weatherapp.ui.theme.WeatherAppTheme
import com.apfol.weatherapp.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class WeatherSearchScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun default_WeatherSearchScreenComposables() {
        // When
        composeRule.setContent {
            val navController = rememberNavController()
            WeatherAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = WeatherScreens.WeatherSearchScreen.name
                ) {
                    composable(WeatherScreens.WeatherSearchScreen.name) {
                        WeatherSearchScreen(navController = navController)
                    }
                }
            }
        }

        // Then
        composeRule
            .onNodeWithTag(TestTags.SEARCH_HEADER)
            .assertExists()
        composeRule
            .onNodeWithTag(TestTags.EMPTY_RESULTS_CONTAINER)
            .assertExists()
    }

    @Test
    fun resultList_showsEmptyResultsContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ResultsView()
            }
        }

        // Then
        composeRule
            .onNodeWithTag(TestTags.EMPTY_RESULTS_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(TestTags.LOADING_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.ERROR_CONTAINER)
            .assertDoesNotExist()
    }

    @Test
    fun resultList_showsLoadingContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ResultsView(
                    state = WeatherSearchState(
                        isLoading = true
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(TestTags.LOADING_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(TestTags.EMPTY_RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.ERROR_CONTAINER)
            .assertDoesNotExist()
    }

    @Test
    fun resultList_showsResultsContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ResultsView(
                    state = WeatherSearchState(
                        isLoading = false,
                        results = listOf(
                            WeatherSearchResult(
                                "Chía",
                                "Colombia"
                            )
                        )
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(TestTags.RESULTS_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(TestTags.EMPTY_RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.LOADING_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.ERROR_CONTAINER)
            .assertDoesNotExist()
    }

    @Test
    fun resultList_showsErrorContainer() {
        // When
        composeRule.setContent {
            WeatherAppTheme {
                ResultsView(
                    state = WeatherSearchState(
                        isLoading = false,
                        error = "Heeeelp!"
                    )
                )
            }
        }

        // Then
        composeRule
            .onNodeWithTag(TestTags.ERROR_CONTAINER)
            .assertExists()
        composeRule
            .onNodeWithTag(TestTags.RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.EMPTY_RESULTS_CONTAINER)
            .assertDoesNotExist()
        composeRule
            .onNodeWithTag(TestTags.LOADING_CONTAINER)
            .assertDoesNotExist()
    }
}
