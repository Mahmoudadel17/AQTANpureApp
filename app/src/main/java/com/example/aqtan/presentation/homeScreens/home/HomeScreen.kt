package com.example.aqtan.presentation.homeScreens.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.aqtan.presentation.components.AnimatedTextWithTileModes
import com.example.aqtan.presentation.components.Product
import com.example.aqtan.presentation.components.ProductCardView
import com.example.aqtan.presentation.components.ScaleAndAlphaArgs
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.TextTitle
import com.example.aqtan.presentation.components.TextWithBackgroundColorAsCard
import com.example.aqtan.presentation.components.calculateDelayAndEasing
import com.example.aqtan.presentation.components.products
import com.example.aqtan.presentation.components.saleProducts
import com.example.aqtan.presentation.components.scaleAndAlpha
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.RedComponentColor3
import com.example.aqtan.ui.theme.brush2


data class HomeList(val list:List<Product>,val title:String,val largTitle:String)

val list = listOf(
    HomeList(saleProducts,"Sale","Super summer sale"),
    HomeList(products,"New","Your've never seen it before!")

)
@Composable
fun HomeScreen(
    navController: NavHostController,
    ) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        item {
            AnimatedTextWithTileModes(
                text = "Shop You",
                textFont = 70,
            )
            AnimatedTextWithTileModes(
                text = "    Home Need",
                textFont = 70,
            )

            list.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        TextLabel(
                            text = it.title,
                            textFont = 40,
                            textFontWight = FontWeight.Bold
                        )
                        TextLabel(
                            text = it.largTitle,
                            textFont = 16,
                            textColor = MaterialTheme.colorScheme.secondary
                            )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    TextLabel(
                        text = "View all",
                        textFont = 14,

                        modifier = Modifier.clickable {

                        }
                    )

                }
                val state = rememberLazyListState()
                LazyRow {
                    itemsIndexed(it.list){index,product->

                        val (delay, easing) = state.calculateDelayAndEasing(index, 1)
                        val animation = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
                        val args = ScaleAndAlphaArgs(fromScale = 2f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
                        val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)
                        ProductCardView(product = product, modifier = Modifier.width(230.dp), alpha = alpha, scale = scale) {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "product",
                                product
                            ) // new
                            navController.navigate(Screens.ProductDetails.route)

                        }

                    }
                }

            }

        }

    }
}