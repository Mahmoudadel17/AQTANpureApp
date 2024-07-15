package com.example.aqtan.presentation.navigation

import com.example.aqtan.R


sealed class NavigationScreen( val icon: Int,val selectedIcon:Int, val route:String){

    // bottom navigation screens
    data object Home:NavigationScreen(
        icon =   R.drawable.home,
        selectedIcon =   R.drawable.home_select,
        route = "home"
    )
    data object Shop:NavigationScreen(
        icon =   R.drawable.shopping_cart,
        selectedIcon =   R.drawable.shopping_cart_select,
        route = "shop"
    )

    data object Bag:NavigationScreen(
        icon =   R.drawable.bags_shopping,
        selectedIcon =   R.drawable.bags_shopping_selcet,
        route = "bag"
    )

    data object Profile:NavigationScreen(
        icon =   R.drawable.user,
        selectedIcon =   R.drawable.user_select,
        route = "profile"
    )
}