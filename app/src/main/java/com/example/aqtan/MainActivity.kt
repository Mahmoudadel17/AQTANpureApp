package com.example.aqtan

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.aqtan.presentation.components.SplashScreen
import com.example.aqtan.presentation.homeScreens.profile.ProfileScreenViewModel
import com.example.aqtan.presentation.navigation.AppNavigation
import com.example.aqtan.ui.theme.AQTANTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val profileViewModel by viewModels<ProfileScreenViewModel>()


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check if this first time to open application or not
        val isIntroScreenShow = profileViewModel.isFirstTimeOpenApp()

        // Now can access resources using the context
        val resources = this.resources

        // make app language like language in SharedPreferences or system if first time
        profileViewModel.onCreateChangeLanguage(
            resources = resources,
            systemLanguage = getCurrentLanguage()
        )



        setContent {
            // this state manage profile screen that contain Dark mode and language
            // and need the state here to change app directly on change any of them
            val state = profileViewModel.state.value
            AQTANTheme(darkTheme = state.isDark) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Get the layout direction based on view model state to make change directly
                    val layoutDirection =  if (state.isRtlDirection) LayoutDirection.Rtl else LayoutDirection.Ltr

                    // Set layout direction, all app screens inside CompositionLocalProvider
                    // to change it if language changed
                    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                        // Boolean var to open splash screen first for 4 seconds then go to home or intro
                        var isSplashScreenVisible by remember { mutableStateOf(true) }


                        if (isSplashScreenVisible) {
                            SplashScreen()
                        } else {
                            AppNavigation(
                                isIntro = isIntroScreenShow,
                                profileViewModel = profileViewModel,
                            )
                        }
                        // make delay for 4 second then start App Navigation
                        LaunchedEffect(Unit) {
                            delay(4.seconds)
                            isSplashScreenVisible = false
                        }

                    }
                }
            }
        }
    }


    private fun getCurrentLanguage(): String {
        val configuration = Resources.getSystem().configuration
        return configuration.locales[0].language
    }
}
