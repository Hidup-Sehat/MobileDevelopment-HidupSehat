package com.bangkit23.hidupsehat.presentation.screen.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.presentation.ui.theme.md_theme_light_onPrimary

@Composable
fun LoginScreen() {
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
            label = "Email",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))
        PasswordTextField(
            label = "Password", onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.lupa_password), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            textAlign = TextAlign.End
        )

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 62.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Masuk")
        }

        Text(
            text = stringResource(R.string.atau), textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        OutlinedButtonWithIcon(
            text = "Masuk dengan Google",
            icon = painterResource(id = R.drawable.logos_google_icon),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.belum_memiliki_akun_daftar),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 62.dp), textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPrev() {
    HidupSehatTheme {
        LoginScreen()
    }
}