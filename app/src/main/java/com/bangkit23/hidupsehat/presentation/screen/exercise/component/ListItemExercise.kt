package com.bangkit23.hidupsehat.presentation.screen.exercise.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Pose
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemExercise(headerText: String, data: List<Pose>) {
    var openBottomSheet by rememberSaveable() {
        mutableStateOf(false)
    }
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = headerText, style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
        )
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data) {
                CardExercise(
                    title = it.title,
                    desc = it.description,
                    image = it.image,
                    onClicked = {
                        openBottomSheet = !openBottomSheet
                    })
            }
        }
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false }, sheetState = bottomSheetState
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Pemula", style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(text = "6 Gerakan")
                }
                Button(onClick = {}, contentPadding = ButtonDefaults.ButtonWithIconContentPadding) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null,modifier = Modifier.size(ButtonDefaults.IconSize))
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "Mulai")
                }
            }
            LazyColumn{
                items(data){
                    DetailItemExercise(icon = it.image, title = it.title, description = it.description)
                }
            }
        }
    }

}

@Composable
fun CardExercise(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    image: String,
    onClicked: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .width(155.dp)
            .height(200.dp)
            .clickable { onClicked() }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 14.dp), text = title,
            style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.Medium
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp), text = desc, style = TextStyle(
                fontSize = 10.sp,
            )
        )
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image, contentDescription = null,
            placeholder = painterResource(id = R.drawable.place_holder)
        )
    }
}

@Preview
@Composable
fun ListItemExercisePreview() {
    val list = listOf<Pose>(
        Pose("Gerakan 1", "Detail", ""),
        Pose("Gerakan 2", "Detail", ""),
        Pose("Gerakan 3", "Detail", "")
    )
    ListItemExercise(headerText = "Aktivitas Terbaru", data = list)
}

@Preview
@Composable
fun CardExercisePrev() {
    CardExercise(title = "Pemula", desc = "6 Gerakan", image = "", onClicked = {})
}