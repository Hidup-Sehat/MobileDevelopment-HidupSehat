package com.bangkit23.hidupsehat.presentation.screen.food_history

import android.widget.GridLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.food_history.component.ProgressHistory
import com.bangkit23.hidupsehat.presentation.screen.food_history.component.WidgetHistoryItem

@Composable
fun FoodHistoryScreen() {


}

@Composable
fun FoodHistoryContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(text = "Hari ini")
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowForward, contentDescription = null)
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
                modifier = Modifier.size(128.dp),
                caloriesIntakeActual = 1024.0)
            WidgetHistoryItem(
                data = "3000",
                label = "Asupan max.",
                icon = painterResource(id = R.drawable.ic_asupan_max)
            )
        }

    }
}

@Preview
@Composable
fun FoodHistoryContentPrev() {
    FoodHistoryContent()
}