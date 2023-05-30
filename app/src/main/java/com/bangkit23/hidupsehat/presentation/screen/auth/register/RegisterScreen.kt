package com.bangkit23.hidupsehat.presentation.screen.auth.register

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.screen.auth.common.GoogleAuthUiClient
import com.bangkit23.hidupsehat.presentation.screen.auth.components.SpannableText
import com.bangkit23.hidupsehat.presentation.screen.auth.components.TextOr
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onRegistered: () -> Unit,
    moveToUserPreference: (userData: UserData?) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val name by viewModel.name
    val email by viewModel.email
    val password by viewModel.password
    val confirmationPassword by viewModel.confirmationPassword
    val registerState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val googleSignInClient = remember {
        GoogleAuthUiClient(
            context = context.applicationContext,
            onTapClient = Identity.getSignInClient(context.applicationContext)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    val signInResult = googleSignInClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    LaunchedEffect(key1 = registerState.isSignInSuccessful) {
        if (registerState.isSignInSuccessful) {
            if (registerState.userData?.isNewUser == true) moveToUserPreference(registerState.userData)
            else onRegistered()
            viewModel.resetState()
        }
    }

    RegisterContent(
        name = name,
        email = email,
        password = password,
        confirmationPassword = confirmationPassword,
        onNameChanged = viewModel::setName,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onConfirmationPasswordChanged = viewModel::setConfirmationPassword,
        onRegistered = {
            scope.launch {
                val signInResult = googleSignInClient.registerWithEmail(email, confirmationPassword)
                viewModel.onSignInResult(signInResult)
            }
        },
        signInWithGoogleClick = {
            scope.launch {
                val intentSender = googleSignInClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        intentSender ?: return@launch
                    ).build()
                )
            }
        },
        onLoginClick = {},
    )
}

@Composable
fun RegisterContent(
    name: String,
    email: String,
    password: String,
    confirmationPassword: String,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmationPasswordChanged: (String) -> Unit,
    onRegistered: () -> Unit,
    onLoginClick: () -> Unit,
    signInWithGoogleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            TextWithSupport(
                headerText = stringResource(R.string.daftar_akun),
                supportText = stringResource(R.string.halo_senang_kamu_hadir_di_sini),
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 40.dp)
            )
            CustomTextField(
                label = stringResource(R.string.masukkan_nama),
                onValueChange = onNameChanged,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = name
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            CustomTextField(
                label = stringResource(R.string.email),
                onValueChange = onEmailChanged,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                value = email
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            PasswordTextField(
                label = stringResource(R.string.password),
                onValueChange = onPasswordChanged,
                modifier = Modifier.fillMaxWidth(),
                value = password
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            PasswordTextField(
                label = stringResource(R.string.konfirmasi_password),
                onValueChange = onConfirmationPasswordChanged,
                modifier = Modifier.fillMaxWidth(),
                value = confirmationPassword
            )
            Spacer(Modifier.height(40.dp))
            Button(
                onClick = onRegistered,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.daftar))
            }
            TextOr(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedButtonWithIcon(
                text = stringResource(R.string.masuk_dengan_google),
                icon = painterResource(R.drawable.logos_google_icon),
                onClick = signInWithGoogleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
            )
            SpannableText(
                text1 = stringResource(R.string.sudah_memiliki_akun),
                text2 = stringResource(R.string.login),
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .clickable { onLoginClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    HidupSehatTheme {
        RegisterContent(
            name = "",
            email = "",
            password = "",
            confirmationPassword = "",
            onNameChanged = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onConfirmationPasswordChanged = {},
            onRegistered = {},
            onLoginClick = {},
            signInWithGoogleClick = {},
        )
    }
}