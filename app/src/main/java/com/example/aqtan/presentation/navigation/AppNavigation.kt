package com.example.aqtan.presentation.navigation



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aqtan.data.list
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.presentation.MainScreen
import com.example.aqtan.presentation.components.SuccessScreen
import com.example.aqtan.presentation.homeScreens.bag.BagScreen
import com.example.aqtan.presentation.homeScreens.home.AllProductsScreen
import com.example.aqtan.presentation.homeScreens.home.HomeScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreenViewModel
import com.example.aqtan.presentation.homeScreens.shop.ProductDetailsScreen
import com.example.aqtan.presentation.homeScreens.shop.ShopScreen
import com.example.aqtan.presentation.homeScreens.shop.search.SearchScreen
import com.example.aqtan.presentation.introScreens.IntroScreen


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation(
    isIntro: Boolean,
    profileViewModel: ProfileScreenViewModel,
) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = if (isIntro) Screens.Intro.route else Screens.Home.route
        ) {
            composable(route = Screens.Intro.route) {
                IntroScreen(navController = navController)
            }
            composable(route = Screens.Home.route) {
                // Home screen
                MainScreen(
                    appNavController = navController,
                    profileViewModel = profileViewModel,
                )
            }
            composable(
                route = Screens.ProductDetails.route,
            ) {
                 // Product Details screen
                 val product: Product? = navController.previousBackStackEntry?.savedStateHandle?.get("product")
                product?.let {
                    ProductDetailsScreen(
                        product = it,
                        navController = navController,
                    )
                }
            }
            composable(
                route = "${Screens.AllProducts.route}/{listId}",
                arguments = listOf(
                    navArgument("listId"){
                        type = NavType.IntType
                    }
                    )
                ) { it ->
                val listId = it.arguments?.getInt("listId")

                var newList:HomeLists? = null
                listId?.let {listID->
                    newList = list.find {currList-> currList.id == listID }
                }
                newList?.let {homeList->
                    AllProductsScreen(navController = navController, allProducts = homeList)
                }

            }
            composable(route = Screens.Search.route) {
                SearchScreen(navController = navController)
            }


            composable(route = Screens.Checkout.route) {
            }

            composable(route = Screens.Success.route) {
                SuccessScreen(navController)

            }

        }

}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavigation(
    bottomNavController: NavHostController,
    appNavController: NavHostController,
    profileViewModel: ProfileScreenViewModel,
    ) {
        NavHost(
            navController = bottomNavController,
            startDestination = NavigationScreen.Home.route
        ) {
            composable(route = NavigationScreen.Home.route) {
                HomeScreen(navController = appNavController)
            }
            composable(route = NavigationScreen.Shop.route) {
                ShopScreen(appNavController)
            }

            composable(route = NavigationScreen.Bag.route) {
                BagScreen()
            }
            composable(route = NavigationScreen.Profile.route) {

                ProfileScreen(
                    profileViewModel = profileViewModel
                )
            }
    }

}
