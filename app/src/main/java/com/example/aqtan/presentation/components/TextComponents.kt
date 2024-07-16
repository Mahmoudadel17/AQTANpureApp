package com.example.aqtan.presentation.components




import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aqtan.R
import com.example.aqtan.ui.theme.ComponentBackgroundDark
import com.example.aqtan.ui.theme.RedComponentColor
import com.example.aqtan.ui.theme.brush2


@Composable
fun TextWithHiatusFont(
    text: String,
    modifier: Modifier = Modifier,
    textFont: Int = 26,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textFont.sp,
        color = textColor,
        fontWeight = FontWeight.Bold,
    )

}

@Composable
fun AppNameWithHiatusFont(
    modifier: Modifier = Modifier,
    fontSize:Int = 90,
    colorText:Color = Color(0xFF097AC7),
    showAiString:Boolean) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colorText)) {
                append("AQTAN")
            }
            if (showAiString){
                append(" ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("pure")
                }
            }

        },
        modifier = modifier,
        fontSize = fontSize.sp,
        fontFamily = FontFamily(Font(R.font.hiatus2)),
    )

}


@Composable
fun TextLabel(
    text: String,
    modifier: Modifier = Modifier,
    textFont: Int = 12,
    textFontWight: FontWeight = FontWeight.Normal,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textDecoration: TextDecoration = TextDecoration.None
) {

    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = textFont.sp,
        fontWeight = textFontWight,
        textDecoration = textDecoration
    )

}



@Composable
fun TextTitle(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    textFont: Int = 18,
    textFontWight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    textLetterSpacing: Double = 0.5,
    isJustify:Boolean = true
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textFont.sp,
        fontWeight = textFontWight,
        color = textColor,
        letterSpacing = textLetterSpacing.sp,
        overflow = TextOverflow.Ellipsis,
        textAlign = if (isJustify) TextAlign.Justify else null,
        maxLines = maxLines,
    )

}



@Composable
fun TextWithBoldUnderLine(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    textFont: Int = 22,
    textLetterSpacing: Double = 1.5,
    lineColor: Color = Color.Black,

    ) {
    Column {
        Text(
            text = text,
            modifier = modifier.padding(horizontal = 4.dp),
            fontSize = textFont.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            letterSpacing = textLetterSpacing.sp,
            fontFamily = FontFamily(Font(R.font.montserratb)),


            )
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .height(4.dp)
                .width(80.dp),
            color = lineColor
        )
    }
}


@Composable
fun TextWithBackgroundColorAsCard(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = MaterialTheme.colorScheme.primary,
    backgroundColor:Color ,
    textFont: Int = 22,
    textPadding:Int = 4,
    textFontWight: FontWeight = FontWeight.Normal,
    textLetterSpacing: Double = 1.5,
    cardCornerTopEnd:Int = 16,
    cardCornerTopStart:Int = 16,
    cardCornerBottomEnd:Int = 16,
    cardCornerBottomStart:Int = 16,
) {
    Card (
        modifier = modifier
            .padding(top = 10.dp, start = 10.dp)
        ,
        elevation = 16.dp,
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(
            topEnd = cardCornerTopEnd.dp,
            topStart = cardCornerTopStart.dp,
            bottomStart = cardCornerBottomStart.dp,
            bottomEnd = cardCornerBottomEnd.dp
        )
    ){
        Text(
            text = text,
            fontSize = textFont.sp,
            modifier = modifier.padding(horizontal = textPadding.dp),

            color = textColor,
            fontWeight = textFontWight,
            letterSpacing = textLetterSpacing.sp
        )

    }

}



@Composable
fun CustomChip(
    text:String,
    modifier: Modifier = Modifier,
    textColor:Color = Color.White,
    backgroundColor:Brush = brush2 ,
    textFont: Int = 14,
    textFontWight: FontWeight = FontWeight.Normal,
    textLetterSpacing: Double = 1.5,
    onIconClick:(String)->Unit
) {
    Card (
        modifier = modifier
            .padding(6.dp)
            .clip( RoundedCornerShape(16.dp))
            .background(backgroundColor)
        ,
        elevation = 16.dp,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(16.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = text,
                modifier = modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                fontSize = textFont.sp,
                color = textColor,
                fontWeight = textFontWight,
                letterSpacing = textLetterSpacing.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.HighlightOff,
                modifier = Modifier.size(30.dp).padding(end = 5.dp).clickable {
                    onIconClick(text)
                },
                contentDescription = "",
                tint = textColor
            )
        }
    }

}




@Composable
fun AnimatedTextWithTileModes(text:String,textFont:Int) {
    val tileMode by remember { mutableStateOf(TileMode.Clamp) }

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "animatedOffset"
    )

    val brush = remember(animatedOffset, tileMode) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * animatedOffset
                val heightOffset = size.height * animatedOffset
                return LinearGradientShader(
                    colors = listOf(
                        ComponentBackgroundDark.copy(alpha = 0.6f),
                        RedComponentColor.copy(alpha = 0.2f),
                        ComponentBackgroundDark.copy(alpha = 0.6f),

                        ),
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width, heightOffset + size.height),
                    tileMode = tileMode
                )
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            style = TextStyle(
                brush = brush,
                fontSize = textFont.sp,
                fontFamily = FontFamily(Font(R.font.hiatus2)),

                ),
        )


    }
}
