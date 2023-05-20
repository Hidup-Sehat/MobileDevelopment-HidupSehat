package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun TextWithSupport(
    headerText: String,
    supportText: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = headerText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = supportText,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun TextWithSupportPrev() {
    HidupSehatTheme {
        TextWithSupport(
            headerText = "Masuk Akun",
            supportText = "Halo! Senang Bertemu denganmu kembali"
        )
    }
}