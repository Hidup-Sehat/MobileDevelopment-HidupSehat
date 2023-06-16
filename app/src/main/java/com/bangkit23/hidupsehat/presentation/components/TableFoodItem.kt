package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.scanfood_result.components.DropDownPortionSize

@Composable
fun TableFoodItem(
    foodName: String?,
    portionSize: String?,
    count: Int?,
    energyKKal: Double?,
    portionSizes: List<Food>,
    onDropDownItemClick: (Food, Int?) -> Unit,
    onPortionSizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Box {
        Row(
            modifier = modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TableCell(
                text = "$foodName",
                weight = 0.3f,
                alignment = TextAlign.Left,
                modifier = Modifier.padding(start = 8.dp)
            )
            Row(
                modifier = Modifier
                    .weight(0.3f)
                    .clickable {
                        onPortionSizeClick()
                        isContextMenuVisible = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableCell(
                    text = "$portionSize",
                    weight = 1f,
                )
                Icon(
                    imageVector = if (isContextMenuVisible) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                    contentDescription = null
                )
            }
            TableCell(
                text = "$count",
                weight = 0.2f,
            )
            TableCell(
                text = "$energyKKal",
                weight = 0.2f,
                alignment = TextAlign.Right,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        )
        DropDownPortionSize(
            isDropDownVisible = isContextMenuVisible,
            portionSizes = portionSizes,
            onDismissRequest = {
                isContextMenuVisible = false
            },
            onDropDownItemClick = {
                onDropDownItemClick(it, count)
                isContextMenuVisible = false
            }
        )
    }
}