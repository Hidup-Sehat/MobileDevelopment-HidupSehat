package com.bangkit23.hidupsehat.presentation.screen.food_history.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.R

@Composable
fun WidgetHistoryItem(
    modifier: Modifier = Modifier,
    data: String,
    label: String,
    icon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(38.dp),
            painter = icon, contentDescription = null)
        Text(text = data, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontSize = 10.sp))
        Text(text = label, style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light, fontSize = 8.sp))
    }
}

@Preview
@Composable
fun WidgetHistoryItemPrev() {
    WidgetHistoryItem(data = "2400", label = "Asupan min.", icon = painterResource(id = R.drawable.ic_asupan_min))
}