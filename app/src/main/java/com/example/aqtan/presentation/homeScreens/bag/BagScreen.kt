package com.example.aqtan.presentation.homeScreens.bag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.presentation.components.AnimatedTextWithTileModes
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.CircleIconBackground
import com.example.aqtan.presentation.components.LottieAnimationShow
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.TextTitle
import com.example.aqtan.presentation.components.ViewImage
import com.example.aqtan.presentation.components.applyDiscount
import com.example.aqtan.presentation.homeScreens.MainViewModel
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.dimens

@Composable
fun BagScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    selectedCountryCode:Int
) {
    val selectedProducts = mainViewModel.selectedListAddedToCart.collectAsState().value
    val state = mainViewModel.state.value

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top
    ){
        AnimatedTextWithTileModes(text = stringResource(R.string.my_bag), textFont = 50 )

        if (selectedProducts.isNotEmpty()){
            LazyColumn (
                modifier = Modifier.weight(1f)
            ){
                items(selectedProducts){
                    BagCard(
                        product = it,
                        selectedCountryCode = selectedCountryCode,
                        onMinusClick = { minusProduct-> mainViewModel.minusCountOfProduct(minusProduct,selectedCountryCode) },
                        onAddClick = {addProduct->mainViewModel.addCountOfProduct(addProduct,selectedCountryCode)}
                    ) {deleteProduct-> mainViewModel.deleteFromCart(deleteProduct,selectedCountryCode) }
                }

            }
        }else{
            Column (
                modifier = Modifier.weight(1f),
                ){
                LottieAnimationShow(
                    animationResId = R.raw.empty_bag,
                    size = MaterialTheme.dimens.animationSize,
                    padding = 0,
                    paddingBottom = 0
                )
            }
        }

        Row (
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ){
            TextLabel(text = "Order: ", textFont = 16)
            Spacer(modifier = Modifier.weight(1f))
            TextLabel(text = state.totalAmount.toString(), textFont = 22, textFontWight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .weight(0.1f)
                .padding(horizontal = 12.dp)
                .fillMaxSize()

        ) {
            ButtonClickOn(
                buttonText = stringResource(R.string.check_out),
                paddingValue = 0,
                buttonHeight = 45,
            ) {
                navController.navigate(Screens.Checkout.route)
            }


        }


    }

}

@Composable
fun BagCard(
    product: Product,
    selectedCountryCode:Int,
    onMinusClick:(Product)->Unit,
    onAddClick:(Product)->Unit,
    onDeleteClick:(Product)->Unit,
) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"

    val selectedPrice = product.prices.find { it.countryId == selectedCountryCode } ?: product.prices.firstOrNull()


    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)

        ){
//            https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-1170x780.jpg
            ViewImage(
                image = product.images[0]?:"",
                modifier = Modifier
                    .weight(0.22f)
                    .height(110.dp),
                contentDescription = "product image"
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.Start

            ){
                Row ( modifier = Modifier.fillMaxWidth()){
                    TextTitle(
                        text =  (if (isArabicLang) product.arProductName else product.enProductName).split(" ").take(3).joinToString(" "),
                        modifier = Modifier.padding(top = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textColor = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CircleIconBackground(
                        imageVector = Icons.Default.Delete,
                        modifier = Modifier
                            .background(Color.Transparent, CircleShape)
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    ){
                        onDeleteClick(product)
                    }
                }


                // need to add product color and size
                // TODO:  need to add product color and size
                Row {
                    CircleIconBackground(
                        imageVector = Icons.Default.Remove,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    ){
                        onMinusClick(product)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    TextTitle(
                        text =  product.count.toString(),
                        modifier = Modifier.padding(top = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textColor = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    CircleIconBackground(
                        imageVector = Icons.Default.Add,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    ){
                        onAddClick(product)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (product.isSale){
                        selectedPrice?.let {
                            if (isArabicLang)"${applyDiscount(it.price,product.salePercentage)}  ${it.arCurrencyName}"
                            else "${applyDiscount(it.price,product.salePercentage)}  ${it.enCurrencyName}"
                        }?.let {
                            TextTitle(
                                text = it,
                                modifier = Modifier.padding(top = 10.dp),
                                textFontWight = FontWeight.Bold,
                                textColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }else{
                        selectedPrice?.let {
                            if (isArabicLang)"${it.price}  ${it.arCurrencyName}"
                            else "${it.price}  ${it.enCurrencyName}"
                        }?.let {
                            TextTitle(
                                text = it,
                                modifier = Modifier.padding(top = 10.dp),
                                textFontWight = FontWeight.Bold,
                                textColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                }

            }

        }
    }
}