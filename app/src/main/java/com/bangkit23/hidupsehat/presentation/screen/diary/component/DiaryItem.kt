package com.bangkit23.hidupsehat.presentation.screen.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_primaryContainer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    selected: Boolean,
    label: String,
    onChipClicked: (String) -> Unit
) {
    InputChip(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        selected = selected,
        onClick = {
            onChipClicked(label)
        }, label = {
            Text(label)
        })
}

@Preview
@Composable
fun DiaryItemPrev() {
    Chip(selected  = false, label = "Semangat", onChipClicked = {})
}