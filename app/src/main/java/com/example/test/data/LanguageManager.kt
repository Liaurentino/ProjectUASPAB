package com.example.test.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object LanguageManager {
    var currentLanguage by mutableStateOf("id")
        private set

    fun init(context: Context) {
        val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        currentLanguage = sharedPrefs.getString("app_language", "id") ?: "id"
    }

    fun setLanguage(context: Context, lang: String) {
        val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putString("app_language", lang).apply()
        currentLanguage = lang
    }

    fun get(key: String): String {
        val isEn = currentLanguage == "en"
        return when (key) {
            // Beranda / Home
            "halo" -> if (isEn) "Hello" else "Halo"
            "mau_makan" -> if (isEn) "What delicious food do you want to eat today?" else "Mau makan enak apa hari ini?"
            "search_placeholder" -> if (isEn) "Search sate, fried rice, soup..." else "Cari sate, nasi goreng, sup buntut..."
            "promo" -> if (isEn) "Hottest Promo" else "Promo Terhangat"
            "categories" -> if (isEn) "Menu Categories" else "Kategori Menu"
            "nasi" -> if (isEn) "Rice" else "Nasi"
            "sate" -> if (isEn) "Satay" else "Sate"
            "sop" -> if (isEn) "Soup" else "Sop"
            "lainnya" -> if (isEn) "Others" else "Lainnya"

            // Profil / Profile Options
            "pengaturan" -> if (isEn) "Settings" else "Pengaturan"
            "keamanan" -> if (isEn) "Security" else "Keamanan"
            "pembayaran" -> if (isEn) "Payment Method" else "Metode Pembayaran"
            "lokasi" -> if (isEn) "Restaurant Location" else "Lokasi Restoran"
            "tentang" -> if (isEn) "About Us" else "Tentang Kami"

            // Edit Profil Screen
            "edit_profil" -> if (isEn) "Edit Profile" else "Edit Profil"
            "change_photo" -> if (isEn) "Tap to change photo" else "Tap untuk ganti foto"
            "uploading_photo" -> if (isEn) "Uploading photo..." else "Mengupload foto..."
            "name" -> if (isEn) "Full Name" else "Nama Lengkap"
            "phone" -> if (isEn) "Phone Number" else "Nomor Telepon"
            "address" -> if (isEn) "Address" else "Alamat"
            "save_changes" -> if (isEn) "Save Changes" else "Simpan Perubahan"

            // Pengaturan Screen
            "app_settings" -> if (isEn) "App Settings" else "Pengaturan Aplikasi"
            "change_profile" -> if (isEn) "Edit Profile" else "Ubah Profil"
            "change_profile_sub" -> if (isEn) "Name, photo, contact details" else "Nama, foto, detail kontak"
            "notifications" -> if (isEn) "Notifications" else "Notifikasi"
            "notifications_sub" -> if (isEn) "Manage food reminders & promos" else "Atur pengingat makanan & promo"
            "language" -> if (isEn) "Language" else "Bahasa"
            "help_faq" -> if (isEn) "Help & FAQ" else "Bantuan & FAQ"
            "help_faq_sub" -> if (isEn) "Contact support center" else "Hubungi pusat bantuan"
            "choose_language" -> if (isEn) "Select Language" else "Pilih Bahasa"
            "cancel" -> if (isEn) "Cancel" else "Batal"

            else -> key
        }
    }
}
