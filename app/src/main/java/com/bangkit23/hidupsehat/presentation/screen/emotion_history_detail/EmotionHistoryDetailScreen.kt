package com.bangkit23.hidupsehat.presentation.screen.emotion_history_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.emotion_history_detail.components.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionHistoryDetailScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minggu 31-12-2023 15:45") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Delete, contentDescription = null)
                    }
                }
            )
        },

        content = {
            EmotionHistoryDetailContent(
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
fun EmotionHistoryDetailContent(
    modifier : Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        SectionHeader()
        PercentageSection()
        NotesEmotionSection()
    }
}

@Composable
fun SectionHeader() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        val (title, desc, emotion) = createRefs()

        Text(text = "Pada saat itu, kamu merasa...",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(
            text = "Sangat senang",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(desc) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
            })
        Text(
            text = "\uD83D\uDE04",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 60.sp
            ),
            modifier = Modifier.constrainAs(emotion) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })
    }
}

@Composable
fun PercentageSection() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (
            labelEmotion, valuePercent, pBar,
            valueNegative, labelNegative, emotionNegative,
            valuePositive, labelPostive, emotionPositive,
        ) = createRefs()
        Text(text = "2", modifier = Modifier.constrainAs(valueNegative) {
            start.linkTo(parent.start)
            bottom.linkTo(labelNegative.top)
        })
        Text(text = "Negatif", modifier = Modifier.constrainAs(labelNegative) {
            start.linkTo(parent.start)
            bottom.linkTo(emotionNegative.top)
        })
        Text(text = ":(", modifier = Modifier.constrainAs(emotionNegative) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
        })
        Text(text = "80%", modifier = Modifier.constrainAs(valuePercent) {
            bottom.linkTo(pBar.top, 4.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        LinearProgressIndicator(
            modifier = Modifier.constrainAs(pBar) {
                start.linkTo(emotionNegative.end, 16.dp)
                end.linkTo(emotionPositive.start, 16.dp)
                bottom.linkTo(parent.bottom)
                top.linkTo(emotionNegative.top)
                width = Dimension.fillToConstraints

            }
        )
        Text(text = "Presentase Emosimu", modifier = Modifier.constrainAs(labelEmotion) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(valuePercent.top)
        })
        Text(text = "2", modifier = Modifier.constrainAs(valuePositive) {
            end.linkTo(parent.end)
            bottom.linkTo(labelPostive.top)
        })
        Text(text = "Positif", modifier = Modifier.constrainAs(labelPostive) {
            end.linkTo(parent.end)
            bottom.linkTo(emotionPositive.top)
        })
        Text(text = ":)", modifier = Modifier.constrainAs(emotionPositive) {
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun NotesEmotionSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Informasi Diary", modifier = Modifier.padding(vertical = 10.dp))
        NoteItem(
            title = "Asal Emosimu", dateTime = "Keluarga, Pekerjaan...", color = colorResource(
                id = R.color.card_yellow
            )
        )
        NoteItem(
            title = "Emosi Positifmu",
            dateTime = "Bahagia, Bangga, Semangat...",
            color = colorResource(
                id = R.color.card_green
            )
        )
        NoteItem(
            title = "Emosi Negatifmu", dateTime = "Sedih Kesepian...", color = colorResource(
                id = R.color.card_red
            )
        )
        NoteItem(
            title = "Ceritamu", dateTime = "Kemarin...", color = colorResource(
                id = R.color.card_blue
            )
        )
    }
}

@Preview
@Composable
fun EmotionHistoryDetailPrev() {
    EmotionHistoryDetailScreen()
}

@Preview
@Composable
fun PercentageSectionPrev() {
    EmotionHistoryDetailContent()
}