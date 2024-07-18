package com.example.aqtan.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aqtan.R
import com.example.aqtan.ui.theme.brush2


@Composable
fun ViewImage(
    image:String,
    modifier: Modifier = Modifier,
    contentDescription:String = "",
) {


    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(brush = brush2)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(16.dp)
            )
        ,
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
    )

}


@Composable
fun ImageButtonClick(
    image:Int,
    modifier: Modifier = Modifier,
    paddingValue:Int,
    imageWidth: Int = 60,
    imageHeight: Int = 60,
    onButtonClick:() -> Unit
) {
    Image(
        painter = painterResource(id = image),
        modifier = modifier
            .clickable { onButtonClick() }
            .padding(paddingValue.dp)
            .width(imageWidth.dp)
            .height(imageHeight.dp)
        ,
        contentDescription = "image button",
    )

}

@Composable
fun CountryImage(link: String, modifier: Modifier) {
    AsyncImage(
        model = link,
        modifier = modifier
            .width(28.dp)
            .height(30.dp),
        contentDescription = "Country Image",
        error = painterResource(id = R.drawable.placeholder),
        placeholder = painterResource(id = R.drawable.placeholder),
    )
}