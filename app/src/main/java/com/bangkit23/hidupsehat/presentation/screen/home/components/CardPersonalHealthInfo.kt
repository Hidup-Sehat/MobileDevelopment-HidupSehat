package com.bangkit23.hidupsehat.presentation.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
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
        Button(
            onClick = onScanClicked,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_camera),
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Scan Foods")
        }
        Spacer(Modifier.width(16.dp))
        Button(
            onClick = onWriteManualClicked,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_create),
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Input Manual")
        }
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