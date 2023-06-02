package com.bangkit23.hidupsehat.presentation.screen.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.profile.components.ProfileItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    moveToUpdateProfile: () -> Unit,
    moveToChangePassword: () -> Unit,
    moveToFaq: () -> Unit,
    moveToRating: () -> Unit,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Akun Saya")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            ProfileContent(
                modifier = Modifier.padding(it),
                moveToUpdateProfile = moveToUpdateProfile,
                moveToChangePassword = moveToChangePassword,
                moveToFaq = moveToFaq,
                moveToRating = moveToRating
            )
        }
    )

}

@Composable
fun ProfileContent(
    modifier : Modifier = Modifier,
    moveToUpdateProfile: () -> Unit,
    moveToChangePassword: () -> Unit,
    moveToFaq: () -> Unit,
    moveToRating: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(148.dp)
                .align(Alignment.CenterHorizontally)
                ,
            painter = painterResource(id = R.drawable.ic_male), contentDescription = null
        )
        Spacer(modifier = Modifier.height(28.dp))
        ProfileItem(
            modifier = Modifier.clickable {
                moveToUpdateProfile()
            },
            title = "Ubah Profile", icon = Icons.Default.ManageAccounts
        )
        ProfileItem(
            modifier = Modifier.clickable {
                moveToChangePassword()
            },
            title = "Ubah Password", icon = Icons.Default.Password
        )
        ProfileItem(
            modifier = Modifier.clickable {
                moveToFaq()
            },
            title = "Pertanyaan Sering Diajukan", icon = Icons.Default.Help
        )
        ProfileItem(
            modifier = Modifier.clickable { moveToRating() },
            title = "Beri Rating", icon = Icons.Default.StarRate
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPrev() {
    ProfileScreen({},{},{},{},{})
}