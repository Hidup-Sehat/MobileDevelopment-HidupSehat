package com.bangkit23.hidupsehat.presentation.screen.scanfood_edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components.CustomTextFieldLabel
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components.DropDownEditFood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanFoodResultEditScreen(
    food: Food?,
    portionSizes: List<Food>,
    onDismiss: () -> Unit,
    onSaveClick: (Food?) -> Unit,
    onDeleteClick: (Food?) -> Unit,
    onPortionSizeClick: (Food?) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(
        confirmValueChange = { false },
        skipPartiallyExpanded = true
    ),
) {
    var editFood by remember { mutableStateOf(food) }
    var foodCount by remember { mutableStateOf(food?.count) }

    SheetWithHeader(
        sheetState = sheetState,
        contentBody = {
            SheetEditContent(
                food = editFood,
                onSaveClick = {
                    onSaveClick(
                        editFood?.copy(count = foodCount ?: 1)
                    )
                },
                onPortionSizeClick = onPortionSizeClick,
                onDropDownItemClick = {
                    editFood = it
                },
                portionSizes = portionSizes,
                onFoodCountChange = {
                    foodCount = if (it.isNotEmpty()) {
                        it.toInt()
                    } else null
                },
                foodCount = foodCount
            )
        },
        onDismiss = onDismiss,
        contentHeader = {
            SheetEditHeader(
                onDeleteClick = {
                    onDeleteClick(food)
                }
            )
        })
}

@Composable
fun SheetEditContent(
    food: Food?,
    foodCount: Int?,
    portionSizes: List<Food>,
    onSaveClick: () -> Unit,
    onPortionSizeClick: (Food?) -> Unit,
    onDropDownItemClick: (Food?) -> Unit,
    onFoodCountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextFieldLabel(
            label = "Nama Makanan",
            enabled = false,
            onValueChange = {},
            value = "${food?.name}"
        )
        Spacer(Modifier.height(16.dp))
        CustomTextFieldLabel(
            label = "Kalori",
            enabled = false,
            onValueChange = {},
            value = "${food?.energyKKal}"
        )
        Spacer(Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DropDownEditFood(
                value = "${food?.portionSize}",
                portionSizes = portionSizes,
                onDropDownItemClick = onDropDownItemClick,
                onPortionSizeClick = {
                    onPortionSizeClick(food)
                },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomTextFieldLabel(
                modifier = Modifier.weight(1f),
                label = "Jumlah",
                onValueChange = onFoodCountChange,
                value = (foodCount ?: "").toString()
            )
        }
        ButtonWithIcon(
            modifier = Modifier.padding(top = 32.dp),
            text = "Simpan Perubahan",
            icon = Icons.Default.Done,
            onClick = { onSaveClick() }
        )
    }
}

@Composable
fun SheetEditHeader(
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Ubah", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
        Text(
            modifier = Modifier.clickable {
                onDeleteClick()
            },
            text = "Hapus",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.error
        )
    }
}
