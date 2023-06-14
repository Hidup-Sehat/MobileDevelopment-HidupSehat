package com.bangkit23.hidupsehat.presentation.screen.food_information

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.food_information.component.FoodInformationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInformationScreen(
    data : List<Food>,
    navigateToDetail: (String) -> Unit,
    onNavigateUp : () -> Unit,
    viewModel: FoodInformationViewModel = hiltViewModel()
    ) {
    var isSearching by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit){
        viewModel.onEvent(FoodInformationEvent.OnGetAllFeed(data))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = {
                    if (!isSearching){
                        Text("Informasi Makanan")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        //muncul text field
                        isSearching = !isSearching
                    }) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                    AnimatedVisibility(visible = isSearching) {
                        TextField(value = "", onValueChange = {})
                    }
                }
            )
        },
        content = {
            FoodInformationContent(
                modifier = Modifier.padding(it),
                data = state.foods,
                navigateToDetail = navigateToDetail
            )
        }
    )
}

@Composable
fun FoodInformationContent(
    modifier : Modifier = Modifier,
    data : List<Food>,
    navigateToDetail: (String) -> Unit
    ) {
    LazyColumn(modifier = modifier){
        items(data){food ->
            FoodInformationItem(name = food.name!!, onItemClick = {
                navigateToDetail(food.name)
            }, unit = "", calories = food.energyKKal.toString() )
        }
    }

}

@Preview
@Composable
fun FoodInformationScreePrev() {
    FoodInformationScreen(data = listOf(), navigateToDetail = {}, onNavigateUp = {})
}