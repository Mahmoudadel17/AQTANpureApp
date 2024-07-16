package com.example.aqtan.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aqtan.ui.theme.BackgroundDark
import com.example.aqtan.ui.theme.ComponentBackgroundDark
import com.example.aqtan.ui.theme.RedComponentColor
import com.example.aqtan.ui.theme.animatedShimmerColor
import com.example.aqtan.ui.theme.brushGray


@Composable
fun ButtonClickOn(
    buttonText:String,
    modifier: Modifier = Modifier,
    buttonHeight: Int = 60,
    textFont:Int = 18,
    paddingValue:Int ,
    onButtonClick:() -> Unit ) {
    Button (
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = modifier
            .padding(top = paddingValue.dp)
            .height(buttonHeight.dp)
            .fillMaxWidth(1f)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                animatedShimmerColor(
                    shimmerColors = listOf(
                        BackgroundDark.copy(alpha = 0.6f),
                        ComponentBackgroundDark.copy(alpha = 0.5f),
                        RedComponentColor.copy(alpha = 0.8f),
                    )
                )
            ),


        ){
        Text(text = buttonText, fontSize = textFont.sp, style = TextStyle(color = Color.White))
    }
}



@Composable
fun ButtonClickOnBorder(
    buttonText:String,
    modifier: Modifier = Modifier,
    buttonHeight: Int = 60,
    paddingValueTop:Int ,
    paddingValueBottom:Int ,
    buttonColor:Color,
    textColor:Color,
    onButtonClick:() -> Unit ) {
    Button (
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent,),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = modifier
            .padding(top = paddingValueTop.dp)
            .padding(bottom = paddingValueBottom.dp)
            .height(buttonHeight.dp)
            .fillMaxWidth(1f)
            .shadow(elevation = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, brush = animatedShimmerColor(
                shimmerColors = listOf(
                    BackgroundDark.copy(alpha = 0.6f),
                    ComponentBackgroundDark.copy(alpha = 0.5f),
                    RedComponentColor.copy(alpha = 0.8f),
                )
            ), shape = RoundedCornerShape(16.dp))
            .background(buttonColor),


        ){
        Text(text = buttonText, fontSize = 18.sp, style = TextStyle(color = textColor))
    }
}



@Composable
fun BackIcon(onBackIconClick:()->Unit) {
    CircleIconBackground(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        modifier = Modifier.background(brush = brushGray, shape = CircleShape),
        contentDescription = "Arrow back"
    ){
        onBackIconClick()
    }
}


@Composable
fun CircleIconBackground(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription:String = "Icon",
    iconColor:Color = MaterialTheme.colorScheme.primary,
    iconSize:Int = 50,
    onBackIconClick:()->Unit = {}
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier
            .size(iconSize.dp)
            .padding(5.dp)
            .clickable {
                onBackIconClick()
            },
        tint = iconColor
    )
}