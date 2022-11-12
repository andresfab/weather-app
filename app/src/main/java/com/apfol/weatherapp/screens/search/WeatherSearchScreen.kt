package com.apfol.weatherapp.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WeatherSearchScreen() {
    Header()
}

@Preview
@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Search",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = "Search a City / Region / Province",
            onValueChange = {}
        )
    }
}

@Preview
@Composable
fun ResultList() {
    LazyColumn(
        modifier = Modifier.padding(
            horizontal = 20.dp,
            vertical = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            WeatherResultItem()
        }
        item {
            WeatherResultItem()
        }
    }
}

@Preview
@Composable
fun WeatherResultItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Chía")
            Text(text = "Cundinamarca")
        }
    }
}
