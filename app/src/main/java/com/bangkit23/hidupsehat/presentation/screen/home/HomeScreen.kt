package com.bangkit23.hidupsehat.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.user.UserNeeds
import com.bangkit23.hidupsehat.presentation.components.CardEmotionFeel
import com.bangkit23.hidupsehat.presentation.components.DotsIndicator
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.presentation.screen.home.components.CardPersonalHealthInfo
import com.bangkit23.hidupsehat.presentation.screen.home.model.CardFeature
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel
import com.bangkit23.hidupsehat.presentation.screen.home.model.emotions
import com.bangkit23.hidupsehat.presentation.screen.update_user_info.UpdateUserInfoScreen
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.model.LineData

@Composable
fun HomeScreen(
    onScanClicked: () -> Unit,
    onPoseMenuClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onReminderMenuClicked: () -> Unit,
    onSeeAllMonitoringClick: () -> Unit,
    onManualFoodsClick: () -> Unit,
    onMentalHealthClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        chosenEmotion = state.chosenEmotion,
        onEmotionChosen = {
            viewModel.onEvent(HomeEvent.OnTodayEmotionChosen(it))
        },
        userNeeds = state.userNeeds,
        userData = state.userData,
        onScanClicked = onScanClicked,
        onPoseMenuClicked = onPoseMenuClicked,
        onProfileClicked = onProfileClicked,
        onReminderMenuClicked = onReminderMenuClicked,
        onSeeAllMonitoringClick = onSeeAllMonitoringClick,
        onManualFoodsClick = onManualFoodsClick,
        onMentalHealthClick = onMentalHealthClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    chosenEmotion: Feel?,
    userNeeds: UserNeeds,
    userData: UserData?,
    onEmotionChosen: (Feel?) -> Unit,
    onScanClicked: () -> Unit,
    onPoseMenuClicked: () -> Unit,
    onReminderMenuClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onSeeAllMonitoringClick: () -> Unit,
    onManualFoodsClick: () -> Unit,
    onMentalHealthClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarWithProfile(
                name = "${userData?.username?.split(" ")?.getOrNull(0)}",
                userAvatar = userData?.profilePictureUrl,
                onNotificationClick = {},
                onProfileClick = {
                    onProfileClicked()
                }
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it)
                .verticalScroll(rememberScrollState())
            ,
        ) {
            var openUpdateInfoSheet by rememberSaveable { mutableStateOf(false) }

            CardEmotionFeel(
                emotions = emotions,
                onEmotionChosen = onEmotionChosen,
                chosenEmotion = chosenEmotion
            )
            CardPersonalHealthInfo(
                caloriesIntakeActual = userNeeds.actualCalorie ?: 0,
                waterDrunkActual = userNeeds.actualWater ?: 0,
                sleepTimeActual = userNeeds.actualSleep ?: 0,
                caloriesBurnedActual = userNeeds.actualCalorie ?: 0,
                caloriesIntakeExpected = userNeeds.calorieNeeds ?: 2400,
                waterDrunkExpected = userNeeds.waterNeeds ?: 0.0,
                sleepTimeExpected = userNeeds.sleepNeeds ?: 0,
                caloriesBurnedExpected = userNeeds.calorieNeeds ?: 0,
                onScanClicked = onScanClicked,
                onWriteManualClicked = onManualFoodsClick,
                onUpdateClicked = {
                    openUpdateInfoSheet = true
                }
            )
            FeaturesMenu(
                onPoseMenuClicked = onPoseMenuClicked,
                onReminderMenuClicked = onReminderMenuClicked,
                onMentalHealthClick = onMentalHealthClick
            )
            MonitoringSection(
                onSeeAllClick = onSeeAllMonitoringClick
            )

            if (openUpdateInfoSheet) {
                UpdateUserInfoScreen(
                    initialCalorieBurned = "${userNeeds.calorieNeeds}",
                    initialCalorieNeeds = "${userNeeds.calorieNeeds}",
                    initialSleepNeeds = userNeeds.sleepNeeds ?: 8,
                    initialWaterNeeds = userNeeds.actualWater ?: 1,
                    onDismissRequest = { openUpdateInfoSheet = false }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonitoringSection(
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Monitoring",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp)
            )
            TextButton(
                onClick = onSeeAllClick,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Text("Lihat Semua")
            }
        }
        MonitoringPager()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithProfile(
    name: String,
    userAvatar: String?,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text("Hi, $name!") },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(Icons.Outlined.Notifications, contentDescription = null)
            }
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = onProfileClick,
            ) {
                if (userAvatar != null) {
                    AsyncImage(
                        model = userAvatar,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = "${name.first()}",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
        },
    )
}

