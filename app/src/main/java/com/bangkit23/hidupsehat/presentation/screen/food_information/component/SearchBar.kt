package com.bangkit23.hidupsehat.presentation.screen.food_information.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconToggleButton(
            checked = isSearching,
            onCheckedChange = { isSearching = it },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = if (isSearching) Color.Gray else Color.Black
            )
        }

        AnimatedVisibility(visible = isSearching) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                placeholder = {
                    Text(text = "Search")
                },
                singleLine = true,
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPrev() {
    SearchBar()
}