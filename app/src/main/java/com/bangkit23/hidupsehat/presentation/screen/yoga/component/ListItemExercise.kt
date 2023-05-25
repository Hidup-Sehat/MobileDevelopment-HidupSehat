package com.bangkit23.hidupsehat.presentation.screen.yoga.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
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
import com.bangkit23.hidupsehat.presentation.screen.yoga.model.Pose

@Composable
fun ListItemExercise(headerText: String, data: List<Pose>) {

    Column {
        Text(
            text = headerText, style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Medium
            )
        )
        LazyRow(contentPadding = PaddingValues(8.dp)) {
            items(data) {
                CardExercise(title = it.title, desc = it.description, image = it.image)
            }
        }
    }

}

@Composable
fun CardExercise(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    image: String
) {
    ElevatedCard(
        modifier = Modifier
            .width(155.dp)
            .height(200.dp)
            .padding(horizontal = 4.dp)
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
    CardExercise(title = "Pemula", desc = "6 Gerakan", image = "")
}