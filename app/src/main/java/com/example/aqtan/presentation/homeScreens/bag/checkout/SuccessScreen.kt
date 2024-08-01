package com.example.aqtan.presentation.homeScreens.bag.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.LottieAnimationShow
import com.example.aqtan.presentation.components.TextWithBoldUnderLine
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.dimens
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SuccessScreen(navController: NavHostController,) {

    Column (
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = R.raw.order_confirm,
                size = MaterialTheme.dimens.animationSize,
                padding = 0,
                paddingBottom = 0
            )
        }

        ButtonClickOn(
            buttonText = "CONTINUE SHOPPING",
            buttonHeight = 45,
            paddingValue = 0) {
            // got to home screen
            navController.navigate(Screens.Home.route){
                popUpTo(Screens.Intro.route) {
                    inclusive = true
                }
            }
        }
    }

}

