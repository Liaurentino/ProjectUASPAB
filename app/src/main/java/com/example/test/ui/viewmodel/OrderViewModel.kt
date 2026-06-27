package com.example.test.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageResName: String // we can map this to local drawables
)

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int
)

enum class OrderStatus {
    NONE,
    PREPARING,    // Memasak
    PICKED_UP,    // Driver Menjemput
    ON_THE_WAY,   // Di Jalan
    ARRIVED       // Sampai
}

data class UiState(
    val userName: String = "",
    val userEmail: String = "",
    val userLocation: String = "Jl. Thamrin, Jakarta Pusat",
    val cartItems: List<CartItem> = emptyList(),
    val activeOrderStatus: OrderStatus = OrderStatus.NONE,
    val selectedPaymentMethod: String = "E-Money"
)

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPrefs = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Predefined Menu Items
    val menuList = listOf(
        MenuItem("sate_ayam", "Sate Ayam", "Sate ayam bumbu kacang khas Madura lezat", 20000.0, "makanansate"),
        MenuItem("nasi_goreng", "Nasi Goreng", "Nasi goreng spesial dengan telur dadar dan acar", 15000.0, "nasigorengdewa"), // fallback to same drawable
        MenuItem("sop_buntut", "Sop Buntut", "Sop buntut sapi dengan kuah kaldu hangat gurih", 35000.0, "sopbuntut"),
        MenuItem("nasi_goreng_dewa", "Nasi Goreng Dewa", "Nasi goreng pedas level dewa dengan topping melimpah", 25000.0, "nasigorengdewa")
    )

    init {
        // Load initial user details from SharedPreferences
        val name = sharedPrefs.getString("user_name", "") ?: ""
        val email = sharedPrefs.getString("user_email", "") ?: ""
        val location = sharedPrefs.getString("user_location", "Jl. Thamrin, Jakarta Pusat") ?: "Jl. Thamrin, Jakarta Pusat"
        _uiState.update { 
            it.copy(userName = name, userEmail = email, userLocation = location) 
        }
    }

    fun setUserName(name: String) {
        sharedPrefs.edit().putString("user_name", name).apply()
        _uiState.update { it.copy(userName = name) }
    }

    fun setUserEmail(email: String) {
        sharedPrefs.edit().putString("user_email", email).apply()
        _uiState.update { it.copy(userEmail = email) }
    }

    fun setUserLocation(location: String) {
        sharedPrefs.edit().putString("user_location", location).apply()
        _uiState.update { it.copy(userLocation = location) }
    }

    fun addToCart(menuItem: MenuItem) {
        _uiState.update { currentState ->
            val existingIndex = currentState.cartItems.indexOfFirst { it.menuItem.id == menuItem.id }
            val newItems = if (existingIndex != -1) {
                currentState.cartItems.mapIndexed { index, cartItem ->
                    if (index == existingIndex) cartItem.copy(quantity = cartItem.quantity + 1)
                    else cartItem
                }
            } else {
                currentState.cartItems + CartItem(menuItem, 1)
            }
            currentState.copy(cartItems = newItems)
        }
    }

    fun updateCartQuantity(menuItemId: String, newQuantity: Int) {
        _uiState.update { currentState ->
            val newItems = currentState.cartItems.mapNotNull { cartItem ->
                if (cartItem.menuItem.id == menuItemId) {
                    if (newQuantity <= 0) null else cartItem.copy(quantity = newQuantity)
                } else {
                    cartItem
                }
            }
            currentState.copy(cartItems = newItems)
        }
    }

    fun selectPaymentMethod(method: String) {
        _uiState.update { it.copy(selectedPaymentMethod = method) }
    }

    fun getCartTotal(): Double {
        return _uiState.value.cartItems.sumOf { it.menuItem.price * it.quantity }
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyList()) }
    }

    fun placeOrder() {
        if (_uiState.value.cartItems.isEmpty()) return
        
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.PREPARING) }
        clearCart()
        
        // Start simulation of order delivery state changes in background
        viewModelScope.launch {
            delay(10000) // 10s preparing
            _uiState.update { it.copy(activeOrderStatus = OrderStatus.PICKED_UP) }
            delay(10000) // 10s driver picked up
            _uiState.update { it.copy(activeOrderStatus = OrderStatus.ON_THE_WAY) }
            delay(10000) // 10s on the way
            _uiState.update { it.copy(activeOrderStatus = OrderStatus.ARRIVED) }
        }
    }

    fun confirmOrderDelivery() {
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.NONE) }
    }
}
