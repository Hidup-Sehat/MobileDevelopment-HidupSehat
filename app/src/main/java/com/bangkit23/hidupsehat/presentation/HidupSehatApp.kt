package com.bangkit23.hidupsehat.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme

@Composable
fun HidupSehatApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navController.currentDestination?.route
    NavHost(
        navController = navController,
        startDestination = "",
        modifier = modifier
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HidupSehatTheme {
        HidupSehatApp()
    }
}