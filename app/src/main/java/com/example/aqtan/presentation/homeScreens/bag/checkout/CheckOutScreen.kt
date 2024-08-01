package com.example.aqtan.presentation.homeScreens.bag.checkout

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.presentation.components.BackIcon
import com.example.aqtan.presentation.components.ButtonClickOn
import com.example.aqtan.presentation.components.CheckboxWithName
import com.example.aqtan.presentation.components.EmailEditText
import com.example.aqtan.presentation.components.NumberEditText
import com.example.aqtan.presentation.components.StringEditText
import com.example.aqtan.presentation.components.TextLabel
import com.example.aqtan.presentation.components.UserNameEditText
import com.example.aqtan.presentation.homeScreens.MainViewModel


@Composable
fun CheckOutScreen(mainViewModel: MainViewModel,navController: NavHostController) {
    val state = mainViewModel.state.value
    val context = LocalContext.current

    LazyColumn {
        item {
            Column(
                modifier = Modifier.padding(12.dp),
            ) {
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
                            text = stringResource(R.string.checkout),
                            textFont = 26
                        )
                    }

                }
                TextLabel(
                    text = stringResource(R.string.shipping_address),
                    modifier = Modifier.padding(top = 10.dp),
                    textFont = 28,
                    textFontWight = FontWeight.Bold
                )
                TextLabel(
                    text = stringResource(R.string.enter_the_required_data_below),
                    modifier = Modifier.padding(top = 10.dp),
                    textFont = 18,
                    textColor = MaterialTheme.colorScheme.secondary
                )
                TextLabel(
                    text = stringResource(R.string.full_name),
                    modifier = Modifier.padding(top = 30.dp,bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                UserNameEditText(
                    userName = state.userName,
                    isUserNameError = state.isErrorUserName,
                    userNameErrorMessage = state.userNameErrorMessage,
                    editTextHeight = 50,
                    onValueChange = {newUserName-> mainViewModel.onUserNameChange(newUserName)}
                )


                TextLabel(
                    text = stringResource(R.string.email),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                EmailEditText(
                    email = state.email,
                    isErrorEmail = state.isErrorEmail,
                    emailErrorMessage = state.emailErrorMessage,
                    editTextHeight = 50,
                    onValueChange = {newEmail-> mainViewModel.onEmailChange(newEmail)}
                )


                TextLabel(
                    text = stringResource(R.string.phone),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                StringEditText(
                    textValue = state.phone,
                   placeholder =  stringResource(R.string.phone),
                    isTextValueError  = state.isErrorPhone,
                    textValueErrorMessage = state.phoneErrorMessage,
                    onValueChange = {newPhoneNumber-> mainViewModel.onPhoneNumberChange(newPhoneNumber)}
                )




                TextLabel(
                    text = stringResource(R.string.street_address),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                StringEditText(
                    textValue = state.streetAddress,
                    placeholder =  stringResource(R.string.street_address),
                    isTextValueError  = state.isErrorStreetAddress,
                    textValueErrorMessage = state.streetAddressErrorMessage,
                    onValueChange = {newStreetAddress-> mainViewModel.onStreetAddressChange(newStreetAddress)}
                )

                TextLabel(
                    text = stringResource(R.string.city),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                StringEditText(
                    textValue = state.city,
                    isTextValueError  = state.isErrorCity,
                    textValueErrorMessage = state.cityErrorMessage,
                    onValueChange = {newCityName-> mainViewModel.onCityChange(newCityName)}
                )


                TextLabel(
                    text = stringResource(R.string.zip_postal_code),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                NumberEditText(
                    number = state.postalCode,
                    placeholderID = R.string.empty_string,
                    editTextHeight = 50,
                    isNumberError = state.isPostalCode,
                    numberErrorMessage = state.postalCodeErrorMessage
                ) {newPostalCode-> mainViewModel.onPostalCodeChange(newPostalCode) }



                TextLabel(
                    text = stringResource(R.string.payment_method),
                    modifier = Modifier.padding(bottom = 10.dp),
                    textFont = 18,
                    textFontWight = FontWeight.Bold
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CheckboxWithName(
                        checkBoxText = stringResource(R.string.cash),
                        checkedState = state.cash,
                        onToggleClick = {mainViewModel.onCashSelect()}
                    )
                    CheckboxWithName(
                        checkBoxText = stringResource(R.string.visa),
                        checkedState = state.visa,
                        onToggleClick = {mainViewModel.onVisaSelect()}
                    )

                }
                Row {
                    Text(
                        state.paymentMethodErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                            .padding(top = 3.dp, start = 25.dp), color = Color.Red
                    )
                    Spacer(modifier = Modifier.weight(1f))

                }

                Spacer(modifier = Modifier.weight(1f))
                ButtonClickOn(
                    buttonText = if (state.visa) stringResource(R.string.next) else stringResource(R.string.complete_order),
                    buttonHeight = 50,
                    paddingValue = 0) {

                    mainViewModel.onCheckoutComplete(navController,context)
                }

            }
        }
    }


}