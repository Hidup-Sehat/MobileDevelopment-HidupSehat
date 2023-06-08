package com.bangkit23.hidupsehat.presentation.screen.food_information

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.food_information.component.FoodInformationItem
import com.bangkit23.hidupsehat.presentation.screen.food_information.model.FoodInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInformationScreen(
    data : List<FoodInformation>,
    onItemClick: () -> Unit,
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    Text("Informasi Makanan")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                }
            )
        },
        content = {
            FoodInformationContent(
                modifier = Modifier.padding(it),
                data = data,
                onItemClick = onItemClick
            )
        }
    )
}

@Composable
fun FoodInformationContent(
    modifier : Modifier = Modifier,
    data : List<FoodInformation>,
    onItemClick : () -> Unit
    ) {
    LazyColumn(modifier = modifier){
        items(data){ data->
            FoodInformationItem(name = data.name, unit = data.unit, calories = data.calories,
            onItemClick = onItemClick)
        }
    }

}

@Preview
@Composable
fun FoodInformationScreePrev() {
    val dummyData = listOf<FoodInformation>(
        FoodInformation(1,"Nasi Goreng","100g","168kal")
    )
    FoodInformationScreen(dummyData, onItemClick = {})
}