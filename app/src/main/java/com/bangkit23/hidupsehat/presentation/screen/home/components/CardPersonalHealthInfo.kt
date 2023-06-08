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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.TextWithIcon
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun CardPersonalHealthInfo(
    caloriesIntakeActual: Int,
    waterDrunkActual: Int,
    sleepTimeActual: Int,
    caloriesBurnedActual: Int,
    caloriesIntakeExpected: Int,
    waterDrunkExpected: Int,
    sleepTimeExpected: Int,
    caloriesBurnedExpected: Int,
    onScanClicked: () -> Unit,
    onWriteManualClicked: () -> Unit,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onCardClicked() }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val (progress, bodyNeeds, foodButton, editButton) = createRefs()

            ProgressCaloriesIntake(
                caloriesIntakeActual = caloriesIntakeActual,
                caloriesIntakeExpected = caloriesIntakeExpected,
                modifier = Modifier.constrainAs(progress) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
            )
            Column(
                modifier = Modifier.constrainAs(bodyNeeds) {
                    start.linkTo(progress.end)
                    end.linkTo(editButton.start)
                    top.linkTo(parent.top, margin = 16.dp)
                },
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextWithIcon(
                    text = "$waterDrunkActual/$waterDrunkExpected L",
                    icon = ImageVector.vectorResource(R.drawable.ic_glass_water),
                    textStyle = MaterialTheme.typography.bodyMedium
                )
                TextWithIcon(
                    text = "$sleepTimeActual/$sleepTimeExpected jam",
                    icon = ImageVector.vectorResource(R.drawable.ic_sleep),
                    textStyle = MaterialTheme.typography.bodyMedium
                )
                TextWithIcon(
                    text = "$caloriesBurnedActual/$caloriesBurnedExpected kal",
                    icon = ImageVector.vectorResource(R.drawable.ic_fire),
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
            ButtonInputFoods(
                onScanClicked = onScanClicked,
                onWriteManualClicked = onWriteManualClicked,
                modifier = Modifier.constrainAs(foodButton) {
                    top.linkTo(progress.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Icon(
                imageVector = Icons.Rounded.Create,
                contentDescription = "Update User Info",
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(editButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }.clickable { onCardClicked() }
            )
        }
    }
}

@Composable
fun ProgressCaloriesIntake(
    caloriesIntakeActual: Int,
    caloriesIntakeExpected: Int,
    modifier: Modifier = Modifier,
) {
    val progress by remember { mutableStateOf((caloriesIntakeActual / caloriesIntakeExpected) * 100.0) }
    val percentage by remember { mutableStateOf(progress / 100.0) }
    ConstraintLayout(modifier = modifier) {
        val (progressBar, foodIcon, caloriesIntake, calorieText) = createRefs()

        CircularProgressBar(
            percentage = percentage.toFloat(),
            strokeWidth = 16.dp,
            modifier = Modifier.constrainAs(progressBar) {
                top.linkTo(parent.top)
            }
        )
        Text(
            text = "${caloriesIntakeActual}/${caloriesIntakeExpected}",
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Visible,
            modifier = Modifier.constrainAs(caloriesIntake) {
                top.linkTo(progressBar.top)
                bottom.linkTo(progressBar.bottom)
                start.linkTo(progressBar.start)
                end.linkTo(progressBar.end)
            }
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
            contentDescription = null,
            modifier = Modifier.constrainAs(foodIcon) {
                bottom.linkTo(caloriesIntake.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            tint = MaterialTheme.colorScheme.primary
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
            icon = Icons.Rounded.Add,
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
            caloriesIntakeActual = 200,
            waterDrunkActual = 3,
            sleepTimeActual = 4,
            caloriesBurnedActual = 400,
            caloriesBurnedExpected = 220,
            sleepTimeExpected = 7,
            waterDrunkExpected = 4,
            caloriesIntakeExpected = 2400,
            onScanClicked = {},
            onWriteManualClicked = {},
            onCardClicked = {},
        )
    }
}