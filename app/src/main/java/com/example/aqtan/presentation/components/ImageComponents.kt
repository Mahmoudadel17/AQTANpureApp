package com.example.aqtan.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


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