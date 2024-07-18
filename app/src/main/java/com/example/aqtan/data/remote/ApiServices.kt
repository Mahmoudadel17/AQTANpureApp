package com.example.aqtan.data.remote

import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.Country
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("categories.json")
    fun getCategories():Response<List<Category>>

    @GET("countries.json")
    fun getCountries():Response<List<Country>>

    @GET("homeLists.json")
    fun getHomeLists():Response<List<HomeLists>>


//    @Post
//    fun getCategoryProduct(@Query("categoryId") categoryID : Int):Response<List<Product>>
    @GET("categoryProducts.json")
    fun getCategoryProduct():Response<List<Product>>


//    @POST
//    suspend fun search( @Query("searchTerm") searchedItem : String): Response<List<Product>>




}