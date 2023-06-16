package com.bangkit23.hidupsehat.presentation.screen.food_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.food_history.component.ItemHistory
import com.bangkit23.hidupsehat.presentation.screen.food_history.component.ProgressHistory
import com.bangkit23.hidupsehat.presentation.screen.food_history.component.WidgetHistoryItem
import com.bangkit23.hidupsehat.presentation.screen.food_information_detail.component.DetailItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodHistoryScreen() {
    val list = listOf(
        Food(
            id = 2887,
            name = "Nasi Putih",
            portionSize = "100 gram (g)",
            energyKj = 540.0,
            energyKKal = 129.0,
            sugar = 0.05,
            potassium = 35.0,
            carbohydrate = 27.9,
            cholesterol = 0.0,
            fat = 0.28,
            saturatedFat = 0.076,
            transFat = 0.0,
            polyunsaturatedFat = 0.075,
            monounsaturatedFat = 0.087,
            protein = 2.66,
            fiber = 0.4,
            sodium = 365.0
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Makanan") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            FoodHistoryContent(
                modifier = Modifier.padding(it),
                listFoods = list
            )
        }
    )

}

@Composable
fun FoodHistoryContent(
    modifier: Modifier = Modifier,
    listFoods: List<Food>
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ChevronLeft, contentDescription = null)
            }
            Text(
                text = "Hari ini", style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(onClick = {}) {
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            WidgetHistoryItem(
                data = "2400",
                label = "Asupan min.",
                icon = painterResource(id = R.drawable.ic_asupan_min)
            )
            //circular progress bar
            ProgressHistory(
                modifier = Modifier.size(150.dp),
                caloriesIntakeActual = 500.0,
                caloriesIntakeExpected = 700.0
            )
            WidgetHistoryItem(
                data = "3000",
                label = "Asupan max.",
                icon = painterResource(id = R.drawable.ic_asupan_max)
            )
        }

        CardFoodHistoryContent(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp),

        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 20.dp),
            text = "Catatan Makanan", style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
        )
        Column {
            listFoods.forEach {
                ItemHistory(
                    foodName = it.name.toString(),
                    count = it.count,
                    portionSize = "${it.portionSize}",
                    energyKKal = it.energyKKal ?: 0.0
                )
            }
        }

    }
}

@Composable
fun CardFoodHistoryContent(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (cv1, cv2, cv3, cv4) = createRefs()
        DetailItem(
            modifier = Modifier
                .constrainAs(cv1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(cv2.start, 10.dp)
                    width = Dimension.fillToConstraints
                },
            title = "Karbohidrat",
            data = "-",
            color = colorResource(id = R.color.green_tile)
        )

        DetailItem(
            modifier = Modifier.constrainAs(cv2) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(cv1.end)
                width = Dimension.fillToConstraints
            },
            title = "Lemak", data = "-", color = colorResource(id = R.color.blue_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv3) {
                top.linkTo(cv1.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(cv4.start, 10.dp)
                width = Dimension.fillToConstraints
            },
            title = "Serat", data = "-", color = colorResource(id = R.color.yellow_tile)
        )
        DetailItem(
            modifier = Modifier.constrainAs(cv4) {
                top.linkTo(cv2.bottom, 10.dp)
                start.linkTo(cv3.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            title = "Protein", data = "-", color = colorResource(id = R.color.red_tile)
        )
    }
}

@Preview
@Composable
fun FoodHistoryContentPrev() {
    FoodHistoryScreen()
}