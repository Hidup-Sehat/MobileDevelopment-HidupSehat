package com.bangkit23.hidupsehat.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.common.ScanSharedViewModel
import com.bangkit23.hidupsehat.presentation.navigation.NavigationItem
import com.bangkit23.hidupsehat.presentation.navigation.Screen
import com.bangkit23.hidupsehat.presentation.screen.feeds.FeedScreen
import com.bangkit23.hidupsehat.presentation.screen.home.HomeScreen
import com.bangkit23.hidupsehat.presentation.screen.preference.UserInformationScreen
import com.bangkit23.hidupsehat.presentation.screen.preference.UserTargetScreen
import com.bangkit23.hidupsehat.presentation.screen.scanfood.ScanFoodScreen
import com.bangkit23.hidupsehat.presentation.screen.scanfood_result.ScanFoodResultScreen
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun HidupSehatApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = modifier,
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
            startDestination = "auth-graph",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onScanClicked = {
                        navController.navigate("scan-graph")
                    }
                )
            }
            composable(Screen.Feeds.route) {
                FeedScreen()
            }
            navigation(
                startDestination = Screen.PreferenceTarget.route,
                route = "auth-graph"
            ) {
                composable(Screen.PreferenceTarget.route) {
                    UserTargetScreen(
                        onNextClick = { choiceId, targetWeight ->
                            navController.navigate(
                                Screen.PreferenceInformation.createRoute(choiceId, targetWeight)
                            )
                        }
                    )
                }
                composable(
                    Screen.PreferenceInformation.route,
                    arguments = listOf(
                        navArgument("choiceId") { NavType.IntType },
                        navArgument("weightTarget") { NavType.StringType }
                    )
                ) {
                    val choiceId = it.arguments?.getInt("choiceId") ?: -1
                    val weightTarget = it.arguments?.getString("weightTarget") ?: "0"
                    UserInformationScreen(
                        choiceId = choiceId,
                        weightTarget = weightTarget,
                        navigateToHome = {
                            navController.navigate(Screen.Home.route)
                        }
                    )
                }
            }
            navigation(
                startDestination = Screen.Scan.route,
                route = "scan-graph"
            ) {
                composable(Screen.Scan.route) { entry ->
                    val viewModel = entry.sharedViewModel<ScanSharedViewModel>(navController)
                    ScanFoodScreen(
                        onDetectedImage = { bitmap, detectionResults ->
                            viewModel.setBitmap(bitmap)
                            viewModel.setScanResults(detectionResults)
                            navController.navigate(Screen.ScanResult.route)
                        },
                        onNavigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(Screen.ScanResult.route) { entry ->
                    val viewModel = entry.sharedViewModel<ScanSharedViewModel>(navController)
                    val mBitmap by viewModel.bitmap.collectAsStateWithLifecycle()
                    val results by viewModel.scanResults.collectAsStateWithLifecycle()
                    mBitmap?.let {
                        ScanFoodResultScreen(
                            imageResult = it,
                            listDetection = results,
                            onRescanClick = { navController.navigateUp() }
                        )
                    }
                }
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
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
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
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph[Screen.Home.route].id) {
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

@Preview(showBackground = true)
@Composable
fun HidupSehatAppPreview() {
    HidupSehatTheme {
        HidupSehatApp()
    }
}