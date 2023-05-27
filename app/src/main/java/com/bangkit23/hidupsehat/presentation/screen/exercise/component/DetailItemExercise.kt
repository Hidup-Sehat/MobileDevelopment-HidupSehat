package com.bangkit23.hidupsehat.presentation.screen.exercise.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R

@Composable
fun DetailItemExercise(
    icon: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            model = icon, contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_male),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(modifier = Modifier.fillMaxWidth(),text = title, fontSize = 16.sp)
            Text(modifier = Modifier.fillMaxWidth(),text = description, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailItemExercisePrev() {
    DetailItemExercise(icon = "", title = "Gerakan 1", description = "Lorem Ipsum")
}