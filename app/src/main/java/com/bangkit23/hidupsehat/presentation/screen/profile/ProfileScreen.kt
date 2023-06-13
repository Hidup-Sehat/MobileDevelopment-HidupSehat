package com.bangkit23.hidupsehat.presentation.screen.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.presentation.MainActivity
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.presentation.screen.profile.components.ProfileItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    moveToUpdateProfile: () -> Unit,
    moveToChangePassword: () -> Unit,
    moveToFaq: () -> Unit,
    moveToRating: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) {
            activity?.run {
                startActivity(Intent(activity, MainActivity::class.java))
                finish()
            }
        }
    }

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
                userData = state.userData,
                modifier = Modifier.padding(it),
                moveToUpdateProfile = moveToUpdateProfile,
                moveToChangePassword = moveToChangePassword,
                moveToFaq = moveToFaq,
                moveToRating = moveToRating,
                logOut = {
                    viewModel.onEvent(ProfileEvent.OnLogOut)
                }
            )
        }
    )
}

@Composable
fun ProfileContent(
    userData: UserData?,
    modifier : Modifier = Modifier,
    moveToUpdateProfile: () -> Unit,
    moveToChangePassword: () -> Unit,
    moveToFaq: () -> Unit,
    moveToRating: () -> Unit,
    logOut: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = userData?.profilePictureUrl,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp)
                .size(148.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
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
        ProfileItem(
            modifier = Modifier.clickable { logOut() },
            title = "Logout", icon = Icons.Default.Logout
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPrev() {
    ProfileScreen({},{},{},{},{})
}