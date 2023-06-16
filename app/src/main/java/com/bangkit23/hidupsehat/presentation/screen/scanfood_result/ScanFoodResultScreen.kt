package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.LoadingDialog
import com.bangkit23.hidupsehat.presentation.components.OutlinedButtonWithIcon
import com.bangkit23.hidupsehat.presentation.components.TableFoodHeader
import com.bangkit23.hidupsehat.presentation.components.TableFoodItem
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import com.bangkit23.hidupsehat.presentation.screen.scanfood_edit.ScanFoodResultEditScreen
import com.bangkit23.hidupsehat.presentation.screen.scanfood_result.components.DialogAddFoods

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanFoodResultScreen(
    imageResult: Bitmap,
    listDetection: List<DetectionResult>,
    onRescanClick: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ScanFoodResultViewModel = hiltViewModel(),
    sheetEditState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ScanFoodResultEvent.AddDetectedFoods(listDetection))
    }

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    ScanFoodResultContent(
        imageResult = imageResult,
        scanResults = state.foods,
        totalCalories = state.foods.sumOf { it?.energyKKal ?: 0.0 },
        onRescanClick = onRescanClick,
        onNavigateUp = onNavigateUp,
        onAddButtonClick = {
            viewModel.onEvent(ScanFoodResultEvent.ShowDialogAddFoods)
        },
        onSaveButtonClick = {
            viewModel.onEvent(ScanFoodResultEvent.SaveAllFoods(state.foods))
        },
        onPortionSizeClick = {
            viewModel.onEvent(ScanFoodResultEvent.ShowDropDownPortionSize(it))
        },
        onEditFood = {
            viewModel.onEvent(ScanFoodResultEvent.EditFood(it))
        },
        onDropDownItemClick = { food, count ->
            viewModel.onEvent(ScanFoodResultEvent.ChangePortionSize(food, count))
        },
        portionSizes = state.portionSizes
    )

    if (state.isDialogAddFoodsShow) {
        DialogAddFoods(
            onDismiss = { viewModel.onEvent(ScanFoodResultEvent.HideDialogAddFoods) },
            foodQuery = state.foodSearchQuery,
            onFoodQueryChange = {
                viewModel.onEvent(ScanFoodResultEvent.OnSearchFoodQueryChange(it))
            },
            foods = state.foodSearched,
            getInitialFoods = {
                viewModel.onEvent(ScanFoodResultEvent.OnGetInitialAddFoods)
            },
            onButtonAddClick = {
                viewModel.onEvent(ScanFoodResultEvent.AddNewFood(it))
            }
        )
    }

    if (state.isDialogEditFoodShow) {
        ScanFoodResultEditScreen(
            sheetState = sheetEditState,
            onDismiss = { viewModel.onEvent(ScanFoodResultEvent.HideDialogEditFood) },
            food = state.foodEdit,
            onSaveClick = {
                viewModel.onEvent(ScanFoodResultEvent.SaveEditedFood(it))
            },
            onDeleteClick = {
                viewModel.onEvent(ScanFoodResultEvent.DeleteFood(it))
            },
            onPortionSizeClick = { food ->
                if (food != null) {
                    viewModel.onEvent(ScanFoodResultEvent.ShowDropDownPortionSize(food))
                }
            },
            portionSizes = state.portionSizes,
        )
    }

    if (state.isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            Toast.makeText(context, "Berhasil Menyimpan Makanan", Toast.LENGTH_SHORT).show()
            onNavigateUp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanFoodResultContent(
    imageResult: Bitmap,
    totalCalories: Double?,
    scanResults: SnapshotStateList<Food?>,
    portionSizes: List<Food>,
    onDropDownItemClick: (Food, Int?) -> Unit,
    onRescanClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onEditFood: (Food?) -> Unit,
    onSaveButtonClick: () -> Unit,
    onNavigateUp: () -> Unit,
    onPortionSizeClick: (Food) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan Result") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 100.dp),
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                item {
                    AsyncImage(
                        model = imageResult,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
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
                    TableFoodHeader()
                }
                items(items = scanResults) {
                    TableFoodItem(
                        foodName = it?.name,
                        portionSize = it?.portionSize,
                        count = it?.count,
                        energyKKal = it?.energyKKal,
                        onPortionSizeClick = {
                            if (it != null) {
                                onPortionSizeClick(it)
                            }
                        },
                        portionSizes = portionSizes,
                        onDropDownItemClick = onDropDownItemClick,
                        modifier = Modifier
                            .clickable { onEditFood(it) }
                            .padding(vertical = 8.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Divider()
                Spacer(Modifier.height(8.dp))
                Row {
                    Text(
                        text = "Total Kalori",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                            .padding(horizontal = 16.dp)
                    )
                    Text(
                        text = "$totalCalories kkal",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row {
                    OutlinedButtonWithIcon(
                        text = "Tambah",
                        icon = Icons.Rounded.Add,
                        onClick = onAddButtonClick,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 4.dp, bottom = 16.dp)
                    )
                    ButtonWithIcon(
                        text = "Simpan",
                        icon = Icons.Rounded.Check,
                        onClick = onSaveButtonClick,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}
