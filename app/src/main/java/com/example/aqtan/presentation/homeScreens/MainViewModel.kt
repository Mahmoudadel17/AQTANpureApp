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
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject



val allCategory = Category(id = -1, enName = "All", arName = "الكل")

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo:ApiServicesRepository
): ViewModel() {
    private val _tabList = MutableStateFlow(emptyList<Category>())
    val tabList: StateFlow<List<Category>> = _tabList

    // will get all products on init
    private val _productList = MutableStateFlow(emptyList<Product>())
    val productsList: StateFlow<List<Product>> = _productList

    // category list filter when user select another tab of all
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
                _tabList.value = listOf(allCategory)
                _tabList.value += it
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


    private fun getProductsCategoryList(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProductsListOfCategory(id).collect{
                _productListFiltered.value = it

            }
        }
    }

    fun onSelectingTab(category: Category) {
        getProductsCategoryList(category.id)
    }

    fun addToCart(product: Product){
        val currentList = _selectedListAddedToCart.value.toMutableList()
        currentList.add(product)
        _selectedListAddedToCart.value = currentList.toList()
    }

    fun deleteToCart(product: Product){
        val currentList = _selectedListAddedToCart.value.toMutableList()
        currentList.remove(product)
        _selectedListAddedToCart.value = currentList.toList()
    }

    fun addCountOfProduct(product: Product){
        product.count += 1
    }

    fun minusCountOfProduct(product: Product){
        if (product.count>1){
            product.count -= 1
        }
    }

    fun getTotalOrderAmount(selectedCountryCode:Int):Double{
        var totalAmount = 0.0
        _selectedListAddedToCart.value.forEach {product->
            val selectedPrice = product.prices.find { it.countryId == selectedCountryCode }
            selectedPrice?.let { price->
                totalAmount+=price.price
            }
        }
        return totalAmount
    }



}