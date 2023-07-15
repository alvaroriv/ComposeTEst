package com.koombea.presenter.model

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object Login : Routes("Login")
    object Dashboard : Routes("Dashboard")
    object Add : Routes("Add")
}