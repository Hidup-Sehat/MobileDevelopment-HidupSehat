package com.bangkit23.hidupsehat.presentation.screen.update_user_info.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun TextFieldWithIcon(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    startIcon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            startIcon()
            Spacer(Modifier.width(8.dp))
            title()
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(label) },
            onValueChange = onValueChanged,
            placeholder = {
                Text("0")
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldWithIconPreview() {
    HidupSehatTheme {
        TextFieldWithIcon(
            value = "5490",
            onValueChanged = {},
            label = "Kilo kalori (kkal)",
            startIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            title = {
                Text("Kebutuhan kalori")
            },
        )
    }
}