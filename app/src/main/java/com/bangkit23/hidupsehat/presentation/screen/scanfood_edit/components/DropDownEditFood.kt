package com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.scanfood_result.components.DropDownPortionSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownEditFood(
    value: String,
    portionSizes: List<Food>,
    onDropDownItemClick: (Food?) -> Unit,
    onPortionSizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = selectedText,
        label = { Text("Takaran") },
        onValueChange = {},
        readOnly = true,
        colors = TextFieldDefaults.colors(
            disabledTextColor = LocalContentColor.current,
            disabledLabelColor = LocalContentColor.current,
            disabledContainerColor = Color.Transparent,
        ),
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        },
        shape = MaterialTheme.shapes.medium,
        enabled = false,
        modifier = modifier
            .clickable {
                onPortionSizeClick()
                expanded = true
            }
    )
    DropDownPortionSize(
        isDropDownVisible = expanded,
        portionSizes = portionSizes,
        onDismissRequest = {
            expanded = false
        },
        onDropDownItemClick = {
            selectedText = it.portionSize.toString()
            onDropDownItemClick(it)
            expanded = false
        }
    )
}