package com.example.aqtan.presentation.homeScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.domain.repository.ApiServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo:ApiServicesRepository
): ViewModel() {
    private val _tabList = MutableStateFlow(emptyList<Category>())
    val tabList: StateFlow<List<Category>> = _tabList

    // will get all products on init
    private val _productList = MutableStateFlow(emptyList<Product>())
    val productsList: StateFlow<List<Product>> = _productList

    // category list filter
    private val _productListFiltered = MutableStateFlow(emptyList<Product>())
    val productsListFiltered: StateFlow<List<Product>> = _productListFiltered


    private val _homeLists = MutableStateFlow(emptyList<HomeLists>())
    val homeLists: StateFlow<List<HomeLists>> = _homeLists


    // selected list added to cart
    private val _selectedListAddedToCart = MutableStateFlow(emptyList<Product>())
    val selectedListAddedToCart: StateFlow<List<Product>> = _selectedListAddedToCart


    init {
        getCategoriesList()
        getAllHomeLists()
        getAllProducts()
    }

    private fun getCategoriesList(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllCategories().collect{
                _tabList.value =  it
            }
        }
    }

    private fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllProducts().collect{
                _productList.value = it
            }

        }
    }

    private fun getAllHomeLists() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getHomeProductsLists().collect{
                _homeLists.value = it
            }

        }
    }


    fun addToCart(product: Product){
        _selectedListAddedToCart.toM
    }






}