package com.example.aqtan.presentation.homeScreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.presentation.components.BackIcon
import com.example.aqtan.presentation.components.ProductsGridList
import com.example.aqtan.presentation.components.TextLabel


@Composable
fun AllProductsScreen(
    navController:NavHostController,
    allProducts: HomeLists,
    selectedCountryCode:Int
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon {
                navController.popBackStack()
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                TextLabel(text = allProducts.enTitle, textFont = 24)

            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        ProductsGridList(
            products = allProducts.productList,
            selectedCountryCode = selectedCountryCode,
            navController = navController
        )
    }

}