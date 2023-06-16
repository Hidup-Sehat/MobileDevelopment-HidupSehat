package com.bangkit23.hidupsehat.presentation.screen.auth.login

import android.app.Activity.RESULT_OK
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.CustomTextField
import com.bangkit23.hidupsehat.presentation.components.LoadingDialog
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.PasswordTextField
import com.bangkit23.hidupsehat.presentation.components.TextWithSupport
import com.bangkit23.hidupsehat.presentation.screen.auth.common.signInIntentSender
import com.bangkit23.hidupsehat.presentation.screen.auth.components.SpannableText
import com.bangkit23.hidupsehat.presentation.screen.auth.components.TextOr
import com.bangkit23.hidupsehat.presentation.screen.auth.model.UserData
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    moveToUserPreference: (userData: UserData?) -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    viewModel.onEvent(LoginEvent.SignInGoogleWithIntent(result.data ?: return@launch))
                }
            }
        }
    )

    LaunchedEffect(key1 = state.signInSuccessful) {
        if (state.signInSuccessful) {
            if (state.signInResult?.data?.isNewUser == true) moveToUserPreference(state.signInResult?.data)
            else navigateToHome()
            viewModel.onEvent(LoginEvent.ResetState)
        }
    }

    LoginContent(
        email = state.email,
        password = state.password,
        onEmailChanged = {
            viewModel.onEvent(LoginEvent.OnEmailChanged(it))
        },
        onPasswordChanged = {
            viewModel.onEvent(LoginEvent.OnPasswordChanged(it))
        },
        onRegisterClick = onRegisterClick,
        onLoggedIn = {
            viewModel.onEvent(LoginEvent.LoginWithEmailPassword(state.email, state.password))
        },
        onSignInWithGoogleClick = {
            viewModel.onEvent(LoginEvent.SetLoadingState(isLoading = true))
            scope.launch {
                val signInIntentSender = signInIntentSender(context)
                viewModel.onEvent(LoginEvent.SetLoadingState(isLoading = false))
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }
    )

    if (state.loading) {
        LoadingDialog()
    }
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoggedIn: () -> Unit,
    onRegisterClick: () -> Unit,
    onSignInWithGoogleClick: () -> Unit,
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
                headerText = stringResource(R.string.masuk_akun),
                supportText = stringResource(R.string.support_masuk),
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 64.dp)
            )
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
            Text(
                text = stringResource(R.string.lupa_password), modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 32.dp),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Button(
                onClick = onLoggedIn,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(stringResource(R.string.login))
            }
            TextOr(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedButtonWithIcon(
                text = stringResource(R.string.masuk_dengan_google),
                icon = painterResource(R.drawable.logos_google_icon),
                onClick = onSignInWithGoogleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
            )
            SpannableText(
                text1 = stringResource(R.string.belum_punya_akun),
                text2 = stringResource(R.string.daftar),
                modifier = Modifier
                    .padding(vertical = 64.dp)
                    .clickable { onRegisterClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    HidupSehatTheme {
        LoginContent(
            email = "",
            password = "",
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoggedIn = {},
            onRegisterClick = {},
            onSignInWithGoogleClick = {},
        )
    }
}