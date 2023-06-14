package com.bangkit23.hidupsehat.presentation.screen.food_information.component

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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FoodInformationItem(
    modifier: Modifier = Modifier,
    name: String,
    unit: String,
    calories: String,
    onItemClick : (String) -> Unit
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(name) },
            supportingContent = {
                Text(text = "$calories kal")
            },
            trailingContent = {
                IconButton(onClick = {
                    onItemClick(name)
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
//    FoodInformationItem(name = "Nasi Goreng", unit = "100g", calories = "168 kal", onItemClick = {})
}