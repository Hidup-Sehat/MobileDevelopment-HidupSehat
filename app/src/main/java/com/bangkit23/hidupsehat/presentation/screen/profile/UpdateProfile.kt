package com.bangkit23.hidupsehat.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.presentation.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfile(
    onNavigateUp : () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                    Text(text = "Ubah Profile")
            },
            navigationIcon = {
                IconButton(onClick = {onNavigateUp()}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
    }, content = {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            CustomTextField(
                modifier = Modifier.padding(top = 44.dp),
                label ="Nama Lengkap" , onValueChange = {})
            CustomTextField(
                modifier = Modifier.padding(top = 32.dp),
                label ="Email" , onValueChange = {})
            CustomTextField(
                modifier = Modifier.padding(top = 32.dp),
                label ="No Handphone" , onValueChange = {})
            CustomTextField(
                modifier = Modifier.padding(top = 32.dp),
                label ="Tanggal Lahir" , onValueChange = {})

            Box(modifier = Modifier
                .weight(1f)) {
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 44.dp),
                onClick = {}) {
                Text(text = "Perbarui")
            }
        }
    })

}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun UpdateProfilePrev() {
    UpdateProfile({})
}