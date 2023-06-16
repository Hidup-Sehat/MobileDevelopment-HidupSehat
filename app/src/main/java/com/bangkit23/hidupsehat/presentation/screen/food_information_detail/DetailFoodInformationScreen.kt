package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.food_information_detail.component.CardFoodContent
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components.CustomTextFieldLabel
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components.DropDownEditFood
import com.bangkit23.hidupsehat.util.get3DigitsOnly

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFoodInformationScreen(
    name: String,
    onNavigateUp: () -> Unit,
    viewModel: DetailFoodInformationViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(DetailFoodInformationEvent.OnGetFoodById(name))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text("") }, navigationIcon = {
            IconButton(onClick = {
                onNavigateUp()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        })
    }, content = { padding ->
        state.food?.let {
            DetailFoodInformationContent(
                modifier = Modifier.padding(padding),
                food = it,
                count = state.count,
                portionSizes = state.portionSize,
                onDropDownItemClick = { food, count ->
                    viewModel.onEvent(DetailFoodInformationEvent.OnDropDownItemClick(food, count))
                },
                onCountChange = { count ->
                    viewModel.onEvent(DetailFoodInformationEvent.OnCountChange(count))
                },
                onPortionSizeClick = {
                    viewModel.onEvent(DetailFoodInformationEvent.OnPortionSizeClick(it))
                }
            )
        }
    })
}

@Composable
fun DetailFoodInformationContent(
    food: Food,
    count: Int,
    portionSizes: List<Food>,
    onPortionSizeClick: () -> Unit,
    onDropDownItemClick: (Food, Int) -> Unit,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            text = food.name ?: "",
            maxLines = 3,
            style = MaterialTheme.typography.headlineMedium
        )
        Divider(
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextFieldLabel(
                modifier = Modifier.weight(1 / 4f),
                label = "Jumlah",
                onValueChange = {
                    onCountChange(it.toIntOrNull() ?: 1)
                },
                value = "$count"
            )
            Spacer(modifier = Modifier.width(10.dp))
            DropDownEditFood(
                value = "${food.portionSize}",
                portionSizes = portionSizes,
                onDropDownItemClick = {
                    it?.let {
                        onDropDownItemClick(it, count)
                    }
                },
                onPortionSizeClick = onPortionSizeClick,
                modifier = Modifier.weight(3 / 4f),
            )
        }
        CardFoodContent(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            karbohidrat = food.carbohydrate ?: 0.0,
            lemak = food.fat ?: 0.0,
            serat = food.fiber ?: 0.0,
            protein = food.protein ?: 0.0
        )

        InformasiGizi(
            modifier = Modifier.padding(top = 20.dp),
            energyKj = food.energyKj ?: 0.0,
            energyKKal = food.energyKKal ?: 0.0,
            fat = food.fat ?: 0.0,
            saturatedFat = food.saturatedFat ?: 0.0,
            polyunsaturatedFat = food.polyunsaturatedFat ?: 0.0,
            monosaturatedFat = food.monounsaturatedFat ?: 0.0,
            cholesterol = food.cholesterol ?: 0.0,
            protein = food.protein ?: 0.0,
            carbohydrate = food.carbohydrate ?: 0.0,
            fiber = food.fiber ?: 0.0,
            sugar = food.sugar ?: 0.0,
            sodium = food.sodium ?: 0.0,
            potassium = food.potassium ?: 0.0
        )
    }
}

@Composable
fun InformasiGizi(
    modifier: Modifier = Modifier,
    energyKj: Double,
    energyKKal: Double,
    fat: Double,
    saturatedFat: Double,
    polyunsaturatedFat: Double,
    monosaturatedFat: Double,
    cholesterol: Double,
    protein: Double,
    carbohydrate: Double,
    fiber: Double,
    sugar: Double,
    sodium: Double,
    potassium: Double,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 20.dp)
            .background(colorResource(id = R.color.light_gray))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            "Informasi Gizi", style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Divider(modifier = Modifier.padding(vertical = 6.dp))
        Text("Porsi Ukuran", style = MaterialTheme.typography.titleMedium)
        Divider(modifier = Modifier.padding(vertical = 6.dp), thickness = 12.dp)
        Text(
            modifier = Modifier.fillMaxWidth(), text = "Per porsi", textAlign = TextAlign.End
        )
        Divider(modifier = Modifier.padding(vertical = 6.dp), thickness = 4.dp)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Energi")
            Text(text = "${energyKj.get3DigitsOnly()}kJ")
        }
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
            text = "${energyKKal}kkal",
            textAlign = TextAlign.End
        )
        Divider(modifier = Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Lemak")
            Text(text = "${fat}g")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Lemak Jenuh")
                Text(text = "Lemak tak Jenuh Ganda")
                Text(text = "Lemak tak Jenuh Tunggal")
            }
            Column {
                Text(text = "${saturatedFat}g")
                Text(text = "${polyunsaturatedFat}g")
                Text(text = "${monosaturatedFat}g")
            }
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kolesterol")
            Text(text = "${cholesterol}mg")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Protein")
            Text(text = "${protein}g")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Karbohidrat")
            Text(text = "${carbohydrate}g")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Serat")
                Text(text = "Gula")
            }
            Column {
                Text(text = "${fiber}g")
                Text(text = "${sugar}g")
            }
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Sodium")
            Text(text = "${sodium}mg")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kalium")
            Text(text = "${potassium}mg")
        }
        Divider(Modifier.padding(vertical = 6.dp), thickness = 4.dp)
    }
}
