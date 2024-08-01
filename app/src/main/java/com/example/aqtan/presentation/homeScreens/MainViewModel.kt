package com.example.aqtan.presentation.homeScreens

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.aqtan.R
import com.example.aqtan.data.remote.dto.Category
import com.example.aqtan.data.remote.dto.HomeLists
import com.example.aqtan.data.remote.dto.Product
import com.example.aqtan.domain.repository.ApiServicesRepository
import com.example.aqtan.presentation.components.applyDiscount
import com.example.aqtan.presentation.homeScreens.bag.BagState
import com.example.aqtan.presentation.navigation.Screens
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


     fun getTotalOrderAmount(selectedCountryCode:Int) {
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


    /////////////////////////////////////////////////////////////////////////////////////////


    fun onUserNameChange(userName:String){
        _state = _state.copy(
            userName = userName,
            isErrorUserName  = false,
            userNameErrorMessage = ""
        )

    }

    fun onEmailChange(email:String){
        _state = _state.copy(
            email = email,
            isErrorEmail = false,
            emailErrorMessage = ""
        )

    }

    fun onPhoneNumberChange(phoneNumber:String){
        _state = _state.copy(
            phone = phoneNumber,
            isErrorPhone = false,
            phoneErrorMessage = ""
        )

    }

    fun onStreetAddressChange(streetAddress:String){
        _state = _state.copy(
            streetAddress = streetAddress,
            isErrorStreetAddress = false,
            streetAddressErrorMessage = ""
        )

    }


    fun onCityChange(city:String){
        _state = _state.copy(
            city = city,
            isErrorCity = false,
            cityErrorMessage = ""
        )

    }

    fun onPostalCodeChange(postalCode:String){
        _state = _state.copy(
            postalCode = postalCode,
            isPostalCode = false,
            postalCodeErrorMessage = ""
        )
    }


    // when user need to make password is visible
    fun onCashSelect(){
        val newCashValue = _state.cash.not()
        _state = _state.copy(
            cash  = newCashValue,
            visa = newCashValue.not(),
            paymentMethodErrorMessage = "",
        )
    }


    // when user need to make password is visible
    fun onVisaSelect(){
        val newCashValue = _state.visa.not()
        _state = _state.copy(
            cash  = newCashValue.not(),
            visa = newCashValue,
            paymentMethodErrorMessage = "",
        )
    }


    /////////////////////////////////////////////////////////////////////////////////////////


    fun onChangeCardHolderName(newCardHolderName:String){
        _state = _state.copy(
            cardHolderName = newCardHolderName
        )
    }

    fun onChangeCardHolderNumber(newCardHolderNumber:String){
        _state = _state.copy(
            cardHolderNumber = newCardHolderNumber
        )
    }


    fun onChangeExpiryNumber(newExpiryNumber:String){
        if (newExpiryNumber.length<=4){
            _state = _state.copy(
                expiryDate = newExpiryNumber
            )
        }
    }
    fun onChangeCvc(newCvc:String){
        if (newCvc.length<=3){
            _state = _state.copy(
                cvc = newCvc
            )
        }

    }

    fun onCheckoutComplete(navController: NavHostController, context: Context){
        if (_state.userName.isEmpty()){
            _state=_state.copy(
                isErrorUserName = true,
                userNameErrorMessage = context.getString(R.string.please_enter_your_name)
            )

        }
        if (_state.email.isEmpty()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_your_email)
            )

        }
        if (isValidEmail(_state.email).not()){
            _state=_state.copy(
                isErrorEmail = true,
                emailErrorMessage = context.getString(R.string.please_enter_valid_email)
            )

        }

        if (_state.phone.isEmpty()){
            _state=_state.copy(
                isErrorPhone = true,
                phoneErrorMessage = context.getString(R.string.please_enter_your_phone_number)
            )

        }
        if (isValidPhone(_state.phone)){
            _state=_state.copy(
                isErrorPhone = true,
                phoneErrorMessage = context.getString(R.string.please_enter_valid_phone_number)
            )

        }
        if (_state.streetAddress.isEmpty()){
            _state=_state.copy(
                isErrorStreetAddress = true,
                streetAddressErrorMessage = context.getString(R.string.please_enter_your_address)
            )

        }


        if (_state.city.isEmpty()){
            _state=_state.copy(
                isErrorCity = true,
                cityErrorMessage = context.getString(R.string.please_enter_your_city)
            )

        }


        if (_state.postalCode.isEmpty()){
            _state=_state.copy(
                isPostalCode = true,
                postalCodeErrorMessage = context.getString(R.string.please_enter_your_postal_code)
            )

        }
        if (isValidPostalCode(_state.postalCode)){
            _state=_state.copy(
                isPostalCode = true,
                postalCodeErrorMessage = context.getString(R.string.please_enter_valid_postal_code)
            )

        }


        if (
            _state.userName.isNotEmpty()&&
            _state.email.isNotEmpty()&&
            _state.phone.isNotEmpty()&&
            _state.streetAddress.isNotEmpty()&&
            _state.city.isNotEmpty()&&
            _state.postalCode.isNotEmpty()&&
            isValidEmail(_state.email)&&
            isValidPhone(_state.phone)&&
            isValidPostalCode(_state.postalCode)&&
            _state.cash != _state.visa
        ){
            if (_state.visa && !_state.cash){
                navController.navigate(Screens.Payment.route)
            }else if (!_state.visa && _state.cash){
                // call api to complete order

            }else{
                _state=_state.copy(
                    paymentMethodErrorMessage = context.getString(R.string.please_select_payment_method)
                )
            }
        }


    }

    fun onCompleteOrder(navController: NavHostController,context: Context){
        // make check and then call api
        navController.navigate(Screens.Success.route)

    }


////////////////////////////////////////////////////////////////////////////////////////////////

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPhone(phone: String): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches()
    }

    private fun isValidPostalCode(postalCode: String): Boolean {
        return postalCode.matches(Regex("^[0-9]{5}$"))
    }



    private fun isValidExpiryDate(expiryDate: String): Boolean {
        // Check if the expiry date has exactly 4 digits
        if (expiryDate.length != 4) return false

        // Extract month and year from the string
        val month = expiryDate.substring(0, 2).toIntOrNull() ?: return false
        val year = expiryDate.substring(2, 4).toIntOrNull() ?: return false

        // Validate month (should be between 01 and 12)
        if (month !in 1..12) return false

        // Convert the 2-digit year to a 4-digit year (assuming 20XX)
        val fullYear = 2000 + year

        // Get the current month and year
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1

        // Check if the expiry date is in the future
        return fullYear > currentYear || (fullYear == currentYear && month >= currentMonth)
    }



}