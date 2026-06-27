package com.example.test.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

// ============================================================
// Data classes untuk komunikasi dengan Supabase (serializable)
// ============================================================

@Serializable
data class MenuItemDto(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Double,
    @SerialName("image_res_name")
    val imageResName: String
)

@Serializable
data class OrderDto(
    val id: String? = null,
    @SerialName("user_name")
    val userName: String,
    @SerialName("user_email")
    val userEmail: String,
    @SerialName("user_location")
    val userLocation: String,
    @SerialName("payment_method")
    val paymentMethod: String,
    @SerialName("total_price")
    val totalPrice: Double,
    val status: String = "PREPARING",
    @SerialName("created_at")
    val createdAt: String? = null
)

@Serializable
data class OrderItemDto(
    val id: String? = null,
    @SerialName("order_id")
    val orderId: String,
    @SerialName("menu_item_id")
    val menuItemId: String,
    val quantity: Int,
    val price: Double
)

// ============================================================
// Data classes untuk UI (non-serializable, dipakai oleh Screens)
// ============================================================

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageResName: String
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
    val selectedPaymentMethod: String = "E-Money",
    val isLoadingMenu: Boolean = false,
    val activeOrderId: String? = null
)

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPrefs = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Menu list yang di-load dari Supabase (atau fallback lokal)
    private val _menuList = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuList: List<MenuItem> get() = _menuList.value

    // Fallback menu lokal (digunakan jika koneksi Supabase gagal)
    private val localMenuFallback = listOf(
        MenuItem("sate_ayam", "Sate Ayam", "Sate ayam bumbu kacang khas Madura lezat", 20000.0, "makanansate"),
        MenuItem("nasi_goreng", "Nasi Goreng", "Nasi goreng spesial dengan telur dadar dan acar", 15000.0, "nasigorengdewa"),
        MenuItem("sop_buntut", "Sop Buntut", "Sop buntut sapi dengan kuah kaldu hangat gurih", 35000.0, "sopbuntut"),
        MenuItem("nasi_goreng_dewa", "Nasi Goreng Dewa", "Nasi goreng pedas level dewa dengan topping melimpah", 25000.0, "nasigorengdewa")
    )

    companion object {
        private const val TAG = "OrderViewModel"
    }

    init {
        // Load initial user details from SharedPreferences
        val name = sharedPrefs.getString("user_name", "") ?: ""
        val email = sharedPrefs.getString("user_email", "") ?: ""
        val location = sharedPrefs.getString("user_location", "Jl. Thamrin, Jakarta Pusat") ?: "Jl. Thamrin, Jakarta Pusat"
        _uiState.update {
            it.copy(userName = name, userEmail = email, userLocation = location)
        }

        // Fetch menu dari Supabase
        fetchMenuFromSupabase()
    }

    // =============================================================
    // READ: Mengambil daftar menu dari tabel 'menu_items' di Supabase
    // =============================================================
    private fun fetchMenuFromSupabase() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMenu = true) }
            try {
                val result = SupabaseClient.client.postgrest["menu_items"]
                    .select()
                    .decodeList<MenuItemDto>()

                val items = result.map { dto ->
                    MenuItem(
                        id = dto.id,
                        name = dto.name,
                        description = dto.description ?: "",
                        price = dto.price,
                        imageResName = dto.imageResName
                    )
                }
                _menuList.value = items
                Log.d(TAG, "Berhasil fetch ${items.size} menu dari Supabase")
            } catch (e: Exception) {
                Log.e(TAG, "Gagal fetch menu dari Supabase, menggunakan data lokal", e)
                _menuList.value = localMenuFallback
            } finally {
                _uiState.update { it.copy(isLoadingMenu = false) }
            }
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

    // =============================================================
    // CREATE: Memasukkan order baru ke tabel 'orders' & 'order_items'
    // =============================================================
    fun placeOrder() {
        if (_uiState.value.cartItems.isEmpty()) return

        val currentState = _uiState.value
        val cartSnapshot = currentState.cartItems.toList()
        val total = cartSnapshot.sumOf { it.menuItem.price * it.quantity }

        _uiState.update { it.copy(activeOrderStatus = OrderStatus.PREPARING) }
        clearCart()

        viewModelScope.launch {
            try {
                // 1. Insert ke tabel 'orders' (CREATE)
                val orderDto = OrderDto(
                    userName = currentState.userName,
                    userEmail = currentState.userEmail,
                    userLocation = currentState.userLocation,
                    paymentMethod = currentState.selectedPaymentMethod,
                    totalPrice = total,
                    status = "PREPARING"
                )

                val insertedOrders = SupabaseClient.client.postgrest["orders"]
                    .insert(orderDto) {
                        select()
                    }
                    .decodeList<OrderDto>()

                val orderId = insertedOrders.firstOrNull()?.id

                if (orderId != null) {
                    _uiState.update { it.copy(activeOrderId = orderId) }

                    // 2. Insert ke tabel 'order_items' (CREATE)
                    val orderItemDtos = cartSnapshot.map { cartItem ->
                        OrderItemDto(
                            orderId = orderId,
                            menuItemId = cartItem.menuItem.id,
                            quantity = cartItem.quantity,
                            price = cartItem.menuItem.price * cartItem.quantity
                        )
                    }

                    SupabaseClient.client.postgrest["order_items"]
                        .insert(orderItemDtos)

                    Log.d(TAG, "Order berhasil dibuat dengan ID: $orderId")

                    // 3. Simulasi perubahan status (UPDATE)
                    simulateOrderProgress(orderId)
                } else {
                    Log.e(TAG, "Gagal mendapatkan ID order setelah insert")
                    simulateOrderProgressLocal()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Gagal insert order ke Supabase", e)
                // Tetap jalankan simulasi lokal agar UX tidak terganggu
                simulateOrderProgressLocal()
            }
        }
    }

    // =============================================================
    // UPDATE: Memperbarui status pesanan di tabel 'orders'
    // =============================================================
    private suspend fun simulateOrderProgress(orderId: String) {
        delay(10000) // 10s preparing
        updateOrderStatus(orderId, OrderStatus.PICKED_UP)

        delay(10000) // 10s driver picked up
        updateOrderStatus(orderId, OrderStatus.ON_THE_WAY)

        delay(10000) // 10s on the way
        updateOrderStatus(orderId, OrderStatus.ARRIVED)
    }

    private suspend fun updateOrderStatus(orderId: String, newStatus: OrderStatus) {
        _uiState.update { it.copy(activeOrderStatus = newStatus) }
        try {
            SupabaseClient.client.postgrest["orders"]
                .update({ set("status", newStatus.name) }) {
                    filter { eq("id", orderId) }
                }
            Log.d(TAG, "Status order $orderId diperbarui ke: ${newStatus.name}")
        } catch (e: Exception) {
            Log.e(TAG, "Gagal update status order di Supabase", e)
        }
    }

    // Fallback simulasi lokal (tanpa Supabase)
    private suspend fun simulateOrderProgressLocal() {
        delay(10000)
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.PICKED_UP) }
        delay(10000)
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.ON_THE_WAY) }
        delay(10000)
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.ARRIVED) }
    }

    // =============================================================
    // DELETE: Menghapus order dari tabel 'orders' (cascade ke order_items)
    // =============================================================
    fun deleteOrder(orderId: String) {
        viewModelScope.launch {
            try {
                SupabaseClient.client.postgrest["orders"]
                    .delete {
                        filter { eq("id", orderId) }
                    }
                _uiState.update {
                    it.copy(
                        activeOrderStatus = OrderStatus.NONE,
                        activeOrderId = null
                    )
                }
                Log.d(TAG, "Order $orderId berhasil dihapus dari Supabase")
            } catch (e: Exception) {
                Log.e(TAG, "Gagal menghapus order dari Supabase", e)
            }
        }
    }

    fun confirmOrderDelivery() {
        val orderId = _uiState.value.activeOrderId
        _uiState.update {
            it.copy(activeOrderStatus = OrderStatus.NONE, activeOrderId = null)
        }
        // Update status akhir di Supabase
        if (orderId != null) {
            viewModelScope.launch {
                try {
                    SupabaseClient.client.postgrest["orders"]
                        .update({ set("status", "DELIVERED") }) {
                            filter { eq("id", orderId) }
                        }
                    Log.d(TAG, "Order $orderId dikonfirmasi dan diperbarui ke DELIVERED")
                } catch (e: Exception) {
                    Log.e(TAG, "Gagal update status DELIVERED di Supabase", e)
                }
            }
        }
    }

    // =============================================================
    // AUTH: Integrasi Registrasi & Login dengan Supabase Auth
    // =============================================================

    fun signUpWithSupabase(
        emailInput: String,
        passwordInput: String,
        nameInput: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                SupabaseClient.client.auth.signUpWith(Email) {
                    email = emailInput
                    password = passwordInput
                    data = buildJsonObject {
                        put("name", nameInput)
                    }
                }
                setUserName(nameInput)
                setUserEmail(emailInput)
                onSuccess()
            } catch (e: Exception) {
                Log.e(TAG, "Sign Up failed", e)
                onError(e.message ?: "Sign Up gagal. Silakan coba lagi.")
            }
        }
    }

    fun signInWithSupabase(
        emailInput: String,
        passwordInput: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                SupabaseClient.client.auth.signInWith(Email) {
                    email = emailInput
                    password = passwordInput
                }
                // Dapatkan user metadata setelah berhasil login
                val currentUser = SupabaseClient.client.auth.currentUserOrNull()
                val name = currentUser?.userMetadata?.get("name") as? String 
                    ?: emailInput.substringBefore("@")
                
                setUserName(name)
                setUserEmail(emailInput)
                onSuccess()
            } catch (e: Exception) {
                Log.e(TAG, "Sign In failed", e)
                onError(e.message ?: "Sign In gagal. Email atau password salah.")
            }
        }
    }

    fun signOut(onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            try {
                SupabaseClient.client.auth.signOut()
            } catch (e: Exception) {
                Log.e(TAG, "Sign Out failed", e)
            } finally {
                setUserName("")
                setUserEmail("")
                onSuccess()
            }
        }
    }

    // =============================================================
    // AUTH: Google Sign-In menggunakan Credential Manager
    // =============================================================

    fun signInWithGoogle(
        activityContext: android.app.Activity,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val credentialManager = androidx.credentials.CredentialManager.create(activityContext)

                val googleIdOption = com.google.android.libraries.identity.googleid.GetGoogleIdOption.Builder()
                    .setServerClientId(SupabaseClient.GOOGLE_WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()

                val request = androidx.credentials.GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(activityContext, request)
                val credential = result.credential

                if (credential is androidx.credentials.CustomCredential &&
                    credential.type == com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    val googleIdTokenCredential =
                        com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    // Kirim token ke Supabase Auth
                    SupabaseClient.client.auth.signInWith(
                        io.github.jan.supabase.gotrue.providers.builtin.IDToken
                    ) {
                        this.idToken = idToken
                        provider = io.github.jan.supabase.gotrue.providers.Google
                    }

                    // Ambil info user setelah login berhasil
                    val currentUser = SupabaseClient.client.auth.currentUserOrNull()
                    val name = (currentUser?.userMetadata?.get("full_name") as? String)
                        ?: (currentUser?.userMetadata?.get("name") as? String)
                        ?: currentUser?.email?.substringBefore("@") ?: ""
                    val email = currentUser?.email ?: ""

                    setUserName(name)
                    setUserEmail(email)
                    onSuccess()
                } else {
                    onError("Credential type tidak didukung.")
                }
            } catch (e: androidx.credentials.exceptions.GetCredentialCancellationException) {
                Log.w(TAG, "Google Sign In dibatalkan oleh user")
                onError("Login dibatalkan.")
            } catch (e: Exception) {
                Log.e(TAG, "Google Sign In failed", e)
                onError(e.message ?: "Google Sign In gagal.")
            }
        }
    }
}
