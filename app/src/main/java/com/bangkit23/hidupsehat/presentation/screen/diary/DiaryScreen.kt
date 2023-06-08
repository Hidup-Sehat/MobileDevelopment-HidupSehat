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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var onItemClicked by remember { mutableStateOf(false) }
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
//            DiaryContent(
//                modifier = Modifier.padding(it),
//                onItemClicked = {
//                    onItemClicked = !onItemClicked
//                },
//                isSelected = onItemClicked
//            )
//            FlowRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(it), horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//                FilterChip(selected = false, onClick = {}, label = { Text("Senang") })
//            }
            Column(
                modifier = Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Apa emosi yang kamu\n rasakan sekarang?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary, fontSize = 16.sp, textAlign = TextAlign.Center
                    )
                )

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
                        listPositive.forEach {
                            Chip(isSelected = false, label = it)
                        }
                    })
                Divider()

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
                        listPositive.forEach {
                            Chip(isSelected = false, label = it)
                        }
                    })
                Divider()

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
                        listPositive.forEach {
                            Chip(isSelected = false, label = it)
                        }
                    })
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

@Preview
@Composable
fun DiaryScreenPrev() {
    DiaryScreen()
}

@Preview
@Composable
fun ContentPrev() {
    DiaryContent(onItemClicked = {}, isSelected = false)
}
