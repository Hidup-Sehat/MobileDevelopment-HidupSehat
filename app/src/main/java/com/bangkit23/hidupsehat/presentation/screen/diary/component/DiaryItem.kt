package com.bangkit23.hidupsehat.presentation.screen.diary.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(
    isSelected : Boolean,
    label : String
) {
    FilterChip(
        modifier = Modifier.padding(horizontal = 4.dp),
        selected = false , onClick = { /*TODO*/ }, label = {Text(label)})
}

//@Composable
//fun DiaryItem(
//    modifier: Modifier = Modifier,
//    onItemClicked: () -> Unit,
//    isSelected: Boolean = false,
//    label: String
//) {
//    AssistChip(
//        modifier = Modifier.wrapContentHeight(),
//        onClick = {
//        onItemClicked()
//    }, label = {
//        Text(text = label)
//    },
//        colors = AssistChipDefaults.assistChipColors(
//            if (isSelected) {
//                MaterialTheme.colorScheme.primaryContainer
//            } else Color.Transparent
//        ),
//        border = AssistChipDefaults.assistChipBorder(
//            if (isSelected){
//                Color.Transparent
//            }else {
//                Color.Gray
//            }
//        )
//    )
//}
//
//@Composable
//fun Chip(text: String) {
//    Surface(
//        modifier = Modifier.padding(4.dp),
//        shadowElevation = 4.dp,
//        shape = RoundedCornerShape(16.dp),
//        color = Color.LightGray
//    ) {
//        Text(
//            text = text,
//            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
//            color = Color.Black
//        )
//    }
//}

@Composable
fun Sugg() {
    SuggestionChip(onClick = {}, label = { Text("Antusiass") })
}

@Preview
@Composable
fun DiaryItemPrev() {
//    DiaryItem(label = "Antusias", onItemClicked = {})
//    Sugg()
    Chip(isSelected = false, label = "Semangat")
}