package com.example.keyboard.payment


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aqtan.R
import com.example.aqtan.ui.theme.ComponentBackgroundDark2
import com.example.aqtan.ui.theme.RedComponentColor
import com.example.aqtan.ui.theme.animatedShimmerColor

@ExperimentalAnimationApi
@Composable
fun PaymentCard(
    nameText:String,
    cardNumber: String,
    expiryNumber: String,
    cvcNumber: String
) {
    var backVisible by remember { mutableStateOf(false) }
//    var visaType by remember { mutableStateOf(CardType.None) }

    val length = if (cardNumber.length > 16) 16 else cardNumber.length
    val initial = remember { "*****************" }
        .replaceRange(0..length, cardNumber.take(16))

    if (cvcNumber.length == 1 && !backVisible) {
        backVisible = true
    } else if (cvcNumber.length == 2) {
        backVisible = true
    } else if (cvcNumber.length == 3) {
        backVisible = false
    }


    Box {
        Surface(
            shape = RoundedCornerShape(25.dp),

            modifier = Modifier
                .padding(16.dp).fillMaxWidth().height(220.dp)
                .graphicsLayer(
                    rotationY = animateFloatAsState(if (backVisible) 180f else 0f).value,
                    translationY = 0f
                ).clickable {
                    backVisible = !backVisible
                },
            color = Color.Transparent,
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    animatedShimmerColor(
                    listOf(
                        ComponentBackgroundDark2.copy(alpha = 0.2f),
                        RedComponentColor.copy(alpha = 0.3f),
                        ComponentBackgroundDark2.copy(alpha = 0.6f),

                        ),
                    3000
                ))) {
                AnimatedVisibility(visible = !backVisible) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (symbol, logo, cardName, cardNameLabel, number, expiry, expiryLabel) = createRefs()

                        Image(
                            painter = painterResource(
                                id = R.drawable.card_symbol
                            ),
                            contentDescription = "symbol",
                            modifier = Modifier.padding(20.dp).constrainAs(symbol) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                        )

                        AnimatedVisibility(visible = true,
                            modifier = Modifier.padding(20.dp).constrainAs(logo) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.bank_logo
                                ),
                                modifier = Modifier.size(45.dp),
                                contentDescription = "symbol"
                            )
                        }

                        Text(
                            text = initial.chunked(4).joinToString(" "),
                            style = MaterialTheme.typography.h5,
                            maxLines = 1,
                            color = Color(0xFFFFD700),
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .constrainAs(number) {
                                    linkTo(
                                        start = parent.start,
                                        end = parent.end
                                    )
                                    linkTo(
                                        top = parent.top,
                                        bottom = parent.bottom
                                    )
                                }
                        )

                        Text(
                            text = "CARD HOLDER",
                            style = MaterialTheme.typography.caption,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .constrainAs(cardNameLabel) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(cardName.top)
                                }
                        )
                        Text(
                            text = nameText,
                            style = MaterialTheme.typography.h6,
                            color = Color(0xFFFFD700),
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(start = 16.dp, bottom = 16.dp)
                                .constrainAs(cardName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                        )

                        Text(
                            text = "Expiry",
                            style = MaterialTheme.typography.caption,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .constrainAs(expiryLabel) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(expiry.top)
                                },

                        )
                        Text(
                            text = expiryNumber.take(4).chunked(2).joinToString(" / "),
                            style = MaterialTheme.typography.h6,
                            color = Color(0xFFFFD700),
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(end = 16.dp, bottom = 16.dp)
                                .constrainAs(expiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }

                AnimatedVisibility(visible = backVisible) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val (backScanner) = createRefs()
                        Spacer(
                            modifier = Modifier.height(50.dp).background(
                                Color.Black
                            ).fillMaxWidth().constrainAs(backScanner) {
                                linkTo(
                                    top = parent.top,
                                    bottom = parent.bottom
                                )
                            }
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = backVisible,
            modifier = Modifier
                .padding(end = 50.dp, bottom = 50.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cvcNumber,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(vertical = 4.dp, horizontal = 16.dp)

                )
            }

        }
    }
}
//
//enum class CardType(
//    val title: String,
//    @DrawableRes val image: Int,
//) {
//    None("", R.drawable.ic_visa_logo),
//    Visa("visa", R.drawable.ic_visa_logo),
//}