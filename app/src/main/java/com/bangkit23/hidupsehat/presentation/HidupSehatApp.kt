package com.bangkit23.hidupsehat.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.presentation.common.AuthSharedViewModel
import com.bangkit23.hidupsehat.presentation.common.ExerciseSharedViewModel
import com.bangkit23.hidupsehat.presentation.common.ScanSharedViewModel
import com.bangkit23.hidupsehat.presentation.model.User
import com.bangkit23.hidupsehat.presentation.navigation.NavigationItem
import com.bangkit23.hidupsehat.presentation.navigation.Screen
import com.bangkit23.hidupsehat.presentation.screen.auth.login.LoginScreen
import com.bangkit23.hidupsehat.presentation.screen.auth.register.RegisterScreen
import com.bangkit23.hidupsehat.presentation.screen.consultation.ConsultationScreen
import com.bangkit23.hidupsehat.presentation.screen.exercise.ExerciseScreen
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.ExercisePlayScreen
import com.bangkit23.hidupsehat.presentation.screen.feeds.FeedScreen
import com.bangkit23.hidupsehat.presentation.screen.home.HomeScreen
import com.bangkit23.hidupsehat.presentation.screen.leaderboard.LeaderboardScreen
import com.bangkit23.hidupsehat.presentation.screen.manual_foods.ManualFoodsScreen
import com.bangkit23.hidupsehat.presentation.screen.mental_health.MentalHealthScreen
import com.bangkit23.hidupsehat.presentation.screen.monitoring.MonitoringScreen
import com.bangkit23.hidupsehat.presentation.screen.onboarding.OnBoardingScreen
import com.bangkit23.hidupsehat.presentation.screen.preference.UserInformationScreen
import com.bangkit23.hidupsehat.presentation.screen.preference.UserTargetScreen
import com.bangkit23.hidupsehat.presentation.screen.profile.ChangePasswordScreen
import com.bangkit23.hidupsehat.presentation.screen.profile.FaqScreen
import com.bangkit23.hidupsehat.presentation.screen.profile.ProfileScreen
import com.bangkit23.hidupsehat.presentation.screen.profile.UpdateProfile
import com.bangkit23.hidupsehat.presentation.screen.reminder.ReminderScreen
import com.bangkit23.hidupsehat.presentation.screen.scanfood.ScanFoodScreen
import com.bangkit23.hidupsehat.presentation.screen.scanfood_result.ScanFoodResultScreen
import com.bangkit23.hidupsehat.presentation.screen.splash.SplashScreen
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.util.toUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HidupSehatApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
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
            startDestination = "splash-screen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash-screen") {
                SplashScreen(
                    onTimeOut = { isLoggedIn ->
                        if (isLoggedIn) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("splash-screen") {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate("auth-graph") {
                                popUpTo("splash-screen") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onScanClicked = {
                        navController.navigate("scan-graph")
                    },
                    onPoseMenuClicked = {
                        navController.navigate("exercise-graph")
                    },
                    onProfileClicked = {
                        navController.navigate("profile-graph")
                    },
                    onReminderMenuClicked = {
                        navController.navigate("reminder-graph")
                    },
                    onSeeAllMonitoringClick = {
                        navController.navigate("monitoring-graph")
                    },
                    onManualFoodsClick = {
                        navController.navigate("manual-foods")
                    },
                    onMentalHealthClick = {
                        navController.navigate("mental-health-graph")
                    }
                )
            }
            composable(Screen.Consultation.route) {
                ConsultationScreen()
            }
            composable(Screen.Feeds.route) {
                FeedScreen()
            }
            composable(Screen.Leaderboard.route) {
                LeaderboardScreen()
            }
            composable("manual-foods") {
                ManualFoodsScreen(
                    navigateUp = {
                        navController.navigateUp()
                    }
                )
            }
            navigation(
                startDestination = "on-boarding",
                route = "auth-graph"
            ) {
                composable("on-boarding") {
                    OnBoardingScreen(
                        moveToLogin = {
                            navController.navigate(Screen.Login.route)
                        }
                    )
                }
                composable(Screen.Login.route) { entry ->
                    val viewModel = entry.sharedViewModel<AuthSharedViewModel>(navController)
                    LoginScreen(
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("auth-graph") {
                                    inclusive = true
                                }
                            }
                        },
                        moveToUserPreference = { userData ->
                            viewModel.setUser(userData?.toUser() ?: User())
                            navController.navigate(Screen.PreferenceTarget.route)
                        },
                        onRegisterClick = {
                            navController.navigate(Screen.Register.route)
                        }
                    )
                }
                composable(Screen.Register.route) { entry ->
                    val viewModel = entry.sharedViewModel<AuthSharedViewModel>(navController)
                    RegisterScreen(
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("auth-graph") {
                                    inclusive = true
                                }
                            }
                        },
                        moveToUserPreference = { userData ->
                            viewModel.setUser(userData?.toUser() ?: User())
                            navController.navigate(Screen.PreferenceTarget.route)
                        },
                        navigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
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
                        navArgument("choiceId") { type = NavType.IntType },
                        navArgument("weightTarget") { type = NavType.StringType }
                    )
                ) { entry ->
                    val viewModel = entry.sharedViewModel<AuthSharedViewModel>(navController)
                    val user by viewModel.user.collectAsStateWithLifecycle()
                    val choiceId = entry.arguments?.getInt("choiceId") ?: -1
                    val weightTarget = entry.arguments?.getString("weightTarget") ?: "0"
                    UserInformationScreen(
                        user = user,
                        choiceId = choiceId,
                        weightTarget = weightTarget,
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("auth-graph") {
                                    inclusive = true
                                }
                            }
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
                            onRescanClick = { navController.navigateUp() },
                            onNavigateUp = { navController.navigateUp() }
                        )
                    }
                }
            }
            navigation(
                startDestination = "exercise",
                route = "exercise-graph"
            ) {
                composable("exercise") { entry ->
                    val viewModel = entry.sharedViewModel<ExerciseSharedViewModel>(navController)
                    ExerciseScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                        moveToExercisePlay = { exercise ->
                            viewModel.setExercise(exercise)
                            navController.navigate("exercise-play")
                        }
                    )
                }
                composable("exercise-play") { entry ->
                    val viewModel = entry.sharedViewModel<ExerciseSharedViewModel>(navController)
                    val exercise by viewModel.exercise.collectAsStateWithLifecycle()
                    ExercisePlayScreen(
                        exercise = exercise,
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                    )
                }
            }
            navigation(
                startDestination = "profile",
                route = "profile-graph"
            ) {
                composable("profile") {
                    ProfileScreen(
                        moveToUpdateProfile = {
                            navController.navigate("update-profile")
                        },
                        moveToChangePassword = {
                            navController.navigate("change-password")
                        },
                        moveToFaq = {
                            navController.navigate("faq")
                        },
                        moveToRating = {
                            navController.navigate("rating")
                        },
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                    )
                }
                composable("update-profile") {
                    UpdateProfile(
                        onNavigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
                composable("change-password") {
                    ChangePasswordScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
                composable("faq") {
                    FaqScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                        faq = listOf("Pertanyaan 1"))
                }
                composable("rating", deepLinks = listOf(navDeepLink {
                    uriPattern = "https://play.google.com/store/apps/details?id=com.gojek.app"
                })) {
                    val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.gojek.app")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }
            }
            navigation(
                startDestination = "reminder",
                route = "reminder-graph"
            ) {
                composable( "reminder") {
                    ReminderScreen()
                }
            }
            navigation(
                startDestination = "monitoring",
                route = "monitoring-graph"
            ) {
                composable("monitoring") {
                    MonitoringScreen()
                }
            }
            navigation(
                startDestination = "mental-health",
                route = "mental-health-graph"
            ) {
                composable("mental-health") {
                    MentalHealthScreen(
                        navigateUp = {
                            navController.navigateUp()
                        }
                    )
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
    modifier: Modifier = Modifier,
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