package com.bangkit23.hidupsehat.presentation.screen.scanfood_edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.model.Food
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components.CustomTextFieldLabel
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanFoodResultEditScreen(
    modifier: Modifier = Modifier,
    food: Food,
    onDismiss: (() -> Unit)? = null,
    onSaveClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null,
    sheetState: SheetState = rememberModalBottomSheetState(
        confirmValueChange = { false },
        skipPartiallyExpanded = true
    )
) {
    if (onDismiss != null) {
        SheetWithHeader(
            sheetState = sheetState,
            contentBody = {
                SheetEditContent(food = food
                )
            }, onDismiss = onDismiss,
            contentHeader = {
                SheetEditHeader(
                    onDeleteClick = onDeleteClick
                )
            })
    }
}

@Composable
fun SheetEditContent(
    food : Food,
    onSaveClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextFieldLabel(label = "Nama Makanan", enabled = false, onValueChange = {}, value = food.name)
        CustomTextFieldLabel(label = "Kalori", enabled = false, onValueChange = {}, value = food.calories.toString())
        CustomTextFieldLabel(label = "Ukuran", onValueChange = {})
        CustomTextFieldLabel(label = "Jumlah", onValueChange = {}, value = food.count.toString())
        ButtonWithIcon(
            modifier = Modifier.padding(top = 32.dp),
            text = "Simpan", icon = Icons.Default.Done, onClick = { onSaveClick?.let { it() } })
    }
}

@Composable
fun SheetEditHeader(
    onDeleteClick: (() -> Unit)? = null
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
                onDeleteClick?.let { it() }
            },
            text = "Hapus",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = md_theme_light_error
        )
    }
}
