package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.bangkit23.hidupsehat.presentation.screen.home.model.Emotion
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel

@Composable
fun CardEmotionFeel(
    emotions: List<Emotion>,
    onEmotionChosen: (Feel?) -> Unit,
    modifier: Modifier = Modifier,
    chosenEmotion: Feel? = null,
) {
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "Bagaimana perasaanmu hari ini?",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(emotions) { emotion ->
                    Text(
                        emotion.emoticon,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .background(
                                color = if (chosenEmotion != emotion.feel) Color.Transparent else MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable { onEmotionChosen(emotion.feel) }
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}