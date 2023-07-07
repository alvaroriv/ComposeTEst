package com.koombea.composetest.presenter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koombea.composetest.model.Routes
import com.koombea.composetest.presenter.ui.home.AddScreen
import com.koombea.composetest.presenter.ui.home.DashboardScreen
import com.koombea.composetest.presenter.ui.login.LoginScreen
import com.koombea.composetest.presenter.ui.signup.SignUpScreen

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