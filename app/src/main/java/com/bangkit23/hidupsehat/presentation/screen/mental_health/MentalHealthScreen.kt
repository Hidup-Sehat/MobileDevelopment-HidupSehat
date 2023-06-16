package com.bangkit23.hidupsehat.presentation.screen.mental_health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.domain.model.feed.Feed
import com.bangkit23.hidupsehat.presentation.components.CardEmotionFeel
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.ListItemExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.home.model.emotions
import com.bangkit23.hidupsehat.presentation.screen.mental_health.components.FilledEmotionCard
import com.bangkit23.hidupsehat.presentation.screen.mental_health.components.ListItemConsultation
import com.bangkit23.hidupsehat.presentation.screen.mental_health.components.ListItemFeed
import com.bangkit23.hidupsehat.presentation.screen.mental_health.model.Consultation
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerControlView

@Composable
fun MentalHealthScreen(
    navigateUp: () -> Unit,
    navigateToFeedDetail: (String) -> Unit,
    viewModel: MentalHealthViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MentalHealthContent(
        welcomeWords = state.greetingMessage,
        hasFilledEmotion = true,
        musicName = "Nature - Lesfm",
        recommendedConsultations = state.recommendationConsultation,
        recommendedFeeds = state.recommendationFeeds,
        recommendedActivities = state.recommendationActivities,
        onItemFeedClick = {
            navigateToFeedDetail(it.key ?: "")
        },
        onItemConsultationClick = {},
        onItemActivityClick = {},
        navigateUp = navigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentalHealthContent(
    welcomeWords: String,
    hasFilledEmotion: Boolean,
    musicName: String,
    recommendedActivities: List<Exercise>,
    recommendedFeeds: List<Feed>,
    recommendedConsultations: List<Consultation>,
    onItemActivityClick: (Exercise) -> Unit,
    onItemFeedClick: (Feed) -> Unit,
    onItemConsultationClick: (Consultation) -> Unit,
    navigateUp: () -> Unit,
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
                    .padding(bottom = 128.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = welcomeWords,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(16.dp)
                )
                if (hasFilledEmotion) {
                    FilledEmotionCard(
                        emoji = "\uD83D\uDE01",
                        emotionType = "Sangat Senang",
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
                ListItemFeed(
                    headerText = "Rekomendasi Feeds",
                    data = recommendedFeeds,
                    onItemClicked = onItemFeedClick,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                ListItemConsultation(
                    headerText = "Konsultasikan Masalahmu",
                    data = recommendedConsultations,
                    onItemClicked = onItemConsultationClick,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
            MusicPlayer(
                musicName = musicName,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun MusicPlayer(
    musicName: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context)
            .build()
    }

    DisposableEffect(Unit) {
        val mediaItem =
            MediaItem.fromUri("https://github.com/Hidup-Sehat/MobileDevelopment-HidupSehat/raw/development-rijal/assets/nature-99499.mp3")
        player.setMediaItem(mediaItem)
        player.prepare()
        onDispose {
            player.release()
        }
    }

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (musicTitleText, playerControl) = createRefs()
        Text(
            text = musicName,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xCC000000))
                .padding(top = 16.dp)
                .constrainAs(musicTitleText) {
                    bottom.linkTo(playerControl.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        AndroidView(
            factory = {
                PlayerControlView(it).apply {
                    this.showTimeoutMs = 0
                    this.player = player
                }
            },
            modifier = Modifier.fillMaxWidth()
                .constrainAs(playerControl) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusicPlayerPreview() {
    HidupSehatTheme {
        MusicPlayer(
            musicName = "Nature",
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