package com.bangkit23.hidupsehat.presentation.screen.update_user_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader
import com.bangkit23.hidupsehat.presentation.screen.update_user_info.components.TextFieldWithIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUserInfoScreen(
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    viewModel: UpdateUserInfoViewModel = hiltViewModel(),
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    LaunchedEffect(key1 = isExpanded) {
        if (isExpanded) {
            //LaterAddFromParameter
            viewModel.onEvent(UpdateUserInfoEvent.SaveInitialInfo("8000", "200"))
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (isExpanded) {
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
                    calorieNeeds = state.calorieNeeds,
                    calorieBurned = state.calorieBurned,
                    onCalorieNeedsChanged = {
                        viewModel.onEvent(UpdateUserInfoEvent.OnCalorieNeedsChanged(it))
                    },
                    onCalorieBurnedChanged = {
                        viewModel.onEvent(UpdateUserInfoEvent.OnCalorieBurnedChanged(it))
                    },
                    onSaveClick = {

                    }
                )
            },
            modifier = Modifier
        )
    }
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
    calorieNeeds: String,
    onCalorieNeedsChanged: (String) -> Unit,
    calorieBurned: String,
    onCalorieBurnedChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxHeight()
    ) {
        Spacer(Modifier.height(16.dp))
        TextFieldWithIcon(
            value = calorieNeeds,
            onValueChanged = onCalorieNeedsChanged,
            label = "Kilo kalori (kkal)",
            startIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                    contentDescription = "Kalori",
                    tint = Color.Unspecified
                )
            },
            title = {
                Text("Kebutuhan kalori")
            }
        )
        Spacer(Modifier.height(24.dp))
        TextFieldWithIcon(
            value = calorieBurned,
            onValueChanged = onCalorieBurnedChanged,
            label = "Kalori (kal)",
            startIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_fire),
                    contentDescription = "Kalori",
                    tint = Color.Unspecified
                )
            },
            title = {
                Text("Pembakaran kalori")
            }
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
