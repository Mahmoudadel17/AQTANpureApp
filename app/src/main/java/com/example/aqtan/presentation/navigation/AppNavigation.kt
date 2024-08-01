package com.example.aqtan.presentation.navigation



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.presentation.MainScreen
import com.example.aqtan.presentation.components.SuccessScreen
import com.example.aqtan.presentation.homeScreens.MainViewModel
import com.example.aqtan.presentation.homeScreens.bag.BagScreen
import com.example.aqtan.presentation.homeScreens.bag.checkout.CheckOutScreen
import com.example.aqtan.presentation.homeScreens.bag.checkout.PaymentScreen
import com.example.aqtan.presentation.homeScreens.home.AllProductsScreen
import com.example.aqtan.presentation.homeScreens.home.HomeScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreenViewModel
import com.example.aqtan.presentation.homeScreens.shop.ProductDetailsScreen
import com.example.aqtan.presentation.homeScreens.shop.ShopScreen
import com.example.aqtan.presentation.homeScreens.shop.search.SearchScreen
import com.example.aqtan.presentation.introScreens.IntroScreen


@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation(
    isIntro: Boolean,
    profileViewModel: ProfileScreenViewModel,
    mainViewModel: MainViewModel,
) {
        val navController = rememberNavController()
         val homeLists = mainViewModel.homeLists.collectAsState().value

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
                    mainViewModel = mainViewModel,
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
                        mainViewModel = mainViewModel,
                        navController = navController,
                        selectedCountryCode = profileViewModel.state.value.countryCode
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
                    newList = homeLists.find {currList-> currList.id == listID }
                }
                newList?.let {homeList->
                    AllProductsScreen(
                        navController = navController,
                        allProducts = homeList,
                        selectedCountryCode = profileViewModel.state.value.countryCode ,
                        )

                }

            }
            composable(route = Screens.Search.route) {
                SearchScreen(
                    navController = navController,
                    selectedCountryCode = profileViewModel.state.value.countryCode ,
                    )
            }


            composable(route = Screens.Checkout.route) {
                CheckOutScreen(mainViewModel = mainViewModel, navController = navController)
            }

            composable(route = Screens.Payment.route) {
                PaymentScreen(mainViewModel = mainViewModel, navController = navController)

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
    mainViewModel: MainViewModel,
    ) {
        NavHost(
            navController = bottomNavController,
            startDestination = NavigationScreen.Home.route
        ) {
            composable(route = NavigationScreen.Home.route) {
                HomeScreen(
                    navController = appNavController,
                    mainViewModel = mainViewModel,
                    selectedCountryCode = profileViewModel.state.value.countryCode ,

                    )
            }
            composable(route = NavigationScreen.Shop.route) {
                ShopScreen(
                    navController =  appNavController,
                    mainViewModel = mainViewModel,
                    selectedCountryCode = profileViewModel.state.value.countryCode ,
                )
            }

            composable(route = NavigationScreen.Bag.route) {
                BagScreen(
                    navController = appNavController,
                    mainViewModel = mainViewModel,
                    selectedCountryCode = profileViewModel.state.value.countryCode
                    )
            }
            composable(route = NavigationScreen.Profile.route) {
                ProfileScreen(
                    profileViewModel = profileViewModel,
                    mainViewModel = mainViewModel
                    )
            }
    }

}
