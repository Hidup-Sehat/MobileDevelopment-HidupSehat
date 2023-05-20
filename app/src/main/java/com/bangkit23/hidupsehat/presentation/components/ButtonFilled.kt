package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_onPrimary
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_primary

@Composable
fun ButtonFilled(
    text: String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = md_theme_light_onPrimary,
            containerColor = md_theme_light_primary
        ),
        contentPadding = PaddingValues(10.dp),
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun ButtonFilledPrev() {
    HidupSehatTheme {
        ButtonFilled(text = "Login", onClick = {})
    }
}