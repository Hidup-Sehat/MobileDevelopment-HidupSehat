package com.bangkit23.hidupsehat.presentation.screen.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

@Composable
fun SpannableText(
    modifier: Modifier = Modifier,
    text1: String,
    text2: String
) {
    val annotatedString = buildAnnotatedString {
        append(text1)
        withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)){
            append(text2)
        }
    }
    Text(
        text = annotatedString,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}