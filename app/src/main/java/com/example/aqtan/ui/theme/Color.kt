package com.example.aqtan.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// background
val BackgroundDark = Color(0xFF001C30 )
val BackgroundLight = Color(0xFFDCDEDF)

// components
val ComponentBackgroundDark = Color(0xFF002B49)
val ComponentBackgroundLight = Color(0xFFF7F9FA)

val RedComponentColor = Color(0xFFcb1027)
val RedComponentColor3 = Color(0xFF7f0a21)


// text
val TextDark = Color(0xFFDEEAEE)
val TextLight =  Color(0xFF02070F)

val TextHintDark = Color(0xFFAAA9A9)
val TextHintLight = Color(0xFF6D6969)




val brushGray =  Brush.linearGradient(
    colors = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f)
    )
)

val brush2 =
    Brush.linearGradient(
        colors = listOf(
            ComponentBackgroundDark.copy(alpha = 0.6f),
            RedComponentColor.copy(alpha = 0.2f),
            ComponentBackgroundDark.copy(alpha = 0.6f),

            )
    )





@Composable
fun animatedShimmerColor(
    shimmerColors:List<Color>,
    durationMillis:Int = 2000
): Brush {

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )


}






