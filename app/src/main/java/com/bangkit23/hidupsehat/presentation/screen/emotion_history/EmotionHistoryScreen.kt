package com.bangkit23.hidupsehat.presentation.screen.emotion_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.screen.emotion_history.components.CardEmotion
import com.bangkit23.hidupsehat.presentation.screen.emotion_history.components.EmotionItem
import com.bangkit23.hidupsehat.presentation.screen.emotion_history.model.EmotionHistory

@Composable
fun EmotionHistoryScreen() {
    Scaffold(
        topBar = {},
        content = {
            EmotionHistoryContent(modifier = Modifier.padding(it))
        }
    )
}

@Composable
fun EmotionHistoryContent(modifier: Modifier = Modifier) {
    val list = listOf<EmotionHistory>(
        EmotionHistory("Netral", "Rabu, 7 Juni 2023 19:52", "\uD83D\uDE10")
    )
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        WeekSection()
        Text(text = "Keseluruhan")
        CardEmotion()
        Text(text = "Catatan Emosi")
        LazyColumn() {
            items(list) { item ->
                EmotionItem(
                    title = item.title,
                    dateTime = item.dateTime,
                    emotionIcon = item.unicode
                )
                EmotionItem(
                    title = item.title,
                    dateTime = item.dateTime,
                    emotionIcon = item.unicode
                )
            }
        }
    }
}

@Composable
fun WeekSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier,
            onClick = {}) {
            Icon(Icons.Default.ChevronLeft, contentDescription = null)
        }

        Text(
            modifier = Modifier.weight(1f),
            text = "Minggu Ini",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun EmotionHistoryScreenPrev() {
    EmotionHistoryScreen()
}

@Preview(showBackground = true)
@Composable
fun WeeSectionPrev() {
    WeekSection()
}