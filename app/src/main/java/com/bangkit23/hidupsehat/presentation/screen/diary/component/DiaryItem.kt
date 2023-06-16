package com.bangkit23.hidupsehat.presentation.screen.diary.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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