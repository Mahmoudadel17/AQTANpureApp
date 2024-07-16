package com.example.aqtan.presentation.navigation



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aqtan.presentation.MainScreen
import com.example.aqtan.presentation.components.Product
import com.example.aqtan.presentation.components.SuccessScreen
import com.example.aqtan.presentation.homeScreens.home.HomeScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreenViewModel
import com.example.aqtan.presentation.homeScreens.shop.ProductDetailsScreen
import com.example.aqtan.presentation.homeScreens.shop.ShopScreen
import com.example.aqtan.presentation.homeScreens.shop.search.SearchScreen
import com.example.aqtan.presentation.introScreens.IntroScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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

            }
            composable(route = NavigationScreen.Profile.route) {

                ProfileScreen(
                    profileViewModel = profileViewModel
                )
            }
    }

}
