package com.bangkit23.hidupsehat.presentation.screen.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit23.hidupsehat.presentation.screen.profile.model.Faq

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(
    onNavigateUp: () -> Unit,
    faq: List<Faq>
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "FAQ")
            }, navigationIcon = {
                IconButton(onClick = {
                    onNavigateUp.invoke()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            })
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(it)
            ) {
                items(faq) {
                    ItemFaq(question = it.ask, answer = it.answer)
                }
            }
        }
    )
}

@Composable
fun ItemFaq(question: String, answer: String) {
    var isExpanded by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 4.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(start = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
                .padding(start = 8.dp)
        ) {
            Text(text = question, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            if (isExpanded) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = answer)
            }
        }
        IconButton(onClick = {
            isExpanded = !isExpanded
        }) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FaqScreenPrev() {
    val dummyList = listOf(
        Faq("Apa itu aplikasi HidupSehat", ""),
        Faq("Apakah aplikasi HidupSehat gratis?", ""),
        Faq("Siapa yang menciptakan aplikasi HidupSehat?", ""),
        Faq("Mengapa beberapa fitur belum tersedia", ""),
        Faq("Fitur apa saja yang berada dalam pengembangan?", "")
    )
    FaqScreen({}, dummyList)
}

@Preview(showBackground = true)
@Composable
fun ItemFaqPrev() {
    ItemFaq(question = "Pertanyaan 1", answer = "Jawaban 1")
}