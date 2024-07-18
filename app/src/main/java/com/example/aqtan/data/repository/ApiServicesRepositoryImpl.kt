package com.example.aqtan.data.repository

import com.example.aqtan.data.remote.ApiServices
import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.Country
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.domain.repository.ApiServicesRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ApiServicesRepositoryImpl  @Inject constructor(
    private val api: ApiServices
) : ApiServicesRepository {
    override suspend fun getAllProducts(): StateFlow<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCategories(): StateFlow<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCountries(): StateFlow<List<Country>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeProductsLists(): StateFlow<List<HomeLists>> {
        TODO("Not yet implemented")
    }
}