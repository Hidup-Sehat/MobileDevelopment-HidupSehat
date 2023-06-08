package com.bangkit23.hidupsehat.presentation.screen.food_information.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FoodInformationItem(
    modifier: Modifier = Modifier,
    name: String,
    unit: String,
    calories: String,
    onItemClick : () -> Unit
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(text = name) },
            supportingContent = {
                Text(text = "$unit $calories")
            },
            trailingContent = {
                IconButton(onClick = {
                    onItemClick()
                }) {
                    Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null)
                }
            }
        )
        Divider()
    }
}

@Preview
@Composable
fun FoodInformationItemPrev() {
    FoodInformationItem(name = "Nasi Goreng", unit = "100g", calories = "168 kal", onItemClick = {})
}