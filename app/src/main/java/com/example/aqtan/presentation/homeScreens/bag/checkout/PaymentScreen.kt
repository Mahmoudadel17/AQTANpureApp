package com.example.aqtan.presentation.homeScreens.bag.checkout

import com.example.keyboard.payment.CreditCardFilter
import com.example.keyboard.payment.InputItem
import com.example.keyboard.payment.PaymentCard
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.presentation.components.BackIcon
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.homeScreens.MainViewModel

@ExperimentalAnimationApi
@Composable
fun PaymentScreen(mainViewModel: MainViewModel,navController: NavHostController) {
    val state = mainViewModel.state.value
    val context  = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackIcon {
                navController.popBackStack()
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextLabel(
                    text = stringResource(R.string.add_payment_method),
                    textFont = 22
                )
            }

        }

        PaymentCard(
            state.cardHolderName,
            state.cardHolderNumber,
            state.expiryDate,
            state.cvc
        )
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            item {
                InputItem(
                    textFieldValue =  state.cardHolderName,
                    label = "Card holder name",
                    onTextChanged = { mainViewModel.onChangeCardHolderName(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            item {
                InputItem(
                    textFieldValue =  state.cardHolderNumber,
                    label = "Card holder number",
                    keyboardType = KeyboardType.Number,
                    onTextChanged = { mainViewModel.onChangeCardHolderNumber(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    visualTransformation = CreditCardFilter
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputItem(
                        textFieldValue =  state.expiryDate,
                        label = "Expiry date",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = {mainViewModel.onChangeExpiryNumber(it) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    InputItem(
                        textFieldValue =  state.cvc,
                        label = "CVC",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { mainViewModel.onChangeCvc(it) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        ButtonClickOn(
            buttonText = stringResource(R.string.complete_order),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            paddingValue = 0
        ) {
            mainViewModel.onCompleteOrder(navController, context )
        }
    }
}