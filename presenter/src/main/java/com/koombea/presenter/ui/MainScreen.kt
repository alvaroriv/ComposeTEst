package com.koombea.presenter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koombea.composetest.presenter.ui.home.DashboardScreen
import com.koombea.composetest.presenter.ui.login.LoginScreen
import com.koombea.presenter.model.Routes
import com.koombea.presenter.ui.signup.SignUpScreen

@Composable
fun MainScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.SignUp.route) {
            SignUpScreen()
        }

        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Routes.Dashboard.route) {
            DashboardScreen()
        }


    }
}