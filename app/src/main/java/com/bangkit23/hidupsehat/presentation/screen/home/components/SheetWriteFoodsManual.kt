package com.bangkit23.hidupsehat.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetWriteFoodsManual(
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState()
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
            BodySheetFoods()
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
        TextButton(
            onClick = onSaveClick
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun BodySheetFoods(
    modifier: Modifier = Modifier,
) {
    
}

@Composable
fun ItemFood(
    name: String,
    count: Int,
    size: Int,
    calories: Double,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = name)
            Text(text = "$size")
            Text(text = "$count")
            Text(text = "${calories.toInt()} kal")
        }
        Spacer(Modifier.height(24.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemFoodPreview() {
    HidupSehatTheme {
        ItemFood(
            name = "Ayam",
            count = 4,
            size = 5,
            calories = 46.0,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewSheetWriteFoodsManual() {
    HidupSehatTheme {
        SheetWriteFoodsManual(
            onDismiss = {},
            onSaveClick = {}
        )
    }
}