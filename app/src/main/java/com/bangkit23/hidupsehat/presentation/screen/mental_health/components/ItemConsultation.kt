package com.bangkit23.hidupsehat.presentation.screen.mental_health.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R

@Composable
fun ItemConsultation(
    image: String,
    drName: String,
    price: String,
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
                .padding(top = 14.dp),
            text = drName,
            style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.Medium
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = price,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.place_holder)
        )
    }
}