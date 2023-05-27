package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.screen.exercise.component.ListItemExercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Pose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YogaScreen() {
    var openBottomSheet by rememberSaveable() {
        mutableStateOf(false)
    }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val list = listOf<Pose>(
        Pose("Gerakan 1", "Detail", ""),
        Pose("Gerakan 2", "Detail", ""),
        Pose("Gerakan 3", "Detail", "")
    )
    Column {
        ListItemExercise(
            headerText = "Aktifitas terakhirmu",
            data = list,
        )
    }

}

@Preview
@Composable
fun YogaScreenPrev() {
    YogaScreen()
}