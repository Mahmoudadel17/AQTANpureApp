package com.example.aqtan.presentation.navigation




sealed class Screens(val route:String){
    data object Intro : Screens(route = "intro")
    data object Home : Screens(route = "home")
    data object ProductDetails : Screens(route = "productDetails")
    data object AllProducts : Screens(route = "allProducts")
    data object Search : Screens(route = "search")
    data object Success : Screens(route = "success")
    data object Checkout : Screens(route = "checkout")

}
