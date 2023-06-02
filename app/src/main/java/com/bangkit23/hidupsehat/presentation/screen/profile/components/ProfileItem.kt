package com.bangkit23.hidupsehat.presentation.screen.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileItem(
    modifier : Modifier = Modifier,
    title : String,
    icon : ImageVector,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .height(56.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon,contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(modifier = Modifier.weight(1/3f),text = title, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            Icon(imageVector = Icons.Default.ArrowRight , contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(title = "Ubah Password", icon = Icons.Default.AccountCircle)
}