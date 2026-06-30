package com.example.test.ui.screens

import android.content.Context
import com.example.test.data.LanguageManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.components.ResourceHelper
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed
import com.example.test.ui.theme.SuccessGreen

import androidx.compose.runtime.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PengaturanScreen(
    onNavigateToEditProfil: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val sharedPrefs = remember { context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE) }
    var isNotificationOn by remember { mutableStateOf(sharedPrefs.getBoolean("notifications_enabled", true)) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showHelpDialog by remember { mutableStateOf(false) }

    val languageLabel = if (LanguageManager.currentLanguage == "en") "English" else "Bahasa Indonesia"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(LanguageManager.get("app_settings"), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(padding)
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    SettingOptionItem(
                        title = LanguageManager.get("change_profile"),
                        subtitle = LanguageManager.get("change_profile_sub"),
                        onClick = onNavigateToEditProfil
                    )
                    Divider(color = Color(0xFFF5F5F5))
                    SettingSwitchItem(
                        title = LanguageManager.get("notifications"),
                        subtitle = LanguageManager.get("notifications_sub"),
                        isChecked = isNotificationOn,
                        onCheckedChange = { checked ->
                            isNotificationOn = checked
                            sharedPrefs.edit().putBoolean("notifications_enabled", checked).apply()
                        }
                    )
                    Divider(color = Color(0xFFF5F5F5))
                    SettingOptionItem(
                        title = LanguageManager.get("language"),
                        subtitle = languageLabel,
                        onClick = { showLanguageDialog = true }
                    )
                    Divider(color = Color(0xFFF5F5F5))
                    SettingOptionItem(
                        title = LanguageManager.get("help_faq"),
                        subtitle = LanguageManager.get("help_faq_sub"),
                        onClick = { showHelpDialog = true }
                    )
                }
            }
        }

        if (showLanguageDialog) {
            AlertDialog(
                onDismissRequest = { showLanguageDialog = false },
                title = { Text(LanguageManager.get("choose_language")) },
                text = {
                    Column {
                        Text(
                            text = "Bahasa Indonesia",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    LanguageManager.setLanguage(context, "id")
                                    showLanguageDialog = false
                                }
                                .padding(vertical = 12.dp)
                        )
                        Text(
                            text = "English",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    LanguageManager.setLanguage(context, "en")
                                    showLanguageDialog = false
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showLanguageDialog = false }) {
                        Text(LanguageManager.get("cancel"), color = PrimaryRed)
                    }
                }
            )
        }

        if (showHelpDialog) {
            AlertDialog(
                onDismissRequest = { showHelpDialog = false },
                title = { Text(LanguageManager.get("help_faq")) },
                text = { Text("Silakan hubungi kami di support@dinein.com atau via telepon di (021) 555-0199 untuk bantuan lebih lanjut.") },
                confirmButton = {
                    TextButton(onClick = { showHelpDialog = false }) {
                        Text("Tutup", color = PrimaryRed)
                    }
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeamananScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Keamanan Akun", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    SettingOptionItem(title = "Ganti Password", subtitle = "Terakhir diubah: 2 bulan lalu")
                    Divider(color = Color(0xFFF5F5F5))
                    SettingOptionItem(title = "Verifikasi 2 Langkah", subtitle = "Tingkatkan keamanan masuk")
                    Divider(color = Color(0xFFF5F5F5))
                    SettingOptionItem(title = "Perangkat Tertaut", subtitle = "Kelola ponsel & web login")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetodePembayaranScreen(
    currentMethod: String,
    onMethodSelected: (String) -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Metode Pembayaran", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Text(
                text = "Pilih Metode Pembayaran Utama",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    PaymentMethodItem(
                        name = "E-Money",
                        description = "OVO, GoPay, ShopeePay",
                        iconRes = "iconemoney",
                        isSelected = currentMethod == "E-Money",
                        onClick = { onMethodSelected("E-Money") }
                    )
                    Divider(color = Color(0xFFF5F5F5))
                    PaymentMethodItem(
                        name = "M-Banking",
                        description = "Transfer Virtual Account Bank",
                        iconRes = "iconmbanking",
                        isSelected = currentMethod == "M-Banking",
                        onClick = { onMethodSelected("M-Banking") }
                    )
                    Divider(color = Color(0xFFF5F5F5))
                    PaymentMethodItem(
                        name = "QRIS",
                        description = "Scan kode QRIS instan",
                        iconRes = "iconqris",
                        isSelected = currentMethod == "QRIS",
                        onClick = { onMethodSelected("QRIS") }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LokasiRestoranScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lokasi Restoran", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    val mapResId = ResourceHelper.getDrawableResId(context, "tees")
                    Image(
                        painter = painterResource(id = mapResId),
                        contentDescription = "Peta Lokasi",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.2f))
                    )
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Pin Resto",
                        tint = PrimaryRed,
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Dine In Resto - Cabang Thamrin",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Jl. M.H. Thamrin No.12, Menteng, Kota Jakarta Pusat, DKI Jakarta 10310",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color(0xFFF0F0F0))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Jam Buka", fontSize = 12.sp, color = Color.Gray)
                            Text(text = "10.00 - 22.00 WIB", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                        }
                        Column {
                            Text(text = "Telepon", fontSize = 12.sp, color = Color.Gray)
                            Text(text = "(021) 555-0199", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TentangKamiScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tentang Kami", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logoResId = ResourceHelper.getDrawableResId(context, "dineinlogo")
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Dine In Resto",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Version 1.0.0 (Premium Compose Edition)",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Text(
                    text = "Dine In Resto adalah aplikasi pemesanan makanan berbasis mobile modern yang dirancang untuk memberikan kemudahan bagi pelanggan saat berkunjung atau memesan dari rumah. Dengan teknologi asli Jetpack Compose, kami menjamin performa super mulus dan desain UI yang elegan dan interaktif.",
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}

@Composable
fun SettingOptionItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "Forward",
            tint = Color.LightGray,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Composable
fun SettingSwitchItem(
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = subtitle, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryRed
            )
        )
    }
}

@Composable
fun PaymentMethodItem(
    name: String,
    description: String,
    iconRes: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconId = ResourceHelper.getDrawableResId(context, iconRes)
        Image(
            painter = painterResource(id = iconId),
            contentDescription = name,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 2.dp))
        }
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Settings, // placeholder or check
                contentDescription = "Selected",
                tint = SuccessGreen,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Choose",
                tint = Color.LightGray,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}