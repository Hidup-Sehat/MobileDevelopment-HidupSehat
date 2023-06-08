package com.bangkit23.hidupsehat.presentation.screen.food_history.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bangkit23.hidupsehat.presentation.screen.home.components.CircularProgressBar

@Composable
fun ProgressHistory(
    caloriesIntakeActual: Double,
    modifier: Modifier = Modifier
) {
    val progress by remember { mutableStateOf((caloriesIntakeActual) * 100.0) }
    val percentage by remember { mutableStateOf(progress / 100.0) }
    ConstraintLayout(modifier = modifier) {
        val (progressBar, caloriesIntake, calorieText) = createRefs()

        CircularProgressBar(
            percentage = percentage.toFloat(),
            strokeWidth = 16.dp,
            modifier = Modifier.constrainAs(progressBar) {
                top.linkTo(parent.top)
            }
        )
        Text(
            text = "${caloriesIntakeActual.toInt()}",
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Visible,
            modifier = Modifier.constrainAs(caloriesIntake) {
                top.linkTo(progressBar.top)
                bottom.linkTo(progressBar.bottom)
                start.linkTo(progressBar.start)
                end.linkTo(progressBar.end)
            }
        )
        Text(
            text = "kkal",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.constrainAs(calorieText) {
                top.linkTo(caloriesIntake.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview
@Composable
fun ProgressHistoryPrev() {
    ProgressHistory(caloriesIntakeActual = 1024.0)
}