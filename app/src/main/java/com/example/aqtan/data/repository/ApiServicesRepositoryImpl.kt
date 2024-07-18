package com.example.aqtan.data.repository

import android.util.Log
import com.example.aqtan.data.remote.ApiServices
import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.Country
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.domain.repository.ApiServicesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ApiServicesRepositoryImpl  @Inject constructor(
    private val api: ApiServices
) : ApiServicesRepository {
    override suspend fun getAllProducts(): StateFlow<List<Product>> {
        val productsList = MutableStateFlow(emptyList<Product>())
        try {
            val response = api.getCategoryProduct().await()

            if (response.isSuccessful){
                response.body()?.let {
                    productsList.value = it
                    return productsList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return productsList
        }

        return productsList
    }

    override suspend fun getAllCategories(): StateFlow<List<Category>> {
        val categoriesList = MutableStateFlow(emptyList<Category>())
        try {
            val response = api.getCategories().await()

            if (response.isSuccessful){
                response.body()?.let {
                    categoriesList.value = it
                    return categoriesList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return categoriesList
        }

        return categoriesList
    }

    override suspend fun getAllCountries(): StateFlow<List<Country>> {
        val countriesList = MutableStateFlow(emptyList<Country>())
        try {
            val response = api.getCountries().await()

            if (response.isSuccessful){
                response.body()?.let {
                    countriesList.value = it
                    return countriesList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return countriesList
        }

        return countriesList
    }

    override suspend fun getHomeProductsLists(): StateFlow<List<HomeLists>> {
        val homeLists = MutableStateFlow(emptyList<HomeLists>())
        Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
        try {
            Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api 1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

            val response = api.getHomeLists().await()
            Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api 2<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

            if (response.isSuccessful){
                Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api 3<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

                response.body()?.let {
                    Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api 4<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

                    homeLists.value = it
                    Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api 5<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

                    return homeLists
                }
            }
        }catch (e: Exception) {
            Log.d("Tag",">>>>>>>>>>>>>>>>>>>>>> home api error <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")

            e.printStackTrace()
            return homeLists
        }

        return homeLists
    }

    override suspend fun getProductsListOfCategory(id: Int): StateFlow<List<Product>> {
        val productsList = MutableStateFlow(emptyList<Product>())
        try {
            // we will make api end point take category id -1 or actual category id
            val response = api.getCategoryProduct().await()

            if (response.isSuccessful){
                response.body()?.let {
                    productsList.value = it
                    return productsList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return productsList
        }

        return productsList
    }

    override suspend fun searchForProduct(query: String): StateFlow<List<Product>> {
        val productsList = MutableStateFlow(emptyList<Product>())
        try {
            // we will make api end point to search for products
            val response = api.getCategoryProduct().await()

            if (response.isSuccessful){
                response.body()?.let {
                    productsList.value = it
                    return productsList
                }
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return productsList
        }

        return productsList
    }
}