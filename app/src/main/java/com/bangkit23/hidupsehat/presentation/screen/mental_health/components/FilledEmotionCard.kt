package com.bangkit23.hidupsehat.presentation.screen.mental_health.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun FilledEmotionCard(
    emoji: String,
    emotionType: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            val (emotionEmoji, emotionTodayText, emotionTypeText) = createRefs()

            Text(
                text = emoji,
                fontSize = 40.sp,
                modifier = Modifier
                    .constrainAs(emotionEmoji) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = "Perasaanmu hari ini",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .constrainAs(emotionTodayText) {
                        top.linkTo(emotionEmoji.top)
                        start.linkTo(emotionEmoji.end, margin = 16.dp)
                        bottom.linkTo(emotionTypeText.top)
                    }
            )
            Text(
                text = emotionType,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .constrainAs(emotionTypeText) {
                        top.linkTo(emotionTodayText.bottom, margin = 4.dp)
                        start.linkTo(emotionEmoji.end, margin = 16.dp)
                        bottom.linkTo(emotionEmoji.bottom)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilledEmotionCardPreview() {
    HidupSehatTheme {
        FilledEmotionCard(
            emoji = "\uD83D\uDE2D",
            emotionType = "Sangat Sedih"
        )
    }
}