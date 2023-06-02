package com.bangkit23.hidupsehat.presentation.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("on-boarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object PreferenceTarget : Screen("preference-target")
    object PreferenceInformation : Screen("preference-information/{choiceId}/{weightTarget}") {
        fun createRoute(choiceId: Int, weightTarget: String) = "preference-information/$choiceId/$weightTarget"
    }
    object Home : Screen("home")
    object Feeds : Screen("feeds")
    object Consultation : Screen("consultation")
    object Leaderboard : Screen("leaderboard")
    object Scan : Screen("scan")
    object ScanResult : Screen("scan-result")
    object Profile : Screen("profile")
}
