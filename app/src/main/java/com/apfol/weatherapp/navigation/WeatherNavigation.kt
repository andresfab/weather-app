package com.apfol.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apfol.weatherapp.screens.details.WeatherDetailsScreen
import com.apfol.weatherapp.screens.search.WeatherSearchScreen
import com.apfol.weatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        composable(WeatherScreens.WeatherSearchScreen.name) {
            WeatherSearchScreen(navController = navController)
        }
        composable(WeatherScreens.WeatherDetailsScreen.name + "/{weatherName}") {
            WeatherDetailsScreen(navController = navController)
        }
    }
}
