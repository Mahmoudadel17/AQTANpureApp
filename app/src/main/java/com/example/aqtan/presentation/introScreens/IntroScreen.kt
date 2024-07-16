package com.example.aqtan.presentation.introScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.presentation.components.AppNameWithHiatusFont
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.LottieAnimationShow
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.TextWithBoldUnderLine
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.RedComponentColor
import com.example.aqtan.ui.theme.dimens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()

    val introScreenList = listOf(
        IntroDataScreen(
            title = stringResource(R.string.purchase_online),
            description = stringResource(
            R.string.discover_a_vast_selection_of_products_available_for_easy_online_shopping),
            animationId = R.raw.find_order
        ),
        IntroDataScreen(
            title = stringResource(R.string.order_delivery),
            description = stringResource(R.string.enjoy_fast_and_reliable_delivery_right_to_your_doorstep),
            animationId = R.raw.wait_order
        ),
        IntroDataScreen(
            title = stringResource(R.string.get_your_order),
            description = stringResource(R.string.receive_your_order_quickly_and_enjoy_your_new_purchase),
            animationId = R.raw.get_order
        ),
    )


    Column (
        modifier = Modifier
        .fillMaxSize(),
    verticalArrangement = Arrangement.Bottom
    ){

        HorizontalPager(
            count = introScreenList.size,
            state = pagerState,
        ){index->
            IntroScreenContent(
                pagerState = pagerState,
                navController = navController,
                count = introScreenList.size-1,
                index = index,
                title = introScreenList[index].title,
                introScreenList[index].animationId,
                description = introScreenList[index].description
            )


        }
    }

}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreenContent(
    pagerState: PagerState,
    navController: NavHostController,
    count: Int,
    index: Int,
    title: String,
    animationId:Int,
    description: String,
) {
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (modifier = Modifier
            .fillMaxWidth()){
            TextWithBoldUnderLine(
                text = "AQTAN pure",
                textFont = 30,
                lineColor = MaterialTheme.colorScheme.secondary
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            LottieAnimationShow(
                animationResId = animationId,
                size = MaterialTheme.dimens.animationSize,
                padding = 0,
                paddingBottom = 0
            )
        }
        TextLabel(
            text = title,
            textFont = 28
        )
        TextLabel(
            text = description,
            modifier = Modifier.padding(horizontal = 30.dp),
            textFont = 18
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            (0..count).forEach {
                Box(modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .shadow(12.dp, CircleShape)
                    .padding(4.dp)
                    .background(if (it == index) RedComponentColor else MaterialTheme.colorScheme.secondary)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (index == count){
            ButtonClickOn(
                buttonText = stringResource(R.string.get_started),
                buttonHeight = 45,
                paddingValue = 0) {
                // got to home screen
                navController.navigate(Screens.Home.route){
                    popUpTo(Screens.Intro.route) {
                        inclusive = true
                    }
                }
            }
        }else{
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)) {
                ButtonClickOn(
                    buttonText = stringResource(R.string.skip),
                    modifier = Modifier.weight(0.4f) ,
                    buttonHeight = 45,
                    paddingValue = 0
                ) {
                    // got to home screen
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Intro.route) {
                            inclusive = true
                        }
                    }

                }
                Spacer(modifier = Modifier.weight(0.2f))
                ButtonClickOn(
                    buttonText = stringResource(R.string.next),
                    modifier = Modifier.weight(0.4f),
                    buttonHeight = 45,
                    paddingValue = 0
                ) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index+1)
                    }
                }
            }
        }
        
    }

}

