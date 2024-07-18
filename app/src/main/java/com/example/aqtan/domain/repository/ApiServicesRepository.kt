package com.example.aqtan.domain.repository

import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.Country
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import kotlinx.coroutines.flow.StateFlow

interface ApiServicesRepository {

    suspend fun getAllProducts():StateFlow<List<Product>>

    suspend fun getAllCategories():StateFlow<List<Category>>

    suspend fun getAllCountries():StateFlow<List<Country>>

    suspend fun getHomeProductsLists():StateFlow<List<HomeLists>>
    suspend fun getProductsListOfCategory(id: Int):StateFlow<List<Product>>
}