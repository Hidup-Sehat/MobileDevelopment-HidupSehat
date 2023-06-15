package com.bangkit23.hidupsehat.presentation.screen.update_user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.screen.update_user_info.components.UserNeedsCounter
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUserInfoScreen(
    initialSleepNeeds: Int,
    initialWaterNeeds: Int,
    onDismissRequest: () -> Unit,
    viewModel: UpdateUserInfoViewModel = hiltViewModel(),
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(
            UpdateUserInfoEvent.SaveInitialInfo(
                sleepNeeds = initialSleepNeeds,
                waterNeeds = initialWaterNeeds,
            )
        )
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    SheetWithHeader(
        onDismiss = {
            onDismissRequest()
        },
        sheetState = sheetState,
        contentHeader = {
            HeaderSheetUserInfo()
        },
        contentBody = {
            BodySheetUserInfo(
                waterNeeds = state.waterNeeds,
                sleepNeeds = state.sleepNeeds,
                onSleepNeedsChanged = {
                    viewModel.onEvent(UpdateUserInfoEvent.OnSleepNeedsChanged(it))
                },
                onWaterNeedsChanged = {
                    viewModel.onEvent(UpdateUserInfoEvent.OnWaterNeedsChanged(it))
                },
                onSaveClick = {

                }
            )
        },
        modifier = Modifier
    )
}

@Composable
fun HeaderSheetUserInfo(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Update",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Ubah jumlah kebutuhan tubuhmu hari ini.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun BodySheetUserInfo(
    sleepNeeds: Int,
    onSleepNeedsChanged: (Int) -> Unit,
    waterNeeds: Int,
    onWaterNeedsChanged: (Int) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxHeight()
    ) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_sleep),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(8.dp))
            Text("Waktu tidur")
        }
        Spacer(Modifier.height(16.dp))
        UserNeedsCounter(
            count = sleepNeeds,
            image = ImageVector.vectorResource(R.drawable.ic_sleep),
            needName = "jam",
            onDecreaseClick = onSleepNeedsChanged,
            onIncreaseClick = onSleepNeedsChanged,
        )
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_glass_water),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(8.dp))
            Text("Air minum")
        }
        Spacer(Modifier.height(16.dp))
        UserNeedsCounter(
            count = waterNeeds,
            onDecreaseClick = onWaterNeedsChanged,
            onIncreaseClick = onWaterNeedsChanged,
            image = ImageVector.vectorResource(R.drawable.ic_glass_water),
            needName = "gelas"
        )
        Spacer(Modifier.height(32.dp))
        ButtonWithIcon(
            text = "Simpan",
            icon = Icons.Rounded.Check,
            onClick = onSaveClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateUserInfoScreenPreview() {
    HidupSehatTheme {
        BodySheetUserInfo(
            onSaveClick = {},
            sleepNeeds = 6,
            onSleepNeedsChanged = {},
            waterNeeds = 3,
            onWaterNeedsChanged = {},
        )
    }
}
