package com.bangkit23.hidupsehat.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    value: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        value = value, onValueChange = onValueChange,
        placeholder = {
            Text(text = label)
        },
        shape = RoundedCornerShape(15.dp),
        trailingIcon = trailingIcon
    )

}

@Composable
fun PasswordTextField(
    label: String,
    value: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    CustomTextField(
        label = label, onValueChange =
        onValueChange,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Visibility, contentDescription = null
            )
        }
    )
}

@Preview
@Composable
fun CustomTextFieldPrev() {
    HidupSehatTheme {
        CustomTextField(label = "Email", onValueChange = {})
    }
}

@Preview
@Composable
fun PasswordTextFieldPrev() {
    HidupSehatTheme {
        PasswordTextField(label = "Password", onValueChange = {})
    }
}