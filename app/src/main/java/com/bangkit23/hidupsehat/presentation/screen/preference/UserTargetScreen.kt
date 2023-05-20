package com.bangkit23.hidupsehat.presentation.screen.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.screen.preference.components.ItemChoice
import com.bangkit23.hidupsehat.presentation.screen.preference.components.PreferenceHeader
import com.bangkit23.hidupsehat.presentation.screen.preference.components.UserPreferenceSection
import com.bangkit23.hidupsehat.presentation.screen.preference.model.TargetChoice
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun UserTargetScreen(
    onNextClick: (choiceId: Int, targetWeight: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserTargetViewModel = hiltViewModel()
) {
    val weightTarget by viewModel.weightTarget
    val chosenTargetId by viewModel.chosenTargetId
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
                title = "Target Saya",
                label = "Masukkan target anda kedepan"
            )
            UserTargetBody(
                selectedId = chosenTargetId,
                weightTarget = weightTarget,
                onTargetChosen = viewModel::setChosenTargetId,
                onWeightTargetChange = viewModel::setWeightTarget,
                onFocusChanged = { focusState ->
                    if (focusState.isFocused) {
                        scope.launch {
                            delay(0.75.seconds)
                            scrollState.animateScrollTo(1000)
                        }
                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Button(
                onClick = {
                    if (chosenTargetId != -1 && weightTarget.isNotEmpty()) {
                        onNextClick(chosenTargetId, weightTarget)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text("Selanjutnya")
            }
        }
    }
}

@Composable
fun UserTargetBody(
    selectedId: Int,
    weightTarget: String,
    onTargetChosen: (id: Int) -> Unit,
    onWeightTargetChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        UserPreferenceSection(
            title = "Pilih salah satu",
            content = {
                TargetChoices(
                    selectedId = selectedId,
                    onSelected = onTargetChosen
                )
            },
            modifier = Modifier.padding(top = 32.dp)
        )
        UserPreferenceSection(
            title = "Berapa sasaran berat badanmu?",
            content = {
                OutlinedTextField(
                    value = weightTarget,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = onWeightTargetChange,
                    placeholder = {
                        Text("Misal: 20")
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                        .onFocusChanged(onFocusChanged)
                        .padding(bottom = 80.dp)
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun TargetChoices(
    selectedId: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val choices = listOf(
        TargetChoice(
            id = 0,
            title = "Menurunkan berat badan",
            icon = ImageVector.vectorResource(R.drawable.ic_foostep)
        ),
        TargetChoice(
            id = 1,
            title = "Menjaga berat badan",
            icon = ImageVector.vectorResource(R.drawable.ic_scales),
        ),
        TargetChoice(
            id = 2,
            title = "Menaikkan berat badan",
            icon = ImageVector.vectorResource(R.drawable.ic_beef)
        ),
    )
    Column(modifier = modifier) {
        choices.forEach { choice ->
            ItemChoice(
                selectedId = selectedId,
                choiceId = choice.id,
                choiceTitle = choice.title,
                choiceIcon = choice.icon,
                onSelected = onSelected
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserTargetScreenPreview() {
    HidupSehatTheme {
        Surface {
            UserTargetScreen(
                onNextClick = { _, _ -> },
            )
        }
    }
}