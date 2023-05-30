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
import androidx.compose.runtime.remember
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
fun LoginScreen(
    onLoggedIn: () -> Unit,
    moveToUserPreference: (userData: UserData?) -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val email by viewModel.email
    val password by viewModel.password
    val loginState by viewModel.state.collectAsStateWithLifecycle()
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
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    val signInResult = googleSignInClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    LaunchedEffect(key1 = loginState.isSignInSuccessful) {
        if (loginState.isSignInSuccessful) {
            moveToUserPreference(loginState.userData)
//            else onLoggedIn()
            viewModel.resetState()
        }
    }

    LoginContent(
        email = email,
        password = password,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onRegisterClick = onRegisterClick,
        onLoggedIn = {
            scope.launch {
                val signInResult = googleSignInClient.signInWithEmail(email, password)
                viewModel.onSignInResult(signInResult)
            }
        },
        onSignInWithGoogleClick = {
            scope.launch {
                val signInIntentSender = googleSignInClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }
    )
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