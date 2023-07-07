package com.koombea.composetest.model

import com.koombea.composetest.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.mipmap.home,"home")
    object Transaction: BottomNavItem("Transaction", R.mipmap.transaction,"transaction")
    object Settings: BottomNavItem("Settings", R.mipmap.settings,"settings")
}