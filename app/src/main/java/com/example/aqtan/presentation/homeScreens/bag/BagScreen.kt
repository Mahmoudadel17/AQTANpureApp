package com.example.aqtan.presentation.homeScreens.bag

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aqtan.R
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.data.selectedProducts
import com.example.aqtan.presentation.components.AnimatedTextWithTileModes
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.CircleIconBackground
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.TextTitle
import com.example.aqtan.presentation.components.ViewImage

@Composable
fun BagScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top
    ){
        AnimatedTextWithTileModes(text = "My Bag", textFont = 50 )

        LazyColumn (
            modifier = Modifier.weight(1f)
        ){
            items(selectedProducts){
                BagCard(product = it, onMinusClick = { /*TODO*/ }, onAddClick = {}) {

                }
            }


        }
        Row (
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ){
            TextLabel(text = "Total amount: ", textFont = 16)
            Spacer(modifier = Modifier.weight(1f))
            TextLabel(text = "124", textFont = 22, textFontWight = FontWeight.Bold)
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

            }


        }


    }

}

@Composable
fun BagCard(
    product: Product,
    onMinusClick:()->Unit,
    onAddClick:()->Unit,
    onDeleteClick:()->Unit,
) {
    val context = LocalContext.current
    // Now can access resources using the context
    val resources = context.resources
    val isArabicLang = resources.configuration.locales[0].language == "ar"

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
            ViewImage(
                image = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-1170x780.jpg",
                modifier = Modifier
                    .weight(0.22f)
                    .height(110.dp),
                contentDescription = "Avoid image"
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
                        text =  product.enProductName.split(" ").take(3).joinToString(" "),
                        modifier = Modifier.padding(top = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textColor = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CircleIconBackground(
                        imageVector = Icons.Default.Delete,
                        modifier = Modifier
                            .background(Color.Transparent, CircleShape)
                            .clickable {
                                onDeleteClick()
                            }
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    )
                }


                // need to add product color and size
                // TODO:  need to add product color and size
                Row {
                    CircleIconBackground(
                        imageVector = Icons.Default.Remove,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                            .clickable {
                                onMinusClick()
                            }
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    )
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
                            .clickable {
                                onAddClick()
                            }
                            .shadow(elevation = 24.dp)
                        ,
                        iconColor = MaterialTheme.colorScheme.secondary,
                        iconSize = 40
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextTitle(
                        text =  product.price.toString(),
                        modifier = Modifier.padding(top = 10.dp),
                        textFontWight = FontWeight.Bold,
                        textColor = MaterialTheme.colorScheme.primary
                    )
                }

            }

        }
    }
}