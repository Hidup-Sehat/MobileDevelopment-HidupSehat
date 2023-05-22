package com.bangkit23.hidupsehat.presentation.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.model.Food
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetWriteFoodsManual(
    foods: List<Food>,
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(
        confirmValueChange = {
            false
        }
    ),
) {
    SheetWithHeader(
        onDismiss = onDismiss,
        sheetState = sheetState,
        contentHeader = {
            HeaderSheetFoods(
                onSaveClick = onSaveClick
            )
        },
        contentBody = {
            BodySheetFoods(
                foods = foods
            )
        },
        modifier = modifier
            .fillMaxHeight()
    )
}

@Composable
fun HeaderSheetFoods(
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Manual Input",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Masukkan makanan anda",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        ButtonWithIcon(
            onClick = onSaveClick,
            text = "Simpan",
            icon = Icons.Rounded.Check,
        )
    }
}

@Composable
fun BodySheetFoods(
    foods: List<Food>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Makanan",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.width(112.dp)
                )
                Text(
                    text = "Jumlah",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Ukuran",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Kalori",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        items(items = foods) {
            ItemFood(
                name = it.name,
                count = it.count,
                size = 1,
                calories = it.calories.toDouble()
            )
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp)
            ) {
                OutlinedButtonWithIcon(
                    onClick = {},
                    text = "Tambah Makanan",
                    icon = Icons.Rounded.Add,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun ItemFood(
    name: String,
    count: Int,
    size: Int,
    calories: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable {  }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = name,
                modifier = Modifier.width(112.dp)
            )
            Text(
                text = "$size",
                modifier = Modifier
            )
            Text(
                text = "$count",
                modifier = Modifier
            )
            Text(
                text = "${calories.toInt()}",
                modifier = Modifier
            )
        }
        Spacer(Modifier.height(16.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemFoodPreview() {
    HidupSehatTheme {
        ItemFood(
            name = "Ayam Goreng",
            count = 40,
            size = 5,
            calories = 46.0,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSheetWriteFoodsManual() {
    HidupSehatTheme {
        BodySheetFoods(
            foods = listOf(Food("Kentang", 3, 500))
        )
    }
}