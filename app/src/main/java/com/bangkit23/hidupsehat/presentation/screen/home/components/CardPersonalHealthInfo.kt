package com.bangkit23.hidupsehat.presentation.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun CardPersonalHealthInfo(
    caloriesIntakeActual: Double,
    waterDrunkActual: Double,
    sleepTimeActual: Double,
    caloriesBurnedActual: Double,
    onScanClicked: () -> Unit,
    onWriteManualClicked: () -> Unit,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
    caloriesIntakeExpected: Double = 2400.0,
    waterDrunkExpected: Double = 4.0,
    sleepTimeExpected: Double = 8.0,
    caloriesBurnedExpected: Double = 2000.0,
) {
    val progress by remember { mutableStateOf((caloriesIntakeActual / caloriesIntakeExpected) * 100.0) }
    val percentage by remember { mutableStateOf(progress / 100.0) }
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onCardClicked() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CircularProgressBar(
                percentage = percentage.toFloat(),
                strokeWidth = 16.dp
            )
            ButtonInputFoods(
                onScanClicked = onScanClicked,
                onWriteManualClicked = onWriteManualClicked
            )
        }
    }
}

@Composable
fun ButtonInputFoods(
    onScanClicked: () -> Unit,
    onWriteManualClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        ButtonWithIcon(
            text = "Scan Foods",
            icon = ImageVector.vectorResource(R.drawable.ic_camera),
            onClick = onScanClicked,
            contentDescription = "Scan Foods",
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(16.dp))
        ButtonWithIcon(
            text = "Manual Input",
            icon = ImageVector.vectorResource(R.drawable.ic_create),
            onClick = onWriteManualClicked,
            contentDescription = "Manual Input",
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPersonalHealthInfo() {
    HidupSehatTheme {
        CardPersonalHealthInfo(
            caloriesIntakeActual = 200.0,
            waterDrunkActual = 3.4,
            sleepTimeActual = 4.0,
            caloriesBurnedActual = 400.0,
            onScanClicked = {},
            onWriteManualClicked = {},
            onCardClicked = {}
        )
    }
}