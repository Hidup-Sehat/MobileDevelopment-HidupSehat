package com.bangkit23.hidupsehat.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.profile.components.ProfileItem

@Composable
fun ProfileScreen() {
    ProfileContent()
}

@Composable
fun ProfileContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.size(148.dp).align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_male), contentDescription = null)
        Spacer(modifier = Modifier.height(28.dp))
        ProfileItem(title = "Ubah Profile", icon = Icons.Default.ManageAccounts)
        ProfileItem(title = "Ubah Password", icon = Icons.Default.Password)
        ProfileItem(title = "Pertanyaan Sering Diajukan", icon = Icons.Default.Help)
        ProfileItem(title = "Beri Rating", icon = Icons.Default.StarRate)

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPrev() {
    ProfileScreen()
}