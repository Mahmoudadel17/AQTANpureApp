package com.example.aqtan.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Price(
    val price:Double,
    val enCurrencyName:String,
    val arCurrencyName:String,
    val countryId:Int
):Parcelable
