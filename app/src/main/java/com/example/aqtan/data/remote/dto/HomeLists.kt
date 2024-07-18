package com.example.aqtan.data.remote.dto

data class HomeLists(
    val id:Int,
    val productList:List<Product>,
    val enTitle:String,
    val arTitle:String,
    val arLargeTitle:String,
    val enLargeTitle:String,
)