@Composable
fun FeaturesMenu(
    onPoseMenuClicked: () -> Unit,
    onReminderMenuClicked: () -> Unit,
    onMentalHealthClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val itemsCard = listOf(
        CardFeature(
            title = "Yoga & \nWorkouts",
            icon = R.drawable.ic_yoga,
            cardColor = CardDefaults.elevatedCardColors(containerColor = colorResource(R.color.yoga)),
        ),
        CardFeature(
            title = "Mental \nHealth",
            icon = R.drawable.ic_brain,
            cardColor = CardDefaults.elevatedCardColors(containerColor = colorResource(R.color.mental_health)),
        ),
        CardFeature(
            title = "Foods \nInformation",
            icon = R.drawable.ic_food,
            cardColor = CardDefaults.elevatedCardColors(containerColor = colorResource(R.color.foods))
        ),
        CardFeature(
            title = "Reminder",
            icon = R.drawable.ic_alarm,
            cardColor = CardDefaults.elevatedCardColors(containerColor = colorResource(R.color.reminder))
        ),
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row {
            CardFeatureMenu(
                cardData = itemsCard[0],
                modifier = Modifier
                    .weight(1f)
                    .clickable { onPoseMenuClicked() }
            )
            CardFeatureMenu(
                cardData = itemsCard[1],
                modifier = Modifier
                    .weight(1f)
                    .clickable { onMentalHealthClick() }
            )
        }
        Row {
            CardFeatureMenu(
                cardData = itemsCard[2],
                modifier = Modifier.weight(1f)
            )
            CardFeatureMenu(
                cardData = itemsCard[3],
                modifier = Modifier.weight(1f)
                    .clickable { onReminderMenuClicked() }
            )
        }
    }
}

@Composable
fun CardFeatureMenu(cardData: CardFeature, modifier: Modifier = Modifier) {
    ElevatedCard(
        colors = cardData.cardColor,
        modifier = modifier
            .heightIn(112.dp)
            .padding(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = cardData.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(cardData.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonitoringPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 8.dp, bottom = 16.dp),
    ) {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageCount = 4,
            state = pagerState,
            pageSpacing = 16.dp,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(MaterialTheme.shapes.medium.topStart))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Performa",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "7 hari terakhir",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(Modifier.height(16.dp))
                        LineChart(
                            lineData = listOf(
                                LineData("3/6", 1.0f),
                                LineData("4/6", 0.3f),
                                LineData("5/6", 0.2f),
                                LineData("6/6", 0.4f),
                                LineData("7/6", 0.8f),
                                LineData("8/6", 0.6f),
                                LineData("9/6", 1.0f),
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(144.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        DotsIndicator(
            totalDots = 4,
            selectedIndex = pagerState.currentPage,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HidupSehatTheme {
        HomeContent(
            userNeeds = UserNeeds(),
            userData = null,
            chosenEmotion = null,
            onManualFoodsClick = {},
            onEmotionChosen = {},
            onScanClicked = {},
            onPoseMenuClicked = {},
            onProfileClicked = {},
            onReminderMenuClicked = {},
            onSeeAllMonitoringClick = {},
            onMentalHealthClick = {},
        )
    }
}