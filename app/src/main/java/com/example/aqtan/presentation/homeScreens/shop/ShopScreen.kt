package com.example.aqtan.presentation.homeScreens.shop






import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aqtan.presentation.components.ProductsGridList
import com.example.aqtan.presentation.components.SearchAppBar
import com.example.aqtan.presentation.homeScreens.MainViewModel
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.RedComponentColor3
import com.example.aqtan.ui.theme.TextDark
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShopScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    selectedCountryCode:Int
){

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabItems = mainViewModel.tabList.collectAsState().value
    val products = mainViewModel.productsList.collectAsState().value
    val filteredProducts = mainViewModel.productsListFiltered.collectAsState().value



    Column (
        modifier = Modifier
            .fillMaxSize(),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            SearchAppBar(
                text = "",
                onClicked = {
                    // go to search screen
                    navController.navigate(Screens.Search.route)
                },
            )
        }
        //  Box Contain HorizontalPager and ScrollableTabRow
        Box(modifier = Modifier){
            HorizontalPager(
                count = tabItems.size, state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,

                ) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                ) {
                    // check if current tab is all or filter.
                    if (tabItems[index].id==-1){
                        // Show MoviesGridList() when shimmerVisible is false or list not empty
                        ProductsGridList(
                            products = products,
                            selectedCountryCode = selectedCountryCode ,
                            navController = navController
                        )

                    }
                    else{
                        // Show MoviesGridList() when shimmerVisible is false or list not empty
                        ProductsGridList(
                            products = filteredProducts,
                            selectedCountryCode = selectedCountryCode ,
                            navController = navController
                        )

                    }

                }
            }


            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .padding(5.dp)
                    .background(color = Color.Transparent),
                edgePadding = 0.dp,
                backgroundColor = MaterialTheme.colorScheme.background,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(
                                pagerState,
                                tabPositions
                            )
                            .width(0.dp)
                            .height(0.dp)
                    )
                }
            ) {
                tabItems.forEachIndexed { index, category ->


                    val color = remember {
                        Animatable(RedComponentColor3)
                    }
                    LaunchedEffect(pagerState.currentPage == index) {
                        color.animateTo(if (pagerState.currentPage == index) RedComponentColor3 else Color.Black)
                    }
                    Tab(
                        text = {
                            Text(
                                category.enName,
                                style = TextStyle(
                                    color = TextDark,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        selected = pagerState.currentPage == index,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .height(35.dp)
                            .background(
                                color = color.value,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }

                        })
                }

            }

        }
    }


}
