package com.example.aqtan.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.dimens


@Composable
fun SuccessScreen(navController: NavHostController ){
    Column {
        Column (
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                LottieAnimationShow(
                    animationResId = R.raw.order_confirm ,
                    modifier = Modifier.padding(top = 80.dp),
                    size = MaterialTheme.dimens.animationSize,
                    padding = 0,
                    paddingBottom = 0
                )
            }
            TextLabel(
                text = stringResource(R.string.your_order_will_be_delivered_soon),
                textFont = 18
            )
            TextLabel(
                text = stringResource(R.string.thank_you_for_choosing_our_app),
                textFont = 18
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonClickOn(
                buttonText = stringResource(R.string.continue_shopping),
                buttonHeight = 45,
                paddingValue = 0) {
                // got to home screen
                navController.navigate(Screens.Home.route){
                    popUpTo(Screens.Success.route) {
                        inclusive = true
                    }
                }
            }

        }
    }
}