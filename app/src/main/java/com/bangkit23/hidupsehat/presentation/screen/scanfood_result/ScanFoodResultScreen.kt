package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.components.ButtonWithIcon
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
    viewModel: ScanFoodResultViewModel = hiltViewModel(),
    sheetEditState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ScanFoodResultEvent.AddDetectedFoods(listDetection))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    ScanFoodResultContent(
        imageResult = imageResult,
        scanResults = state.foods,
        onRescanClick = onRescanClick,
        onAddButtonClick = {
            viewModel.onEvent(ScanFoodResultEvent.ShowDialogAddFoods)
        },
        onSaveButtonClick = {},
        onPortionSizeClick = {
            viewModel.onEvent(ScanFoodResultEvent.ShowDropDownPortionSize(it))
        },
        onEditFood = {
            viewModel.onEvent(ScanFoodResultEvent.EditFood(it))
        },
        onDropDownItemClick = {
            viewModel.onEvent(ScanFoodResultEvent.ChangePortionSize(it))
        },
        onDropDownDismiss = {
            viewModel.onEvent(ScanFoodResultEvent.HideDropDownPortionSize)
        },
        isDropDownPortionSizeShow = state.isPortionSizeDropDownShow,
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
            onButtonAddClick = {
                viewModel.onEvent(ScanFoodResultEvent.AddNewFood(it))
            }
        )
    }

    if (state.isDialogEditFoodShow) {
        ScanFoodResultEditScreen(
            sheetState = sheetEditState,
            onDismiss = { viewModel.onEvent(ScanFoodResultEvent.HideDialogEditFood) },
            food = state.foodEdit
        )
    }
}

@Composable
fun ScanFoodResultContent(
    imageResult: Bitmap,
    scanResults: MutableList<Food?>,
    isDropDownPortionSizeShow: Boolean,
    portionSizes: List<Food>,
    onDropDownItemClick: (Food) -> Unit,
    onDropDownDismiss: () -> Unit,
    onRescanClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onEditFood: (Food?) -> Unit,
    onSaveButtonClick: () -> Unit,
    onPortionSizeClick: (Food) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp),
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
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
        ) {
            OutlinedButtonWithIcon(
                text = "Tambah",
                icon = Icons.Rounded.Add,
                onClick = onAddButtonClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
            )
            ButtonWithIcon(
                text = "Simpan",
                icon = Icons.Rounded.Check,
                onClick = onSaveButtonClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            )
        }
    }

}
