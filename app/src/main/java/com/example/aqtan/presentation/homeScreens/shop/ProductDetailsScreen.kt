package com.example.aqtan.presentation.homeScreens.shop

import androidx.compose.animation.ExperimentalSharedTransitionApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.aqtan.R
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.presentation.components.BackIcon
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.TextTitle
import com.example.aqtan.ui.theme.RedComponentColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailsScreen(
    product: Product,
    navController: NavHostController,
) {
//    DetailsImageCard
    val pagerState = rememberPagerState()


    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp)
        ) {
            BackIcon {
                navController.popBackStack()
            }

        }
        LazyColumn (
            modifier = Modifier.weight(1f)
        ){
            item {
                HorizontalPager(
                    count = product.images.size,
                    state = pagerState,
                    modifier = Modifier
                        .height(340.dp)
                        ,
                ){index->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp)
                    ){

                        AsyncImage(
                            model = product.images[index],
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds
                            , modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                        Spacer(modifier = Modifier.width(40.dp))
                        Row(

                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            (0..<product.images.size).forEach {
                                Box(modifier = Modifier
                                    .size(15.dp)
                                    .clip(CircleShape)
                                    .shadow(12.dp, CircleShape)
                                    .padding(4.dp)
                                    .background(if (it == index) RedComponentColor else MaterialTheme.colorScheme.secondary)
                                )
                            }
                        }

                    }



                }

                Column(
                    modifier = Modifier
                        .padding(12.dp)
                ) {

                    Row (
                        verticalAlignment = Alignment.CenterVertically

                    ){
                        TextLabel(
                            text = product.enProductName,
                            modifier = Modifier.weight(0.8f),
                            textFont = 22
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        TextLabel(
                            text = product.price.toString(),
                            modifier = Modifier.weight(0.2f),
                            textFont = 26,
                            textFontWight = FontWeight.Bold,
                            textColor = RedComponentColor
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    TextTitle(
                        text = product.arDescription,
                        textFont = 22,
                        maxLines = 500,

                    )


                }
            }
        }
        Row(
            modifier = Modifier
                .weight(0.1f)
                .padding(horizontal = 12.dp)
                .fillMaxSize()

        ) {

            ButtonClickOn(
                buttonText = stringResource(R.string.add_to_cart),
                paddingValue = 0,
                buttonHeight = 45,
            ) {

            }

        }


    }
}