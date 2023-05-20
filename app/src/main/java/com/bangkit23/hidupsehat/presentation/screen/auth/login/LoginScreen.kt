package com.bangkit23.hidupsehat.presentation.screen.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonFilled
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.screen.auth.components.SpannableText
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_onPrimary
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_primary

@Composable
fun LoginScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(top = 117.dp))
        TextWithSupport(
            headerText = stringResource(R.string.masuk_akun),
            supportText = stringResource(R.string.support_masuk),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(49.dp))
        CustomTextField(
            label = stringResource(R.string.email),
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = email
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))
        PasswordTextField(
            label = stringResource(R.string.password), onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            value = password
        )

        Text(
            text = stringResource(R.string.lupa_password), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 32.dp),
            textAlign = TextAlign.End,
            color = md_theme_light_primary
        )

        ButtonFilled(text = stringResource(R.string.login), onClick = {}, modifier = Modifier.padding(horizontal = 16.dp))

        Text(
            text = stringResource(R.string.atau), textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        OutlinedButtonWithIcon(
            text = stringResource(R.string.masuk_dengan_google),
            icon = painterResource(id = R.drawable.logos_google_icon),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        SpannableText(text1 = stringResource(R.string.belum_punya_akun), text2 = stringResource(R.string.daftar))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPrev() {
    HidupSehatTheme {
        LoginScreen()
    }
}