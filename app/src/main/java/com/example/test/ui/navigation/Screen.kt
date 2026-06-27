package com.example.test.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Main : Screen("main")
    object Checkout : Screen("checkout")
    object StatusPesanan : Screen("status_pesanan")
    
    // Settings Screens
    object Pengaturan : Screen("pengaturan")
    object Keamanan : Screen("keamanan")
    object MetodePembayaran : Screen("metode_pembayaran")
    object LokasiRestoran : Screen("lokasi_restoran")
    object TentangKami : Screen("tentang_kami")
}
