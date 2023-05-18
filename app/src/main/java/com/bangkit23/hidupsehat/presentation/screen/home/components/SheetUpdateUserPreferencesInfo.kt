package com.bangkit23.hidupsehat.presentation.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.SheetWithHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetUpdateUserPreferencesInfo(
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    SheetWithHeader(
        onDismiss = onDismiss,
        sheetState = sheetState,
        contentHeader = {
            HeaderSheetUserInfo(
                onSaveClick = onSaveClick
            )
        },
        contentBody = {
            BodySheetUserInfo()
        },
        modifier = modifier
            .fillMaxHeight()
    )
}

@Composable
fun HeaderSheetUserInfo(
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Update",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Update preferensi anda.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        ButtonWithIcon(
            onClick = onSaveClick,
            text = "Simpan",
            icon = Icons.Rounded.Check,
        )
    }
}

@Composable
fun BodySheetUserInfo(
    modifier: Modifier = Modifier,
) {

}