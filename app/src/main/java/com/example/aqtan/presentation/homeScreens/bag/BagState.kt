package com.example.aqtan.presentation.homeScreens.bag

import androidx.compose.ui.text.input.TextFieldValue

data class BagState(
    val userName:String = "",
    val isErrorUserName:Boolean = false,
    val userNameErrorMessage:String = "",

    val email:String = "",
    val isErrorEmail:Boolean = false,
    val emailErrorMessage:String = "",

    val phone:String = "",
    val isErrorPhone:Boolean = false,
    val phoneErrorMessage:String = "",


    val streetAddress:String = "",
    val isErrorStreetAddress:Boolean = false,
    val streetAddressErrorMessage:String = "",

    val city:String = "",
    val isErrorCity:Boolean = false,
    val cityErrorMessage:String = "",

    val postalCode:String = "",
    val isPostalCode:Boolean = false,
    val postalCodeErrorMessage:String = "",

    val cardHolderName: String = "" ,
    val cardHolderNumber:String = "",
    val expiryDate:String = "",
    val cvc:String = "",

    val cash:Boolean = false,
    val visa:Boolean = false,
    val paymentMethodErrorMessage:String = "",

    val totalAmount:Double = 0.0
)
