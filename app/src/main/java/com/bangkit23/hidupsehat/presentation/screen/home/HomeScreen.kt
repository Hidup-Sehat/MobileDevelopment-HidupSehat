package com.bangkit23.hidupsehat.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.home.components.CardPersonalHealthInfo
import com.bangkit23.hidupsehat.presentation.screen.home.components.HomeSection
import com.bangkit23.hidupsehat.presentation.screen.home.model.Feel
import com.bangkit23.hidupsehat.presentation.screen.home.model.emotions
import com.bangkit23.hidupsehat.presentation.components.CardEmotionFeel
import com.bangkit23.hidupsehat.presentation.screen.home.model.CardFeature

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeContent()
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
        ,
    ) {
        var chosenEmotion by remember { mutableStateOf<Feel?>(null) }
        CardEmotionFeel(
            emotions = emotions,
            onEmotionChosen = { feel ->
                chosenEmotion = feel
            },
            chosenEmotion = chosenEmotion
        )
        CardPersonalHealthInfo(
            caloriesIntakeActual = 200.0,
            waterDrunkActual = 3.4,
            sleepTimeActual = 4.0,
            caloriesBurnedActual = 400.0,
            onScanClicked = {},
            onWriteManualClicked = {},
            onCardClicked = {}
        )
        FeaturesMenu()
        HomeSection(
            title = "Terbaru",
            content = { NewFeaturePager() }
        )
        HomeSection(
            title = "Monitoring",
            content = { MonitoringPager() }
        )
    }
}


@Composable
fun FeaturesMenu(
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
        )
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row {
            CardFeatureMenu(itemsCard[0], modifier = Modifier.weight(1f))
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
                style = MaterialTheme.typography.titleMedium,
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
fun NewFeaturePager(modifier: Modifier = Modifier) {
    var sliderImage by remember { mutableStateOf("") }
    val pagerState = rememberPagerState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 16.dp),
    ) {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageCount = 3,
            state = pagerState,
            pageSpacing = 16.dp,
        ) { page ->
            sliderImage = when (page) {
                0 -> "https://www.chameleon.io/img/containers/assets/blog/new-feature-announcements.jpg/af6dab444f841848b57180bb941222b2.jpg"
                1 -> "https://announcekit.app/blog/wp-content/uploads/2021/12/Frame-41.png"
                else -> "https://i.ytimg.com/vi/QTkpSTHLcIE/maxresdefault.jpg"
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = sliderImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .height(112.dp)
                        .clip(RoundedCornerShape(MaterialTheme.shapes.medium.topStart))
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonitoringPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
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
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
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