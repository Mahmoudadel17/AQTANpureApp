package com.example.aqtan.presentation.homeScreens

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqtan.R
import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.domain.repository.ApiServicesRepository
import com.example.aqtan.presentation.components.applyDiscount
import com.example.aqtan.presentation.homeScreens.bag.BagState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


val allCategory = Category(id = -1, enName = "All", arName = "الكل")

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo:ApiServicesRepository
): ViewModel() {
    private var _state by mutableStateOf(
        BagState()
    )

    val state: State<BagState>
        get() = derivedStateOf { _state }







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

    fun addToCart(product: Product,context: Context, selectedCountryCode: Int): String {
        // Check if product already exists in _selectedListAddedToCart
        if (_selectedListAddedToCart.value.any { it.id == product.id }) {
            return context.getString(R.string.product_already_in_list)
        }

        // If not in list, add the product and update total amount
        val currentList = _selectedListAddedToCart.value.toMutableList()
        product.count+=1
        currentList.add(product)
        _selectedListAddedToCart.value = currentList.toList()

        getTotalOrderAmount(selectedCountryCode) // Update total order amount

        return  context.getString(R.string.product_added_successfully)
    }


    fun deleteFromCart(product: Product, selectedCountryCode:Int){
        val currentList = _selectedListAddedToCart.value.toMutableList()
        currentList.remove(product)
        _selectedListAddedToCart.value = currentList.toList()
        getTotalOrderAmount(selectedCountryCode)


    }

    fun addCountOfProduct(product: Product,selectedCountryCode:Int){
        val updatedList = _selectedListAddedToCart.value.map { cd ->
            if (cd.id == product.id) product.copy(
                count  = product.count + 1
            )
            else cd
        }
        _selectedListAddedToCart.value = updatedList.toMutableList()
        getTotalOrderAmount(selectedCountryCode)

    }

    fun minusCountOfProduct(product: Product,selectedCountryCode:Int){
        if (product.count>1){
            val updatedList = _selectedListAddedToCart.value.map { cd ->
                if (cd.id == product.id) product.copy(
                    count  = product.count - 1
                )
                else cd
            }
            _selectedListAddedToCart.value = updatedList.toMutableList()

        }
        getTotalOrderAmount(selectedCountryCode)
    }



    private fun getTotalOrderAmount(selectedCountryCode:Int) {
        var totalAmount = 0.0
        _selectedListAddedToCart.value.forEach {product->
            val selectedPrice = product.prices.find { it.countryId == selectedCountryCode } ?: product.prices.firstOrNull()
            selectedPrice?.let { price->
                totalAmount += if (product.isSale){
                    applyDiscount(price.price,product.salePercentage) * product.count
                }else{
                    price.price * product.count
                }

            }
        }
        _state = _state.copy(
            totalAmount = totalAmount
        )
    }



}