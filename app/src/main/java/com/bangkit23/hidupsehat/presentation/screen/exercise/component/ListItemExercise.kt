package com.bangkit23.hidupsehat.presentation.screen.exercise.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise

@Composable
fun ListItemExercise(
    headerText: String,
    data: List<Exercise>,
    onItemClicked: (Exercise) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = headerText,
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data) {
                CardExercise(
                    title = it.title,
                    desc = it.description,
                    image = it.image,
                    onClicked = { onItemClicked(it) }
                )
            }
        }
    }
}

@Composable
fun CardExercise(
    title: String,
    desc: String,
    image: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .width(160.dp)
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
    ListItemExercise(
        headerText = "Aktivitas Terbaru",
        data = emptyList(),
        onItemClicked = {},
    )
}

@Preview
@Composable
fun CardExercisePrev() {
    CardExercise(
        title = "Pemula",
        desc = "6 Gerakan",
        image = "",
        onClicked = {}
    )
}