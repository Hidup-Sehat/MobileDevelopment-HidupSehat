package com.bangkit23.hidupsehat.presentation.screen.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.screen.diary.component.Chip

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DiaryScreen() {
    val listPositive = listOf<String>(
        "Antusiass",
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

    val listNegative = listOf<String>(
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

    val listAsalEmosi = listOf<String>(
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


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
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
                    text = "Apa emosi yang kamu\n rasakan sekarang?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )

//emosi positive
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

//emosi negatif
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

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ceritakan Yuk", style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 16.dp),
                    value = "", onValueChange = {},
                    placeholder = { Text(text = "Ketik ceritamu disini...") }
                )

                Button(
                    onClick = {},
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text("Simpan")
                }

            }
        }
    )

}

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun DiaryContent(
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit,
    isSelected: Boolean,

    ) {
    val listPositive = listOf<String>(
        "Antusiass",
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
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Apa emosi yang kamu rasakan sekarang?",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary, fontSize = 16.sp
            )
        )

        Text(text = "Emosi Positif")
        LazyHorizontalGrid(rows = GridCells.Fixed(4)) {
            items(listPositive) {
                FilterChip(

                    selected = false, onClick = { /*TODO*/ }, label = { Text(it) })
            }
        }
        Divider()

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PositiveEmotionsInput(
    positiveEmotions: List<String>,
    selectedPositiveEmotions: List<String>,
    onPositiveSelected: (String) -> Unit,
    onPositiveDeselected: (String) -> Unit,
) {
    Column() {
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
) {
    Column() {
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
) {
    Column() {
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
    DiaryScreen()
}
