package com.bangkit23.hidupsehat.presentation.screen.monitoring

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.DaySlider
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.ItemFoodHistory
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.ItemNutritionHistory
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.MoodCard
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.SleepCard
import com.bangkit23.hidupsehat.presentation.screen.monitoring.components.TableNutritionHeader
import com.bangkit23.hidupsehat.presentation.screen.monitoring.model.Nutrition
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun MonitoringScreen(
    viewModel: MonitoringViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MonitoringContent(
        historyFoods = state.historyFoods,
        historyNutrition = state.historyNutrition
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringContent(
    historyFoods: List<Food>,
    historyNutrition: List<Nutrition>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoring") },
                navigationIcon = {
                    IconButton(
                        onClick = {},
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
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "More"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
        ) {
            DaySlider(
                day = "Hari Ini",
                onPreviousDayClick = {},
                onNextDayClick = {}
            )
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    MoodCard(
                        icon = "\uD83D\uDE42",
                        positiveEmotion = "Senang",
                        negativeEmotion = "Cemas",
                        modifier = Modifier.weight(1f)
                            .height(112.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    SleepCard(
                        sleepActual = 3,
                        sleepNeeds = 7,
                        modifier = Modifier.weight(1f)
                            .height(112.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Riwayat Makanan",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Lihat Selengkapnya",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                historyFoods.forEach {
                    ItemFoodHistory(
                        foodName = it.name.toString(),
                        count = it.count,
                        portionSize = "${it.portionSize}",
                        energyKKal = it.energyKKal ?: 0.0
                    )
                }
                Spacer(Modifier.height(24.dp))
                NutritionHistory(
                    historyNutrition = historyNutrition
                )
            }
        }
    }
}

@Composable
fun NutritionHistory(
    historyNutrition: List<Nutrition>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Riwayat Nutrisi",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        TableNutritionHeader()
        historyNutrition.forEach {
            ItemNutritionHistory(
                nutritionName = it.nutritionName,
                total = it.total,
                target = it.target,
                remain = it.remain,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonitoringContentPreview() {
    HidupSehatTheme {
        MonitoringContent(
            historyFoods = listOf(),
            historyNutrition = listOf()
        )
    }
}