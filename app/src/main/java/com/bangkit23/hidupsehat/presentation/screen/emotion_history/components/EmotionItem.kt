package com.bangkit23.hidupsehat.presentation.screen.emotion_history.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun EmotionItem(
    title: String,
    dateTime: String,
    emotionIcon: String
) {
    Box(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(16.dp))

    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            val (tEmotion, tDateTime, tEmotionIcon) = createRefs()
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.constrainAs(tEmotion) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )
            Text(text = dateTime,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.constrainAs(tDateTime) {
                    top.linkTo(tEmotion.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            )
            Text(text = emotionIcon, style = MaterialTheme.typography.titleLarge, modifier = Modifier.constrainAs(tEmotionIcon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EmotionItemPrev() {
    EmotionItem(title = "Netral", dateTime = "Rabu, 7 Juni 2023 19:32", emotionIcon = "\uD83D\uDE10")
}