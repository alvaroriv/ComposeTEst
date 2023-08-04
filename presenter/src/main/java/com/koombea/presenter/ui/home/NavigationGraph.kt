package com.koombea.presenter.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.koombea.composetest.presenter.ui.home.HomeScreen
import com.koombea.composetest.presenter.ui.home.SettingsScreen
import com.koombea.composetest.presenter.ui.home.TransactionScreen
import com.koombea.presenter.model.BottomNavItem

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Transaction.screen_route) {
            TransactionScreen()
        }
        composable(BottomNavItem.Settings.screen_route) {
            SettingsScreen()
        }
    }
}