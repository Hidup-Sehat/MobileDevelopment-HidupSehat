package com.bangkit23.hidupsehat.presentation.screen.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun TextOr(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_gradient_line),
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.atau),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.width(8.dp))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_gradient_line),
            contentDescription = null,
            modifier = Modifier.rotate(180f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextOrPreview() {
    HidupSehatTheme {
        TextOr()
    }
}