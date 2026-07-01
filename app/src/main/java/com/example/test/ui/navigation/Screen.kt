package com.example.test.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")

    object Main : Screen("main?tab={tab}") {
        const val BASE_ROUTE = "main"
        fun withTab(tab: Int): String = "$BASE_ROUTE?tab=$tab"
    }

    object Checkout : Screen("checkout")
    object Konfirmasi : Screen("konfirmasi")
    object StatusPesanan : Screen("status_pesanan")

    // Settings Screens
    object Pengaturan : Screen("pengaturan")
    object Keamanan : Screen("keamanan")
    object MetodePembayaran : Screen("metode_pembayaran")
    object LokasiRestoran : Screen("lokasi_restoran")
    object TentangKami : Screen("tentang_kami")
    object EditProfil : Screen("edit_profil")
}