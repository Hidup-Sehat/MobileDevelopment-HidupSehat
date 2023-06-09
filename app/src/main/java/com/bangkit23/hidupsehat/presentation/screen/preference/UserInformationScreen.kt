package com.bangkit23.hidupsehat.presentation.screen.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.LoadingDialog
import com.bangkit23.hidupsehat.presentation.model.User
import com.bangkit23.hidupsehat.presentation.screen.preference.components.ItemGender
import com.bangkit23.hidupsehat.presentation.screen.preference.components.PreferenceHeader
import com.bangkit23.hidupsehat.presentation.screen.preference.components.UserPreferenceSection
import com.bangkit23.hidupsehat.presentation.screen.preference.model.Gender
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun UserInformationScreen(
    user: User,
    choiceId: Int,
    weightTarget: String,
    navigateToHome: () -> Unit,
    viewModel: UserInformationViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.success) {
        if (state.success) {
            navigateToHome()
        }
    }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    UserInformationContent(
        selectedGenderId = state.selectedGenderId,
        age = state.age,
        height = state.height,
        currentWeight = state.currentWeight,
        onAgeChange = {
            viewModel.onEvent(UserInformationEvent.OnAgeChanged(it))
        },
        onGenderChosen = {
            viewModel.onEvent(UserInformationEvent.OnGenderIdChanged(it))
        },
        onHeightChange = {
            viewModel.onEvent(UserInformationEvent.OnHeightChanged(it))
        },
        onCurrentWeightChange = {
            viewModel.onEvent(UserInformationEvent.OnCurrentWeightChanged(it))
        },
        onFinishedClick = {
            viewModel.onEvent(
                UserInformationEvent.CreateUserDetailPreferences(
                    username = user.name.toString(),
                    name = user.name.toString(),
                    age = state.age.toIntOrNull() ?: 0,
                    gender = if (state.selectedGenderId == 0) "Male" else "Female",
                    height = state.height.toIntOrNull() ?: 0,
                    weight = state.currentWeight.toIntOrNull() ?: 0,
                    target = when (choiceId) {
                        0 -> "Menurunkan berat badan"
                        1 -> "Menjaga berat badan"
                        2 -> "Menaikkan berat badan"
                        else -> "Unknown Target"
                    },
                    weightTarget = weightTarget.toIntOrNull() ?: 0,
                    contactNumber = "",
                    dateOfBirth = "",
                )
            )
        },
    )

    if (state.loading) {
        LoadingDialog()
    }
}

@Composable
fun UserInformationContent(
    selectedGenderId: Int,
    age: String,
    height: String,
    currentWeight: String,
    onGenderChosen: (genderId: Int) -> Unit,
    onAgeChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onCurrentWeightChange: (String) -> Unit,
    onFinishedClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter)
                .verticalScroll(scrollState)
        ) {
            PreferenceHeader(
                title = "Informasi Saya",
                label = "Masukkan informasi detail anda"
            )
            UserInformationBody(
                selectedGenderId = selectedGenderId,
                age = age,
                height = height,
                currentWeight = currentWeight,
                onGenderChosen = onGenderChosen,
                onAgeChange = onAgeChange,
                onHeightChange = onHeightChange,
                onCurrentWeightChange = onCurrentWeightChange,
                onFocusChanged = { focusState ->
                    if (focusState.isFocused) {
                        scope.launch {
                            delay(0.75.seconds)
                            scrollState.animateScrollTo(1000)
                        }
                    }
                },
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ButtonWithIcon(
                onClick = onFinishedClick,
                text = "Selesai",
                icon = Icons.Rounded.Check,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(48.dp),
            )
        }
    }
}

@Composable
fun UserInformationBody(
    selectedGenderId: Int,
    age: String,
    height: String,
    currentWeight: String,
    onGenderChosen: (genderId: Int) -> Unit,
    onAgeChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onCurrentWeightChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        UserPreferenceSection(
            title = "Jenis Kelamin",
            content = {
                GenderChoices(
                    selectedGenderId = selectedGenderId,
                    onGenderChosen = onGenderChosen
                )
            },
            modifier = Modifier.padding(top = 32.dp)
        )
        UserPreferenceSection(
            title = "Usia (tahun)",
            content = {
                OutlinedTextField(
                    value = age,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = onAgeChange,
                    placeholder = {
                        Text("Misal: 22")
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                        .onFocusChanged(onFocusChanged)
                )
            },
            modifier = Modifier.padding(top = 24.dp)
        )
        UserPreferenceSection(
            title = "Tinggi badan (cm)",
            content = {
                OutlinedTextField(
                    value = height,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = onHeightChange,
                    placeholder = {
                        Text("Misal: 160")
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                        .onFocusChanged(onFocusChanged)
                )
            },
            modifier = Modifier.padding(top = 24.dp)
        )
        UserPreferenceSection(
            title = "Berat badan sekarang (kg)",
            content = {
                OutlinedTextField(
                    value = currentWeight,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = onCurrentWeightChange,
                    placeholder = {
                        Text("Misal: 64")
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                        .onFocusChanged(onFocusChanged)
                        .padding(bottom = 80.dp)
                )
            },
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
fun GenderChoices(
    selectedGenderId: Int,
    onGenderChosen: (genderId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val genders = listOf(
        Gender(
            id = 0,
            title = "Laki-laki",
            avatar = ImageVector.vectorResource(R.drawable.ic_male)
        ),
        Gender(
            id = 1,
            title = "Perempuan",
            avatar = ImageVector.vectorResource(R.drawable.ic_girl)
        )
    )
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        genders.forEach { gender ->
            ItemGender(
                genderId = gender.id,
                genderAvatar = gender.avatar,
                genderTitle = gender.title,
                chosenGenderId = selectedGenderId,
                onGenderChosen = onGenderChosen
            )
        }
    }
}

@Preview
@Composable
fun UserInformationContentPreview() {
    HidupSehatTheme {
        Surface {
            UserInformationContent(
                selectedGenderId = 0,
                age = "10",
                height = "",
                currentWeight = "",
                onAgeChange = {},
                onGenderChosen = {},
                onHeightChange = {},
                onCurrentWeightChange = {},
                onFinishedClick = {},
            )
        }
    }
}