package com.koombea.presenter.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.koombea.presenter.model.BottomNavItem
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.login.TransactionViewModel

@Composable
fun NavigationGraph(navController: NavHostController, settingsViewModel: SettingsViewModel,transactionViewModel: TransactionViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController,transactionViewModel)
        }
        composable(BottomNavItem.Transaction.screen_route) {
            TransactionScreen(transactionViewModel)
        }
        composable(BottomNavItem.Settings.screen_route) {
            SettingsScreen(settingsViewModel)
        }
    }
}