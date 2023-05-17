package com.bangkit23.hidupsehat.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.navigation.NavigationItem
import com.bangkit23.hidupsehat.presentation.navigation.Screen
import com.bangkit23.hidupsehat.presentation.screen.feeds.FeedScreen
import com.bangkit23.hidupsehat.presentation.screen.home.HomeScreen
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HidupSehatApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = modifier,
        topBar = {
            if (currentRoute == Screen.Home.route) {
                TopAppBarWithProfile(
                    name = "Rijal",
                    userAvatar = "",
                    onNotificationClick = {},
                    onProfileClick = {}
                )
            }
        },
        bottomBar = {
            if (currentRoute.shouldShowBottomAppBar()) {
                BottomAppBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Feeds.route) {
                FeedScreen()
            }
        }
    }
}

private fun String?.shouldShowBottomAppBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Feeds.route,
        Screen.Consultation.route,
        Screen.Leaderboard.route
    )
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Rounded.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = "Konsultasi",
            icon = Icons.Rounded.Call,
            screen = Screen.Consultation
        ),
        NavigationItem(
            title = "Leaderboard",
            icon = ImageVector.vectorResource(R.drawable.ic_leaderboard),
            screen = Screen.Leaderboard
        ),
        NavigationItem(
            title = "Feeds",
            icon = ImageVector.vectorResource(R.drawable.ic_article),
            screen = Screen.Feeds
        ),
    )
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithProfile(
    name: String,
    userAvatar: String,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text("Hi, $name!") },
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(Icons.Outlined.Notifications, contentDescription = null)
            }
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = onProfileClick,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = CircleShape
                        )
                ) {
                    Text(
                        text = "${name.first()}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
        },
    )
}

@Preview(showBackground = true)
@Composable
fun HidupSehatAppPreview() {
    HidupSehatTheme {
        HidupSehatApp()
    }
}