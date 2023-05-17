package com.bangkit23.hidupsehat.presentation.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("on-boarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Feeds : Screen("feeds")
    object Consultation : Screen("consultation")
    object Leaderboard : Screen("leaderboard")
}
