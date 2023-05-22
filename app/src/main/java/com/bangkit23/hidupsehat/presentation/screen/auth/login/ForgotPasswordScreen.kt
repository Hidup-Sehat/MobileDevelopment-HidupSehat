package com.bangkit23.hidupsehat.presentation.screen.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.ButtonFilled
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun ForgotPasswordScreen() {
    var email by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextWithSupport(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 62.dp),
            headerText = "Lupa Password",
            supportText = "Gunakan email yang telah terdaftar"
        )
        CustomTextField(
            modifier = Modifier.padding(top = 49.dp, bottom = 24.dp),
            label = "Email anda",
            value = email,
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        ButtonFilled(modifier = Modifier.padding(horizontal = 16.dp), text = "Reset Password") {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPrev() {
    HidupSehatTheme {
        ForgotPasswordScreen()
    }
}