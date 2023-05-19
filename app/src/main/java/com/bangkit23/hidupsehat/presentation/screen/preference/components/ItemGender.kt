package com.bangkit23.hidupsehat.presentation.screen.preference.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun ItemGender(
    genderId: Int,
    genderAvatar: ImageVector,
    genderTitle: String,
    chosenGenderId: Int,
    onGenderChosen: (genderId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(
            contentColor = if (chosenGenderId == genderId) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(
            width = if (chosenGenderId == genderId) 2.dp else CardDefaults.outlinedCardBorder().width,
            color = if (chosenGenderId == genderId) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        ),
        modifier = modifier
            .clickable { onGenderChosen(genderId) }
            .wrapContentSize()
            .width(96.dp)
    ) {
        Icon(
            imageVector = genderAvatar,
            contentDescription = genderTitle,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = genderTitle,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemGenderPreview() {
    HidupSehatTheme {
        ItemGender(
            genderId = 0,
            genderAvatar = ImageVector.vectorResource(R.drawable.ic_girl),
            genderTitle = "Girl",
            chosenGenderId = 0,
            onGenderChosen = {}
        )
    }
}
