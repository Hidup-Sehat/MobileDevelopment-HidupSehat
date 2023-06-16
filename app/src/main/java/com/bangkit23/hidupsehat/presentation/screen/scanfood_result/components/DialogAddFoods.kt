package com.bangkit23.hidupsehat.presentation.screen.scanfood_result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bangkit23.hidupsehat.domain.model.food.Food
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddFoods(
    foodQuery: String,
    onFoodQueryChange: (String) -> Unit,
    foods: List<Food>,
    onDismiss: () -> Unit,
    onButtonAddClick: (Food) -> Unit,
    getInitialFoods: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DisposableEffect(Unit) {
        getInitialFoods()
        onDispose {
            onFoodQueryChange("")
        }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tambah Makanan") },
                    navigationIcon = {
                        IconButton(
                            onClick = onDismiss,
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = onDismiss,
                        ) {
                            Text("Selesai")
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) { contentPadding ->
            Column(
                modifier = modifier.fillMaxSize()
                    .padding(contentPadding)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = foodQuery,
                    onValueChange = onFoodQueryChange,
                    placeholder = { Text("Cari nama makanan") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(items = foods, key = { it.id }) {
                        ItemSearchFood(
                            foodName = it.name,
                            energyKKal = it.energyKKal,
                            protein = it.protein,
                            portionSize = it.portionSize,
                            onButtonAddClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Berhasil menambahkan ${it.name}")
                                }
                                onButtonAddClick(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemSearchFood(
    foodName: String?,
    portionSize: String?,
    energyKKal: Double?,
    protein: Double?,
    onButtonAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "$foodName",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "$portionSize ,$energyKKal kkal, ${protein}g protein",
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(
            onClick = onButtonAddClick,
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add to list"
            )
        }
    }
}