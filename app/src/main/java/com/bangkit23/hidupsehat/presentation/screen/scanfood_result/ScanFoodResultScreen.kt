package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.screen.home.components.ItemFood
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import com.bangkit23.hidupsehat.presentation.model.Food

@Composable
fun ScanFoodResultScreen(
    imageResult: Bitmap,
    listDetection: List<DetectionResult>,
    onRescanClick: () -> Unit,
    viewModel: ScanFoodResultViewModel = hiltViewModel()
) {
    LaunchedEffect(listDetection) {
        viewModel.addAllScanResults(listDetection)
    }
    val scanResults by viewModel.scanResults
    ScanFoodResultContent(
        imageResult = imageResult,
        scanResults = scanResults,
        onRescanClick = onRescanClick,
        onAddButtonClick = {},
        onSaveButtonClick = {},
    )
}

@Composable
fun ScanFoodResultContent(
    imageResult: Bitmap,
    scanResults: List<Food>,
    onRescanClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
            item {
                AsyncImage(
                    model = imageResult,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                        .height(256.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            item {
                Column(Modifier.fillMaxWidth()) {
                    OutlinedButtonWithIcon(
                        text = "Scan Ulang",
                        icon = ImageVector.vectorResource(R.drawable.ic_camera),
                        onClick = onRescanClick,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "Makanan",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.width(112.dp)
                    )
                    Text(
                        text = "Jumlah",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Ukuran",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Kalori",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            items(items = scanResults) {
                ItemFood(
                    name = it.name,
                    count = it.count,
                    size = 1,
                    calories = it.calories.toDouble()
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
        ) {
            OutlinedButtonWithIcon(
                text = "Tambah",
                icon = Icons.Rounded.Add,
                onClick = onAddButtonClick,
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
            )
            Spacer(Modifier.width(8.dp))
            ButtonWithIcon(
                text = "Simpan",
                icon = Icons.Rounded.Check,
                onClick = onSaveButtonClick,
                modifier = Modifier.weight(1f)
                    .padding(start = 4.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            )
        }
    }
}
