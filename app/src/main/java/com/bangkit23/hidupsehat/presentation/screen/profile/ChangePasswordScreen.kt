package com.bangkit23.hidupsehat.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    onNavigateUp : () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Ubah Password")
        }, navigationIcon = {
            IconButton(onClick = {
                onNavigateUp.invoke()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TextChangePassword(
                modifier = Modifier.padding(top = 24.dp),
                text = "Password Lama")
            PasswordTextField(
                modifier = Modifier.padding(top = 8.dp),
                label = "********", onValueChange = {})

            TextChangePassword(
                modifier = Modifier.padding(top = 16.dp),
                text = "Password Lama")
            PasswordTextField(
                modifier = Modifier.padding(top = 8.dp),
                label = "********", onValueChange = {})

            TextChangePassword(
                modifier = Modifier.padding(top = 16.dp),
                text = "Password Lama")
            PasswordTextField(
                modifier = Modifier.padding(top = 8.dp),
                label = "********", onValueChange = {})

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                contentPadding = ButtonDefaults.TextButtonContentPadding,
                onClick = {}) {
                Text(text = "Ubah Password")
            }
        }
    }

}

@Composable
fun TextChangePassword(
    modifier : Modifier = Modifier,
    text: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun ChangePasswordScreenPrev() {
    ChangePasswordScreen {}
}