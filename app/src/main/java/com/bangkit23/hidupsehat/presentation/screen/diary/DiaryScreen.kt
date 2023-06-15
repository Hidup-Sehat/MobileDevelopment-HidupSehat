package com.bangkit23.hidupsehat.presentation.screen.diary

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.presentation.screen.diary.component.Chip
import com.bangkit23.hidupsehat.presentation.screen.point_popup.PointPopupDialog
import com.bangkit23.hidupsehat.util.ListConverter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    onNavigateUp: () -> Unit,
    onPopBackStak: () -> Unit,
    viewModel: DiaryViewModel = hiltViewModel(),
) {
    val listPositive = listOf(
        "Antusias",
        "Bangga",
        "Takjub",
        "Rileks",
        "Semangat",
        "Gembira",
        "Senang",
        "Puas",
        "Tenang",
        "Lega",
        "Penuh Cinta"
    )

    val listNegative = listOf(
        "Kecewa",
        "Marah",
        "Stress",
        "Kesal",
        "Takut",
        "Cemas",
        "Gelisah",
        "Bingung",
        "Bosan",
        "Capek",
        "Kesepian",
        "Lesu",
        "Berduka",
        "Sedih",
        "Patah Hati",
        "Pusing"
    )

    val listAsalEmosi = listOf(
        "Keluarga",
        "Pekerjaan",
        "Teman",
        "Hobi",
        "Kesehatan",
        "Percintaan",
        "Keuangan",
        "Pendidikan",
        "Makanan",
        "Olahraga",
        "Seni",
        "Ibadah",
        "Refleksi diri",
        "Cuaca",
        "Belanja",
        "Tidur",
        "Santai",
        "Hiburan",
        "Orang Lain"
    )
    val selectedPositiveEmotions = remember { mutableStateListOf<String>() }
    val selectedNegativeEmotions = remember { mutableStateListOf<String>() }
    val selectedAsalEmotions = remember { mutableStateListOf<String>() }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text("Diary Moodku") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "* Kamu bakal dapet total 50 points dari pengisian diary ini!",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Apa emosi yang kamu\n rasakan sekarang?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(Modifier.height(16.dp))
                PositiveEmotionsInput(
                    positiveEmotions = listPositive,
                    selectedPositiveEmotions = selectedPositiveEmotions,
                    onPositiveSelected = { emotion ->
                        selectedPositiveEmotions.add(emotion)
                    },
                    onPositiveDeselected = { emotion ->
                        selectedPositiveEmotions.remove(emotion)

                    }
                )
                Divider()
                Spacer(Modifier.height(8.dp))
                NegativeEmotionsInput(
                    negativeEmotions = listNegative,
                    selectedNegativeEmotions = selectedNegativeEmotions,
                    onNegativeSelected = { emotion ->
                        selectedNegativeEmotions.add(emotion)
                    },
                    onNegativeDeselected = { emotion ->
                        selectedNegativeEmotions.remove(emotion)
                    }
                )

                Divider()
                Spacer(Modifier.height(8.dp))

                AsalEmotionsInput(
                    asalEmotions = listAsalEmosi,
                    selectedAsalEmotions = selectedAsalEmotions,
                    onAsalSelected = { emotion ->
                        selectedAsalEmotions.add(emotion)

                    },
                    onAsalDeselected = { emotion ->
                        selectedAsalEmotions.remove(emotion)
                    }
                )
                Divider()
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ceritakan Yuk", style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center
                    )
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80.dp)
                        .padding(horizontal = 16.dp),
                    value = state.note.toString(),
                    onValueChange = {
                        viewModel.onEvent(DiaryEvent.OnNoteChanged(it))

                    },
                    placeholder = { Text(text = "Ketik ceritamu di sini untuk mendapatkan rekomendasi dari AI secara gratis...") }
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(
                            DiaryEvent.onSaveDiary(
                                positive = ListConverter.convertListToString(
                                    selectedPositiveEmotions
                                ),
                                negative = ListConverter.convertListToString(
                                    selectedNegativeEmotions
                                ),
                                source = ListConverter.convertListToString(selectedAsalEmotions),
                                note = state.note.toString()
                            )
                        )
                    },
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text("Simpan")
                }

            }
        }
    )

    if (state.isDiaryDone) {
        PointPopupDialog(points = 50, onDismissRequest = {
            onPopBackStak()
        })
    }

    if (state.diaryError?.isNotEmpty() == true) {
        Toast.makeText(context, "Hari ini kamu telah mengisi diary!", Toast.LENGTH_SHORT).show()
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PositiveEmotionsInput(
    positiveEmotions: List<String>,
    selectedPositiveEmotions: List<String>,
    onPositiveSelected: (String) -> Unit,
    onPositiveDeselected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Emosi Positif", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center
            )
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 4,
            content = {
                positiveEmotions.forEach { emotion ->
                    val isSelected = selectedPositiveEmotions.contains(emotion)
                    Chip(
                        selected = isSelected,
                        label = emotion,
                        onChipClicked = { clickedEmotion ->
                            if (isSelected) {
                                onPositiveDeselected(clickedEmotion)
                            } else {
                                onPositiveSelected(clickedEmotion)
                            }
                        })
                }
            })
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NegativeEmotionsInput(
    negativeEmotions: List<String>,
    selectedNegativeEmotions: List<String>,
    onNegativeSelected: (String) -> Unit,
    onNegativeDeselected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Emosi Negatif", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center
            )
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 4,
            content = {
                negativeEmotions.forEach { emotion ->
                    val isSelected = selectedNegativeEmotions.contains(emotion)
                    Chip(
                        selected = isSelected,
                        label = emotion,
                        onChipClicked = { clickedEmotion ->
                            if (isSelected) {
                                onNegativeDeselected(clickedEmotion)
                            } else {
                                onNegativeSelected(clickedEmotion)
                            }
                        })
                }
            })
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AsalEmotionsInput(
    asalEmotions: List<String>,
    selectedAsalEmotions: List<String>,
    onAsalSelected: (String) -> Unit,
    onAsalDeselected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Asal Emosi Kamu", style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center
            )
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 4,
            content = {
                asalEmotions.forEach { emotion ->
                    val isSelected = selectedAsalEmotions.contains(emotion)
                    Chip(
                        selected = isSelected,
                        label = emotion,
                        onChipClicked = { clickedEmotion ->
                            if (isSelected) {
                                onAsalDeselected(clickedEmotion)
                            } else {
                                onAsalSelected(clickedEmotion)
                            }
                        })
                }
            })
    }

}

@Preview
@Composable
fun DiaryScreenPrev() {
    DiaryScreen({}, {})
}
