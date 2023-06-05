package com.bangkit23.hidupsehat.presentation.screen.food_information

import android.widget.GridLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.CardFoodInformation
import com.bangkit23.hidupsehat.presentation.screen.food_information.model.FoodInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFoodInformationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                }
            )
        },
        content = {
            DetailFoodInformationContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
fun DetailFoodInformationContent(
    modifier: Modifier = Modifier
) {
    val dummyList = listOf<FoodInformation>(
        FoodInformation(1, "karbohidrat", "0.0g", "168 kal"),
        FoodInformation(1, "karbohidrat", "0.0g", "168 kal"),
        FoodInformation(1, "karbohidrat", "0.0g", "168 kal"),
        FoodInformation(1, "karbohidrat", "0.0g", "168 kal")
    )
    Column(modifier = modifier) {
        Text(text = "Lorem Ipsum", maxLines = 3, style = MaterialTheme.typography.headlineMedium)
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1 / 4f),
                value = "",
                onValueChange = {},
                label = { Text("Qty") })
            OutlinedTextField(
                modifier = Modifier.weight(3 / 4f),
                value = "",
                onValueChange = {},
                label = { Text("Ukuran Porsi") })
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(dummyList) {
                    CardFoodInformation(name = it.name, unit = it.calories, color = Color.Green)
                }
            })
        InformasiGizi()
    }
}

@Composable
fun InformasiGizi(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        Text("Informasi Gizi")
        Divider()
        Text("Porsi Ukuran")
        Divider()
        Text("Per porsi")
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Energi")
            Text(text = "0 kJ")
        }
        Text(text = "0 kkal")
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Lemak")
            Text(text = "0,0g")
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = "Lemak Jenuh")
                Text(text = "Lemak tak Jenuh Ganda")
                Text(text = "Lemak tak Jenuh Tunggal")
            }
            Column() {
                Text(text = "0,000g")
                Text(text = "0,000g")
                Text(text = "0,000g")
            }
        }
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kolesterol")
            Text(text = "0mg")
        }
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Protein")
            Text(text = "0,0g")
        }
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Karbohidrat")
            Text(text = "0,00g")
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = "Serat")
                Text(text = "Gula")
            }
            Column {
                Text(text = "0,00g")
                Text(text = "0,00g")
            }
        }
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Sodium")
            Text(text = "0mg")
        }
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kalium")
            Text(text = "0,0mg")
        }

    }
}

@Preview
@Composable
fun InformasiGiziPrev() {
    InformasiGizi()
}

@Preview
@Composable
fun DetailFoodInformationScreenPrev() {
    DetailFoodInformationScreen()
}