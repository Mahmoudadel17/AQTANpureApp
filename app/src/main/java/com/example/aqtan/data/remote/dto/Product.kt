package com.example.aqtan.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:Int,
    val images:List<String>,
    val enProductName:String,
    val arProductName:String,
    val enDescription:String,
    val arDescription:String,
    val prices:List<Price>,
    val salePercentage:Int,
    val isSale:Boolean,
    val isNew:Boolean,

    var count:Int = 1,

    ): Parcelable

