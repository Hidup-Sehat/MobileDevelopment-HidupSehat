package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_primary

@Composable
fun OutlinedButtonWithIcon(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    OutlinedButton(
        onClick = onClick,
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text)
    }
}

@Composable
fun OutlinedButtonWithIcon(
    text: String,
    icon: Painter,
    textColor: Color = md_theme_light_primary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    OutlinedButton(
        onClick = onClick,
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = modifier
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text, color = textColor)
    }
}

@Preview
@Composable
fun OutlineButtonWithIconPrev() {
    HidupSehatTheme {
        OutlinedButtonWithIcon(
            text = "Google",
            icon = painterResource(id = R.drawable.logos_google_icon),
            onClick = { })
    }
}