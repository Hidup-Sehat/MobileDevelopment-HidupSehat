package com.bangkit23.hidupsehat.presentation.screen.mental_health

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.presentation.components.CardEmotionFeel
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.ListItemExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.home.model.emotions
import com.bangkit23.hidupsehat.presentation.screen.mental_health.components.FilledEmotionCard
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun MentalHealthScreen(
    navigateUp: () -> Unit,
    viewModel: MentalHealthViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MentalHealthContent(
        welcomeWords = "Good Evening, Rijal",
        hasFilledEmotion = true,
        musicName = "River Flows in You - Yiruma",
        isMusicPlay = false,
        musicProgress = 0.43f,
        recommendedConsultations = emptyList(),
        recommendedFeeds = emptyList(),
        recommendedActivities = state.recommendationActivities,
        onItemFeedClick = {},
        onItemConsultationClick = {},
        onItemActivityClick = {},
        onMusicNext = {},
        onMusicPrevious = {},
        onMusicPause = {},
        onMusicPlay = {},
        navigateUp = navigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentalHealthContent(
    welcomeWords: String,
    hasFilledEmotion: Boolean,
    musicName: String,
    isMusicPlay: Boolean,
    musicProgress: Float,
    recommendedActivities: List<Exercise>,
    recommendedFeeds: List<Exercise>,
    recommendedConsultations: List<Exercise>,
    onItemActivityClick: (Exercise) -> Unit,
    onItemFeedClick: () -> Unit,
    onItemConsultationClick: () -> Unit,
    navigateUp: () -> Unit,
    onMusicPlay: () -> Unit,
    onMusicPause: () -> Unit,
    onMusicNext: () -> Unit,
    onMusicPrevious: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mental Health") },
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.HelpOutline,
                            contentDescription = "Help"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 128.dp)
            ) {
                Text(
                    text = welcomeWords,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(16.dp)
                )
                if (hasFilledEmotion) {
                    FilledEmotionCard(
                        emoji = "\uD83D\uDE2D",
                        emotionType = "Sangat Sedih",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                } else {
                    CardEmotionFeel(
                        emotions = emotions,
                        onEmotionChosen = {},
                    )
                }
                ListItemExercise(
                    headerText = "Rekomendasi Aktivitas",
                    data = recommendedActivities,
                    onItemClicked = onItemActivityClick,
                    modifier = Modifier
                        .padding(top = 32.dp)
                )
                ListItemExercise(
                    headerText = "Rekomendasi Feeds",
                    data = recommendedActivities,
                    onItemClicked = onItemActivityClick,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                ListItemExercise(
                    headerText = "Konsultasikan Masalahmu",
                    data = recommendedActivities,
                    onItemClicked = onItemActivityClick,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
            MusicPlayer(
                musicName = musicName,
                isMusicPlay = isMusicPlay,
                musicProgress = musicProgress,
                onMusicPlay = onMusicPlay,
                onMusicPause = onMusicPause,
                onMusicNext = onMusicNext,
                onMusicPrevious = onMusicPrevious,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun MusicPlayer(
    musicName: String,
    isMusicPlay: Boolean,
    musicProgress: Float,
    onMusicPlay: () -> Unit,
    onMusicPause: () -> Unit,
    onMusicNext: () -> Unit,
    onMusicPrevious: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = musicName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = musicProgress,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onMusicPrevious
                ) {
                    Icon(
                        imageVector = Icons.Outlined.SkipPrevious,
                        contentDescription = "Previous"
                    )
                }
                IconButton(
                    onClick = if (isMusicPlay) onMusicPause else onMusicPlay
                ) {
                    Icon(
                        imageVector = if (isMusicPlay) Icons.Outlined.Pause else Icons.Outlined.PlayArrow,
                        contentDescription = if (isMusicPlay) "Pause Music" else "Play Music"
                    )
                }
                IconButton(
                    onClick = onMusicNext
                ) {
                    Icon(
                        imageVector = Icons.Outlined.SkipNext,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicPlayerPreview() {
    HidupSehatTheme {
        MusicPlayer(
            musicName = "Muse - Hysteria",
            isMusicPlay = false,
            musicProgress = 0.3f,
            onMusicPlay = {},
            onMusicPause = {},
            onMusicPrevious = {},
            onMusicNext = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MentalHealthContentPreview() {
    HidupSehatTheme {
        MentalHealthContent(
            welcomeWords = "Good evening, Rijal",
            musicName = "Muse - Hysteria",
            isMusicPlay = false,
            musicProgress = 0.3f,
            onMusicPlay = {},
            onMusicPause = {},
            onMusicPrevious = {},
            onMusicNext = {},
            hasFilledEmotion = false,
            recommendedActivities = emptyList(),
            recommendedConsultations = emptyList(),
            recommendedFeeds = emptyList(),
            navigateUp = {},
            onItemActivityClick = {},
            onItemConsultationClick = {},
            onItemFeedClick = {},
        )
    }
}