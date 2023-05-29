package com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextFieldLabel(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value, onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(16.dp),
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPrev() {
    var name by remember {
        mutableStateOf("")
    }
    CustomTextFieldLabel(label = "Nama Makanan", onValueChange = {})
}