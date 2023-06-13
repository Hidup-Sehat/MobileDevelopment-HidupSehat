package com.bangkit23.hidupsehat.presentation.screen.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.domain.model.leaderboard.LeaderboardItem
import com.bangkit23.hidupsehat.presentation.screen.leaderboard.components.ItemLeaderboard
@Composable
fun LeaderboardScreen(
    viewModel: LeaderboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeaderboardContent(
        listLeaderboard = state.listLeaderboard,
        onLeaderboardTypeChanged = { position ->
            viewModel.onEvent(LeaderboardEvent.OnLeaderboardTypeChanged(position))
        },
        userPosition = state.userPosition
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardContent(
    listLeaderboard: List<LeaderboardItem>,
    userPosition: LeaderboardItem,
    onLeaderboardTypeChanged: (position: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Leaderboard")
                },
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.HelpOutline,
                            contentDescription = "Help about Leaderboard"
                        )
                    }
                }
            )
        }
    ) {
        var state by remember { mutableStateOf(0) }
        val titles = listOf("Harian", "Mingguan", "Bulanan")

        Column(modifier = Modifier.padding(it)) {
            TabRow(
                selectedTabIndex = state,
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = { Text(text = title) }
                    )
                }
            }
            TypeLeaderboard(
                position = state,
                listLeaderboard = listLeaderboard,
                userPosition = userPosition,
                onLeaderboardTypeChanged = onLeaderboardTypeChanged,
            )
        }
    }
}

@Composable
fun TypeLeaderboard(
    position: Int,
    userPosition: LeaderboardItem,
    listLeaderboard: List<LeaderboardItem>,
    onLeaderboardTypeChanged: (position: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(key1 = position) {
        onLeaderboardTypeChanged(position)
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Column{
                Text(
                    text = "Posisimu",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(16.dp))
                ItemLeaderboard(
                    username = userPosition.username ?: "",
                    name = userPosition.name ?: "",
                    photoUrl = userPosition.avatar ?: "https://cdn-icons-png.flaticon.com/128/4140/4140039.png",
                    points = userPosition.points?.toLong() ?: 0,
                    position = userPosition.position ?: 0,
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "Ranking",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
            }
        }
        items(items = listLeaderboard) {
            ItemLeaderboard(
                username = it.username ?: "",
                name = it.name ?: "",
                photoUrl = it.avatar ?: "",
                position = it.position ?: 0,
                points = it.points?.toLong() ?: 0,
            )
        }
    }
}