package com.bangkit23.hidupsehat.presentation.screen.emotion_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.domain.model.diary.Diary
import com.bangkit23.hidupsehat.presentation.screen.emotion_history.components.CardEmotion
import com.bangkit23.hidupsehat.presentation.screen.emotion_history.components.EmotionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionHistoryScreen(
    viewModel: EmotionHistoryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = state.data) {}
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Emotion History") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            state.data?.let { it1 ->
                EmotionHistoryContent(
                    modifier = Modifier.padding(it),
                    data = it1
                )
            }
        }
    )
}

@Composable
fun EmotionHistoryContent(modifier: Modifier = Modifier, data: Diary) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        WeekSection()
        LabelHistory(
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            text = "Keseluruhan"
        )
        CardEmotion(
            positive = data.emotionPositive,
            negative = data.emotionNegative,
            source = data.emotionSource
        )
        LabelHistory(
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            text = "Catatan Emosi"
        )
        EmotionItem(
            title = data.note,
            dateTime = data.lastUpdated,
            emotionIcon = ""
        )
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

        LabelHistory(text = "Hari Ini", modifier = Modifier.weight(1f), align = TextAlign.Center)
    }
}

@Composable
fun LabelHistory(
    modifier: Modifier = Modifier,
    text: String,
    align: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text, style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium, fontSize = 16.sp
        ), textAlign = align
    )
}

@Preview(showBackground = true)
@Composable
fun LabelHistoryPrev() {
    LabelHistory(text = "Keseluruhan")
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