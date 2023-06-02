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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.components.CardEmotionFeel
import com.bangkit23.hidupsehat.presentation.screen.home.components.CardPersonalHealthInfo
import com.bangkit23.hidupsehat.presentation.screen.home.components.HomeSection
import com.bangkit23.hidupsehat.presentation.screen.home.components.SheetWriteFoodsManual
import com.bangkit23.hidupsehat.presentation.screen.home.model.CardFeature
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel
import com.bangkit23.hidupsehat.presentation.screen.home.model.emotions
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun HomeScreen(
    onScanClicked: () -> Unit,
    onPoseMenuClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val chosenEmotion by viewModel.chosenEmotion
    val foods = viewModel.foods
    HomeContent(
        chosenEmotion = chosenEmotion,
        onEmotionChosen = viewModel::setEmotion,
        foods = foods,
        onScanClicked = onScanClicked,
        onPoseMenuClicked = onPoseMenuClicked,
        onProfileClicked = onProfileClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    chosenEmotion: Feel?,
    foods: List<Food>,
    onEmotionChosen: (Feel?) -> Unit,
    onScanClicked: () -> Unit,
    onPoseMenuClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarWithProfile(
                name = "Rijal",
                userAvatar = "",
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
            var openBottomSheet by rememberSaveable { mutableStateOf(false) }
            val sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            )
            CardEmotionFeel(
                emotions = emotions,
                onEmotionChosen = onEmotionChosen,
                chosenEmotion = chosenEmotion
            )
            CardPersonalHealthInfo(
                caloriesIntakeActual = 200.0,
                waterDrunkActual = 3.4,
                sleepTimeActual = 4.0,
                caloriesBurnedActual = 400.0,
                onScanClicked = onScanClicked,
                onWriteManualClicked = {
                    openBottomSheet = !openBottomSheet
                },
                onCardClicked = {
                    openBottomSheet = !openBottomSheet
                }
            )
            FeaturesMenu(
                onPoseMenuClicked = onPoseMenuClicked
            )
            HomeSection(
                title = "Monitoring",
                content = { MonitoringPager() }
            )
            if (openBottomSheet) {
                SheetWriteFoodsManual(
                    foods = foods,
                    sheetState = sheetState,
                    onDismiss = { openBottomSheet = false },
                    onSaveClick = {},
                    onItemClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithProfile(
    name: String,
    userAvatar: String,
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
            Spacer(Modifier.width(8.dp))
        },
    )
}

@Composable
fun FeaturesMenu(
    onPoseMenuClicked: () -> Unit,
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
            CardFeatureMenu(itemsCard[0], modifier = Modifier.weight(1f).clickable { onPoseMenuClicked() })
            CardFeatureMenu(itemsCard[1], modifier = Modifier.weight(1f))
        }
        Row {
            CardFeatureMenu(itemsCard[2], modifier = Modifier.weight(1f))
            CardFeatureMenu(itemsCard[3], modifier = Modifier.weight(1f))
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
        modifier = modifier.padding(top = 16.dp, bottom = 16.dp),
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
                        .height(200.dp)
                        .clip(RoundedCornerShape(MaterialTheme.shapes.medium.topStart))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
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

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(count = totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unselectedColor)
                )
            }
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HidupSehatTheme {
        HomeContent(
            foods = emptyList(),
            chosenEmotion = null,
            onEmotionChosen = {},
            onScanClicked = {},
            onPoseMenuClicked = {},
            onProfileClicked = {}
        )
    }
}