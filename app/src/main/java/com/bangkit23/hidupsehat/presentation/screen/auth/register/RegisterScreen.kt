package com.bangkit23.hidupsehat.presentation.screen.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonFilled
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_onPrimary
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.bangkit23.hidupsehat.presentation.screen.auth.components.SpannableText

@Composable
fun RegisterScreen() {
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextWithSupport(
            headerText = stringResource(R.string.daftar_akun), supportText = stringResource(R.string.halo_senang_kamu_hadir_di_sini),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
        )
        CustomTextField(
            label = stringResource(R.string.masukkan_nama),
            onValueChange = {
                name = it
            },
            modifier = Modifier.padding(top = 42.dp),
            value = name
        )
        CustomTextField(
            label = stringResource(id = R.string.email),
            onValueChange = {
                email = it
            },
            modifier = Modifier.padding(top = 24.dp),
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        PasswordTextField(
            label = stringResource(id = R.string.password), onValueChange = {
                password = it
            },
            modifier = Modifier.padding(top = 24.dp),
            value = password
        )
        PasswordTextField(
            label = stringResource(R.string.konfirmasi_password), onValueChange = {
                confirmPassword = it
            },
            modifier = Modifier.padding(top = 24.dp),
            value = confirmPassword
        )

        ButtonFilled(
            text = stringResource(id = R.string.daftar), onClick = {},
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp)
        )

        Text(
            text = stringResource(id = R.string.atau),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        OutlinedButtonWithIcon(
            text = stringResource(R.string.daftar_dengan_google),
            icon = painterResource(id = R.drawable.logos_google_icon),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        SpannableText(text1 = stringResource(R.string.sudah_memiliki_akun), text2 = stringResource(id = R.string.masuk))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPrev() {
    HidupSehatTheme {
        RegisterScreen()
    }
}