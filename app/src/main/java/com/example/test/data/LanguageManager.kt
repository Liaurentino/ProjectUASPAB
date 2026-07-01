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
            "popular" -> if (isEn) "Most Popular" else "Paling Populer"
            "see_all" -> if (isEn) "See All" else "Lihat Semua"

            // Menu Screen
            "daftar_menu" -> if (isEn) "Menu List" else "Daftar Menu"
            "menu_subtitle" -> if (isEn) "Please choose your favorite dish today" else "Silakan pilih hidangan favorit Anda hari ini"
            "semua" -> if (isEn) "All" else "Semua"
            "minuman" -> if (isEn) "Drinks" else "Minuman"
            "tambah" -> if (isEn) "Add" else "Tambah"

            // Checkout Screen
            "checkout_title" -> if (isEn) "Order Checkout" else "Checkout Pesanan"
            "keranjang_kosong" -> if (isEn) "Shopping Cart Empty" else "Keranjang Belanja Kosong"
            "keranjang_kosong_sub" -> if (isEn) "Please add delicious menu in the Menu tab." else "Silakan tambah menu lezat di tab Menu."
            "voucher" -> if (isEn) "Use Promo Voucher" else "Pakai Voucher Promo"
            "ringkasan" -> if (isEn) "Payment Summary" else "Ringkasan Pembayaran"
            "subtotal" -> if (isEn) "Subtotal" else "Subtotal"
            "biaya_kirim" -> if (isEn) "Service/Delivery Fee" else "Biaya Layanan/Kirim"
            "total" -> if (isEn) "Total Payment" else "Total Pembayaran"
            "pesan_sekarang" -> if (isEn) "Order Now" else "Pesan Sekarang"
            "tambah_menu" -> if (isEn) "Add More Menu" else "Tambah Menu"
            "konfirmasi_dialog" -> if (isEn) "Thank you! Our team is preparing your special dish. Please wait a moment!" else "Terima kasih! Tim kami sedang menyiapkan hidangan spesialmu. Mohon ditunggu sebentar, ya!"
            "konfirmasi_btn" -> if (isEn) "Confirm" else "Konfirmasi"

            // Konfirmasi Screen
            "pesanan_berhasil" -> if (isEn) "Order Successfully Created!" else "Pesanan Berhasil Dibuat!"
            "pesanan_berhasil_sub" -> if (isEn) "Please relax for a moment, your order will be prepared by our chef soon." else "Silakan bersantai sejenak, pesanan Anda akan segera disiapkan oleh koki kami."
            "tambah_pesanan_lagi" -> if (isEn) "Add More Order" else "Tambah Pesanan Lagi"

            // Status Pesanan Screen
            "lacak_pesanan" -> if (isEn) "Track Order" else "Lacak Pesanan"
            "driver_label" -> if (isEn) "Dine In Delivery Driver" else "Dine In Delivery Driver"
            "status_pengantaran" -> if (isEn) "Delivery Status" else "Status Pengantaran"
            "step_dibuat" -> if (isEn) "Order Created" else "Pesanan Dibuat"
            "step_dibuat_sub" -> if (isEn) "Your order has been sent to the restaurant." else "Pesanan Anda berhasil dikirim ke restoran."
            "step_memasak" -> if (isEn) "Being Cooked" else "Sedang Memasak"
            "step_memasak_sub" -> if (isEn) "Our chef is preparing the best food." else "Koki kami sedang menyajikan makanan terbaik."
            "step_diambil" -> if (isEn) "Food Picked Up by Driver" else "Makanan Diambil Driver"
            "step_dijalan" -> if (isEn) "On the Way" else "Dalam Perjalanan"
            "step_dijalan_sub" -> if (isEn) "Driver is heading to your location." else "Driver menuju ke tempat Anda."
            "step_sampai" -> if (isEn) "Arrived at Destination" else "Sampai Tujuan"
            "step_sampai_sub" -> if (isEn) "Your warm food is ready to eat!" else "Makanan hangat siap Anda santap!"
            "konfirmasi_terima" -> if (isEn) "Confirm Food Receipt" else "Konfirmasi Terima Makanan"
            "menunggu" -> if (isEn) "Waiting for Delivery..." else "Menunggu Pengantaran..."

            // Profil / Profile Options
            "pengaturan" -> if (isEn) "Settings" else "Pengaturan"
            "keamanan" -> if (isEn) "Security" else "Keamanan"
            "pembayaran" -> if (isEn) "Payment Method" else "Metode Pembayaran"
            "lokasi" -> if (isEn) "Restaurant Location" else "Lokasi Restoran"
            "tentang" -> if (isEn) "About Us" else "Tentang Kami"
            "logout" -> if (isEn) "Logout" else "Keluar"

            // Edit Profil Screen
            "edit_profil" -> if (isEn) "Edit Profile" else "Edit Profil"
            "change_photo" -> if (isEn) "Tap to change photo" else "Tap untuk ganti foto"
            "uploading_photo" -> if (isEn) "Uploading photo..." else "Mengupload foto..."
            "name" -> if (isEn) "Full Name" else "Nama Lengkap"
            "phone" -> if (isEn) "Phone Number" else "Nomor Telepon"
            "address" -> if (isEn) "Address" else "Alamat"
            "save_changes" -> if (isEn) "Save Changes" else "Simpan Perubahan"
            "name_placeholder" -> if (isEn) "Enter your full name" else "Masukkan nama lengkap"
            "phone_placeholder" -> if (isEn) "e.g. +62 812 3456 7890" else "Contoh: +62 812 3456 7890"
            "address_placeholder" -> if (isEn) "Enter your address" else "Masukkan alamat Anda"
            "change_photo_title" -> if (isEn) "Change Profile Picture" else "Ganti Foto Profil"
            "camera" -> if (isEn) "Take from Camera" else "Ambil dari Kamera"
            "gallery" -> if (isEn) "Choose from Gallery" else "Pilih dari Galeri"
            "camera_permission_denied" -> if (isEn) "Camera permission is required to take a photo" else "Izin kamera dibutuhkan untuk mengambil foto"
            "gagal_baca_gambar" -> if (isEn) "Failed to read image" else "Gagal membaca gambar"

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
            "help_content" -> if (isEn) "Please contact us at support@dinein.com or by phone at (021) 555-0199 for further assistance." else "Silakan hubungi kami di support@dinein.com atau via telepon di (021) 555-0199 untuk bantuan lebih lanjut."
            "close" -> if (isEn) "Close" else "Tutup"

            // Keamanan Screen
            "keamanan_akun" -> if (isEn) "Account Security" else "Keamanan Akun"
            "ganti_password" -> if (isEn) "Change Password" else "Ganti Password"
            "ganti_password_sub" -> if (isEn) "Last changed: 2 months ago" else "Terakhir diubah: 2 bulan lalu"
            "verifikasi_2" -> if (isEn) "Two-Step Verification" else "Verifikasi 2 Langkah"
            "verifikasi_2_sub" -> if (isEn) "Increase login security" else "Tingkatkan keamanan masuk"
            "perangkat" -> if (isEn) "Linked Devices" else "Perangkat Tertaut"
            "perangkat_sub" -> if (isEn) "Manage phone & web login" else "Kelola ponsel & web login"

            // Metode Pembayaran Screen
            "pembayaran_title" -> if (isEn) "Payment Method" else "Metode Pembayaran"
            "pilih_pembayaran" -> if (isEn) "Select Primary Payment Method" else "Pilih Metode Pembayaran Utama"
            "emoney_desc" -> if (isEn) "OVO, GoPay, ShopeePay" else "OVO, GoPay, ShopeePay"
            "mbanking_desc" -> if (isEn) "Virtual Account Bank Transfer" else "Transfer Virtual Account Bank"
            "qris_desc" -> if (isEn) "Scan QRIS code instantly" else "Scan kode QRIS instan"

            // Lokasi Restoran Screen
            "lokasi_title" -> if (isEn) "Restaurant Location" else "Lokasi Restoran"
            "resto_name" -> if (isEn) "Dine In Resto - Thamrin Branch" else "Dine In Resto - Cabang Thamrin"
            "resto_address" -> if (isEn) "Jl. M.H. Thamrin No.12, Menteng, Central Jakarta City, DKI Jakarta 10310" else "Jl. M.H. Thamrin No.12, Menteng, Kota Jakarta Pusat, DKI Jakarta 10310"
            "jam_buka" -> if (isEn) "Opening Hours" else "Jam Buka"
            "jam_buka_val" -> if (isEn) "10.00 - 22.00 WIB" else "10.00 - 22.00 WIB"
            "telepon" -> if (isEn) "Phone" else "Telepon"
            "telepon_val" -> if (isEn) "(021) 555-0199" else "(021) 555-0199"

            // Tentang Kami Screen
            "tentang_title" -> if (isEn) "About Us" else "Tentang Kami"
            "tentang_desc" -> if (isEn) "Dine In Resto is a modern mobile-based food ordering application designed to make it easy for customers when visiting or ordering from home. With native Jetpack Compose technology, we guarantee ultra-smooth performance and elegant, interactive UI design." else "Dine In Resto adalah aplikasi pemesanan makanan berbasis mobile modern yang dirancang untuk memberikan kemudahan bagi pelanggan saat berkunjung atau memesan dari rumah. Dengan teknologi asli Jetpack Compose, kami menjamin performa super mulus dan desain UI yang elegan dan interaktif."

            else -> key
        }
    }
}