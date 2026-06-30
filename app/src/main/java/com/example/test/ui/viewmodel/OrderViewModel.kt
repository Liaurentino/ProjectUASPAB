package com.example.test.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.OrderReceiver
import com.example.test.data.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import java.util.UUID
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
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.contentOrNull

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
    val imageResName: String,
    val category: String
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
    val imageResName: String,
    val category: String
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
    val userPhone: String = "",
    val userLocation: String = "Jl. Thamrin, Jakarta Pusat",
    val profileImageUrl: String = "",
    val cartItems: List<CartItem> = emptyList(),
    val activeOrderStatus: OrderStatus = OrderStatus.NONE,
    val selectedPaymentMethod: String = "E-Money",
    val isLoadingMenu: Boolean = false,
    val isUploadingProfileImage: Boolean = false,
    val activeOrderId: String? = null
)

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPrefs = application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Menu list yang di-load dari Supabase (atau fallback lokal)
    private val _menuList = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuList: List<MenuItem> get() = _menuList.value

    // Helper untuk Screen: ambil menu yang sudah dikelompokkan per kategori
    val menuByCategory: Map<String, List<MenuItem>>
        get() = _menuList.value.groupBy { it.category }

    // Fallback menu lokal (digunakan jika koneksi Supabase gagal)
    // 4 kategori x 3 item = 12 item, nama drawable placeholder asal (sesuaikan di res/drawable)
    private val localMenuFallback = listOf(
        // Kategori: Nasi
        MenuItem("nasi_1", "Nasi Goreng", "Nasi goreng spesial dengan telur dadar dan acar", 15000.0, "nasi_1", "Nasi"),
        MenuItem("nasi_2", "Nasi Goreng Dewa", "Nasi goreng pedas level dewa dengan topping melimpah", 25000.0, "nasi_2", "Nasi"),
        MenuItem("nasi_3", "Nasi Uduk", "Nasi uduk gurih dengan lauk pelengkap", 18000.0, "nasi_3", "Nasi"),

        // Kategori: Sate
        MenuItem("sate_1", "Sate Ayam", "Sate ayam bumbu kacang khas Madura lezat", 20000.0, "sate_1", "Sate"),
        MenuItem("sate_2", "Sate Kambing", "Sate kambing empuk dengan bumbu kecap", 28000.0, "sate_2", "Sate"),
        MenuItem("sate_3", "Sate Padang", "Sate padang dengan kuah kuning kental khas", 22000.0, "sate_3", "Sate"),

        // Kategori: Sop
        MenuItem("sop_1", "Sop Buntut", "Sop buntut sapi dengan kuah kaldu hangat gurih", 35000.0, "sop_1", "Sop"),
        MenuItem("sop_2", "Sop Iga", "Sop iga sapi dengan kuah bening segar", 32000.0, "sop_2", "Sop"),
        MenuItem("sop_3", "Soto Ayam", "Soto ayam kuah kuning dengan suwiran ayam", 17000.0, "sop_3", "Sop"),

        // Kategori: Minuman
        MenuItem("minuman_1", "Es Teh Manis", "Teh manis dingin segar pelepas dahaga", 5000.0, "minuman_1", "Minuman"),
        MenuItem("minuman_2", "Es Jeruk", "Es jeruk peras asli tanpa pengawet", 7000.0, "minuman_2", "Minuman"),
        MenuItem("minuman_3", "Es Kopi Susu", "Kopi susu dingin dengan gula aren", 12000.0, "minuman_3", "Minuman")
    )

    companion object {
        private const val TAG = "OrderViewModel"
    }

    init {
        // Load initial user details from SharedPreferences
        val name = sharedPrefs.getString("user_name", "") ?: ""
        val email = sharedPrefs.getString("user_email", "") ?: ""
        val phone = sharedPrefs.getString("user_phone", "") ?: ""
        val location = sharedPrefs.getString("user_location", "Jl. Thamrin, Jakarta Pusat") ?: "Jl. Thamrin, Jakarta Pusat"
        val profileImageUrl = sharedPrefs.getString("profile_image_url", "") ?: ""
        _uiState.update {
            it.copy(
                userName = name,
                userEmail = email,
                userPhone = phone,
                userLocation = location,
                profileImageUrl = profileImageUrl
            )
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
                        imageResName = dto.imageResName,
                        category = dto.category
                    )
                }
                _menuList.value = items
                Log.d(TAG, "Berhasil fetch ${items.size} menu dari Supabase")
            } catch (e: Throwable) {
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

    private fun sendOrderBroadcast(status: String) {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(context, OrderReceiver::class.java).apply {
            action = OrderReceiver.ACTION_ORDER_STATUS_CHANGED
            putExtra(OrderReceiver.EXTRA_ORDER_STATUS, status)
        }
        context.sendBroadcast(intent)
        Log.d(TAG, "Broadcasting status: $status")
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
        sendOrderBroadcast("PLACED")

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
        sendOrderBroadcast(newStatus.name)
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
        sendOrderBroadcast(OrderStatus.PICKED_UP.name)
        delay(10000)
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.ON_THE_WAY) }
        sendOrderBroadcast(OrderStatus.ON_THE_WAY.name)
        delay(10000)
        _uiState.update { it.copy(activeOrderStatus = OrderStatus.ARRIVED) }
        sendOrderBroadcast(OrderStatus.ARRIVED.name)
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
        sendOrderBroadcast("DELIVERED")
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
    fun updateProfile(
        newName: String,
        newPhone: String,
        newAddress: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val currentUser = SupabaseClient.client.auth.currentUserOrNull()
                if (currentUser != null) {
                    SupabaseClient.client.auth.updateUser {
                        data = kotlinx.serialization.json.buildJsonObject {
                            put("name", newName)
                            put("full_name", newName)
                            put("phone", newPhone)
                            put("address", newAddress)
                        }
                    }
                }
                setUserName(newName)
                sharedPrefs.edit().putString("user_phone", newPhone).apply()
                setUserLocation(newAddress)
                _uiState.update { it.copy(userPhone = newPhone) }
                onSuccess()
            } catch (e: Exception) {
                Log.e(TAG, "Update profile failed", e)
                onError(e.message ?: "Update profil gagal.")
            }
        }
    }

    // =============================================================
    // UPDATE: Upload foto profil ke Supabase Storage (bucket 'profileimages')
    // =============================================================
    fun uploadProfileImage(
        imageBytes: ByteArray,
        fileExtension: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isUploadingProfileImage = true) }
            try {
                // 1. Buat nama file unik menggunakan UUID, agar file lama tidak tertimpa cache CDN
                val fileName = "${UUID.randomUUID()}.$fileExtension"

                // 2. Upload ke bucket 'profileimages'
                val bucket = SupabaseClient.client.storage.from("profileimages")

                bucket.upload(
                    path = fileName,
                    data = imageBytes,
                    upsert = true
                )

                // 3. Dapatkan Public URL
                val publicUrl = bucket.publicUrl(path = fileName)

                // 4. Update metadata user di Supabase Auth (kalau sedang login)
                val currentUser = SupabaseClient.client.auth.currentUserOrNull()
                if (currentUser != null) {
                    SupabaseClient.client.auth.updateUser {
                        data = buildJsonObject {
                            put("avatar_url", publicUrl)
                        }
                    }
                }

                // 5. Simpan URL ke SharedPreferences & UiState
                sharedPrefs.edit().putString("profile_image_url", publicUrl).apply()
                _uiState.update { it.copy(profileImageUrl = publicUrl, isUploadingProfileImage = false) }

                Log.d(TAG, "Foto profil berhasil diupload: $publicUrl")
                onSuccess()
            } catch (e: Exception) {
                Log.e(TAG, "Gagal upload foto profil", e)
                _uiState.update { it.copy(isUploadingProfileImage = false) }
                onError(e.message ?: "Gagal mengupload foto profil.")
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
                val name = currentUser?.userMetadata?.get("name")?.jsonPrimitive?.contentOrNull
                    ?: emailInput.substringBefore("@")
                val avatarUrl = currentUser?.userMetadata?.get("avatar_url")?.jsonPrimitive?.contentOrNull ?: ""

                setUserName(name)
                setUserEmail(emailInput)
                if (avatarUrl.isNotEmpty()) {
                    sharedPrefs.edit().putString("profile_image_url", avatarUrl).apply()
                    _uiState.update { it.copy(profileImageUrl = avatarUrl) }
                }
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

                val rawNonce = java.util.UUID.randomUUID().toString()
                val bytes = rawNonce.toByteArray()
                val md = java.security.MessageDigest.getInstance("SHA-256")
                val digest = md.digest(bytes)
                val hashedNonce = digest.joinToString("") { "%02x".format(it) }

                val googleIdOption = com.google.android.libraries.identity.googleid.GetGoogleIdOption.Builder()
                    .setServerClientId(SupabaseClient.GOOGLE_WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .setNonce(hashedNonce)
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
                        this.nonce = rawNonce
                    }

                    // Ambil info user setelah login berhasil
                    val currentUser = SupabaseClient.client.auth.currentUserOrNull()
                    val name = currentUser?.userMetadata?.get("full_name")?.jsonPrimitive?.contentOrNull
                        ?: currentUser?.userMetadata?.get("name")?.jsonPrimitive?.contentOrNull
                        ?: currentUser?.email?.substringBefore("@") ?: ""
                    val email = currentUser?.email ?: ""
                    val avatarUrl = currentUser?.userMetadata?.get("avatar_url")?.jsonPrimitive?.contentOrNull ?: ""

                    setUserName(name)
                    setUserEmail(email)
                    if (avatarUrl.isNotEmpty()) {
                        sharedPrefs.edit().putString("profile_image_url", avatarUrl).apply()
                        _uiState.update { it.copy(profileImageUrl = avatarUrl) }
                    }
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

    // =============================================================
    // CREATE: Menambahkan menu baru beserta upload gambar ke Storage
    // =============================================================
    fun uploadMenuWithImage(
        name: String,
        description: String,
        price: Double,
        category: String,
        imageBytes: ByteArray,
        fileExtension: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // 1. Buat nama file yang unik menggunakan UUID
                val fileName = "${UUID.randomUUID()}.$fileExtension"

                // 2. Upload gambar ke bucket 'menuimage'
                val bucket = SupabaseClient.client.storage.from("menuimage")
                bucket.upload(path = fileName, data = imageBytes)

                // 3. Dapatkan Public URL
                val publicUrl = bucket.publicUrl(path = fileName)

                // 4. Buat objek DTO menu baru
                val newMenuDto = MenuItemDto(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    description = description,
                    price = price,
                    imageResName = publicUrl, // Simpan URL lengkap di kolom image_res_name
                    category = category
                )

                // 5. Insert ke tabel 'menu_items'
                SupabaseClient.client.postgrest["menu_items"].insert(newMenuDto)

                // 6. Refresh menu list
                fetchMenuFromSupabase()

                onSuccess()
            } catch (e: Exception) {
                Log.e(TAG, "Gagal menambahkan menu baru dengan gambar", e)
                onError(e.message ?: "Terjadi kesalahan tidak diketahui")
            }
        }
    }
}