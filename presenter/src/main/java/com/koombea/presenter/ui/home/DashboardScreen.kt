package com.koombea.presenter.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.koombea.presenter.ui.home.BottomNavigation
import com.koombea.presenter.ui.home.NavigationGraph
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.login.TransactionViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(settingsViewModel: SettingsViewModel, transactionViewModel: TransactionViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController, settingsViewModel,transactionViewModel)
    }
}