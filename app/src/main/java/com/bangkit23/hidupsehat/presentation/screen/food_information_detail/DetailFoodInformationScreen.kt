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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.components.CardFoodInformation
import com.bangkit23.hidupsehat.presentation.screen.food_information_detail.component.CardFoodContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFoodInformationScreen(
    name : String,
    onNavigateUp : () -> Unit,
    viewModel: DetailFoodInformationViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit){
        viewModel.onEvent(DetailFoodInformationEvent.OnGetFoodById(name))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
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
        content = {padding ->
            state.food?.let {
                DetailFoodInformationContent(
                    modifier = Modifier.padding(padding),
                    food = it
                )
            }
        }
    )
}

@Composable
fun DetailFoodInformationContent(
    food : Food,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = food.name!!, maxLines = 3, style = MaterialTheme.typography.headlineMedium
        )
        Divider(
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1 / 4f),
                value = "",
                onValueChange = {},
                label = { Text("Qty") })
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                modifier = Modifier.weight(3 / 4f),
                value = "",
                onValueChange = {},
                label = { Text("Ukuran Porsi") })
        }

        CardFoodContent(
            modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 20.dp)
        )

        InformasiGizi(
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Composable
fun InformasiGizi(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color.LightGray)
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Energi")
            Text(text = "0 kJ")
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            text = "0 kkal", textAlign = TextAlign.End
        )
        Divider(modifier = Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Lemak")
            Text(text = "0,0g")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
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
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kolesterol")
            Text(text = "0mg")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Protein")
            Text(text = "0,0g")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Karbohidrat")
            Text(text = "0,00g")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
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
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Sodium")
            Text(text = "0mg")
        }
        Divider(Modifier.padding(vertical = 6.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kalium")
            Text(text = "0,0mg")
        }
        Divider(Modifier.padding(vertical = 6.dp), thickness = 4.dp)
    }
}