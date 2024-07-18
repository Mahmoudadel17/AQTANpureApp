package com.example.aqtan.presentation

import android.os.Build
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aqtan.R
import com.example.aqtan.presentation.homeScreens.MainViewModel
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreenViewModel
import com.example.aqtan.presentation.navigation.BottomNavigation
import com.example.aqtan.presentation.navigation.NavigationScreen
import com.example.aqtan.ui.theme.RedComponentColor


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainScreen(
    appNavController: NavHostController,
    profileViewModel: ProfileScreenViewModel,
    mainViewModel: MainViewModel,
) {
    // use it when user need to exit app to show toast to click again to exit.
    var doubleBackToExitPressedOnce = false

    // use it to close activity if user click he want to close app.
    val activity = LocalOnBackPressedDispatcherOwner.current as ComponentActivity
    val context = LocalContext.current


    // navController for bottom navigation
    val navController = rememberNavController()

    // list of Bottom navigation and take in your mind the middle
    // navigation item is floating action button, so (it not in list of screens).
    val screens = listOf(
        NavigationScreen.Home,
        NavigationScreen.Shop,
        NavigationScreen.Bag,
        NavigationScreen.Profile
    )

    val screensName = listOf(
        stringResource(R.string.home),
        stringResource(R.string.shop),
        stringResource(R.string.bag),
        stringResource(R.string.profile)
    )

    // back stack & currentRoute to handel route of bottom navigation for user
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .height(60.dp)
            ) {
                screens.forEachIndexed { idx, screen->

                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Icon(
                                    painter = if(screen.route == currentRoute)  painterResource(id = screen.selectedIcon)
                                    else painterResource(id = screen.icon) ,
                                    contentDescription = "bottom nav icon",
                                    tint = if(screen.route == currentRoute) RedComponentColor
                                    else MaterialTheme.colorScheme.secondary

                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = screensName[idx],
                                    fontSize = 16.sp,
                                    modifier = Modifier,
                                    color = if(screen.route == currentRoute) RedComponentColor
                                    else MaterialTheme.colorScheme.secondary,
                                    softWrap = false,
                                )

                            }
                        }
                    )
                }

            }
        }

    ) {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(it)){
            BottomNavigation(
                bottomNavController = navController,
                appNavController = appNavController,
                profileViewModel = profileViewModel,
                mainViewModel = mainViewModel,
                )
        }



        //Back Handler
        BackHandler(onBack = {
            // show toast to user to click again if need to exit and make
            // make time 2 seconds then reassign doubleBackToExitPressedOnce to false
            if (doubleBackToExitPressedOnce) {
                finishAffinity(activity)
            }
            else {
                doubleBackToExitPressedOnce = true
                Toast.makeText(context,
                    context.getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }
        })
    }

}