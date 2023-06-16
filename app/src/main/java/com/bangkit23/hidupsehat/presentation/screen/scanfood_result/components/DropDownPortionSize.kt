package com.bangkit23.hidupsehat.presentation.screen.scanfood_result.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bangkit23.hidupsehat.domain.model.food.Food

@Composable
fun DropDownPortionSize(
    isDropDownVisible: Boolean,
    portionSizes: List<Food>,
    onDismissRequest: () -> Unit,
    onDropDownItemClick: (Food) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center),
    ) {
        DropdownMenu(
            expanded = isDropDownVisible,
            onDismissRequest = onDismissRequest,
        ) {
            portionSizes.forEach {
                DropdownMenuItem(
                    text = { Text("${it.portionSize}") },
                    onClick = {
                        onDropDownItemClick(it)
                    }
                )
            }
        }
    }
}
