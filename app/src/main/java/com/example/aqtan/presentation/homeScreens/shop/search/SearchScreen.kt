package com.example.aqtan.presentation.homeScreens.shop.search


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aqtan.presentation.components.ProductsGridList
import com.example.aqtan.presentation.components.SearchAppBar

@Composable
fun SearchScreen(
    navController: NavHostController,
    selectedCountryCode:Int
) {
    val searchScreenViewModel: SearchScreenViewModel = hiltViewModel()

    val searchQuery = searchScreenViewModel.searchQuery.collectAsState().value
    val searchedItems = searchScreenViewModel.searchedItems.collectAsState().value

    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ){
            SearchAppBar(
                text = searchQuery,
                enable = true,
                onTextChange = {searchScreenViewModel.onChangeSearchQuery(it)},
                onSearchClicked = {searchScreenViewModel.onIconSearchClick()},
            )
        }
        ProductsGridList(
            products = searchedItems,
            selectedCountryCode = selectedCountryCode,
            navController = navController
        )
    }
}