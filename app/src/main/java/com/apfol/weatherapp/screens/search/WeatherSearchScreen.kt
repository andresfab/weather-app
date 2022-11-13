package com.apfol.weatherapp.screens.search

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apfol.weatherapp.domain.model.WeatherSearchResult
import com.apfol.weatherapp.navigation.WeatherScreens

@Composable
fun WeatherSearchScreen(
    navController: NavController,
    viewModel: WeatherSearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Weather search") }) }
    ) {
        Column {
            Header(viewModel.searchQuery.value) { newQuery ->
                viewModel.search(newQuery)
            }
            ResultsView(viewModel.state.value, navController)
        }
    }
}

@Preview
@Composable
fun Header(
    query: String = "",
    onValChange: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = 32.dp,
                end = 20.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = query,
            placeholder = { Text("Search a City / Region / Province") },
            onValueChange = { onValChange(it) }
        )
    }
}

@Preview
@Composable
fun ResultsView(
    state: WeatherSearchState = WeatherSearchState(),
    navController: NavController? = null
) {
    ResultsList(state, navController)
    if (state.results.isEmpty()) {
        Text(
            text = "Find the weather somewhere",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun ResultsList(
    state: WeatherSearchState = WeatherSearchState(
        results = listOf(
            WeatherSearchResult("Chía", "Cundinamarca"),
            WeatherSearchResult("Soacha", "Cundinamarca")
        )
    ),
    navController: NavController? = null
) {
    LazyColumn(
        modifier = Modifier.padding(
            top = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(state.results) { result ->
            WeatherResultItem(
                result.name,
                result.country,
                navController
            )
        }
    }
}

@Preview
@Composable
fun WeatherResultItem(
    name: String = "Chía",
    country: String = "Cundinamarca",
    navController: NavController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController?.navigate(WeatherScreens.WeatherDetailsScreen.name + "/$name")
            }
    ) {
        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 4.dp
            )
        ) {
            Text(text = name)
            Spacer(Modifier.height(4.dp))
            Text(
                text = country,
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.Light
                )
            )
        }
        Divider()
    }
}
