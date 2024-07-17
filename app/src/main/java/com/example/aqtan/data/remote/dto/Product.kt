package com.example.aqtan.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:Int,
    val images:List<String>,
    val productName:String,
    val description:String,
    val price:Double,
    val oldPrice:Double = 167.5,
    val sealNumber:Int = 15,
    val isSale:Boolean = false,
    val isNew:Boolean = true,

    ): Parcelable

