package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = supportText,
            style = MaterialTheme.typography.bodyLarge
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