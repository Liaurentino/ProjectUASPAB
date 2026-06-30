package com.example.test.ui.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.test.ui.theme.PrimaryRed
import java.io.File
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfilScreen(
    userName: String,
    userEmail: String,
    userPhone: String,
    userLocation: String,
    profileImageUrl: String,
    isUploadingImage: Boolean,
    onSave: (name: String, phone: String, address: String) -> Unit,
    onUploadImage: (imageBytes: ByteArray, fileExtension: String) -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var editedName by remember { mutableStateOf(userName) }
    var editedPhone by remember { mutableStateOf(userPhone) }
    var editedAddress by remember { mutableStateOf(userLocation) }

    // Uri foto yang baru dipilih (gallery atau kamera), dipakai untuk preview sebelum/selagi upload
    var localPreviewUri by remember { mutableStateOf<Uri?>(null) }
    var showImageSourceDialog by remember { mutableStateOf(false) }
    var cameraTempUri by remember { mutableStateOf<Uri?>(null) }
    var pendingCameraPermission by remember { mutableStateOf(false) }

    // ---- Launcher: pilih dari Gallery ----
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            localPreviewUri = uri
            uploadImageFromUri(context, uri, onUploadImage)
        }
    }

    // ---- Launcher: ambil foto dari Camera ----
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success && cameraTempUri != null) {
            localPreviewUri = cameraTempUri
            uploadImageFromUri(context, cameraTempUri!!, onUploadImage)
        }
    }

    // ---- Launcher: minta izin kamera, baru buka kamera kalau granted ----
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted: Boolean ->
        if (granted) {
            val newUri = createCameraImageUri(context)
            cameraTempUri = newUri
            cameraLauncher.launch(newUri)
        } else {
            Toast.makeText(context, "Izin kamera dibutuhkan untuk mengambil foto", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profil", fontWeight = FontWeight.Bold) },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clickable(enabled = !isUploadingImage) { showImageSourceDialog = true },
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(PrimaryRed.copy(alpha = 0.1f), CircleShape)
                        .border(2.dp, PrimaryRed, CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    val displayImage = localPreviewUri?.toString() ?: profileImageUrl
                    if (displayImage.isNotEmpty()) {
                        AsyncImage(
                            model = displayImage,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(100.dp),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Picture",
                            tint = PrimaryRed,
                            modifier = Modifier.size(56.dp)
                        )
                    }

                    if (isUploadingImage) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Black.copy(alpha = 0.4f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(28.dp))
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(PrimaryRed, CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Ganti Foto",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isUploadingImage) "Mengupload foto..." else "Tap untuk ganti foto",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    EditField(label = "Nama Lengkap", value = editedName, onValueChange = { editedName = it }, icon = Icons.Default.Person, placeholder = "Masukkan nama lengkap")
                    Spacer(modifier = Modifier.height(16.dp))
                    EditField(label = "Email", value = userEmail, onValueChange = {}, icon = Icons.Default.Email, placeholder = "Email", enabled = false)
                    Spacer(modifier = Modifier.height(16.dp))
                    EditField(label = "Nomor Telepon", value = editedPhone, onValueChange = { editedPhone = it }, icon = Icons.Default.Phone, placeholder = "Contoh: +62 812 3456 7890")
                    Spacer(modifier = Modifier.height(16.dp))
                    EditField(label = "Alamat", value = editedAddress, onValueChange = { editedAddress = it }, icon = Icons.Default.LocationOn, placeholder = "Masukkan alamat Anda")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    onSave(editedName, editedPhone, editedAddress)
                    onBackClicked()
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(52.dp)
            ) {
                Text(text = "Simpan Perubahan", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Dialog pilihan sumber foto: Kamera atau Galeri
    if (showImageSourceDialog) {
        AlertDialog(
            onDismissRequest = { showImageSourceDialog = false },
            title = { Text("Ganti Foto Profil") },
            text = {
                Column {
                    Text(
                        text = "Ambil dari Kamera",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showImageSourceDialog = false
                                cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                            }
                            .padding(vertical = 12.dp)
                    )
                    Text(
                        text = "Pilih dari Galeri",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showImageSourceDialog = false
                                galleryLauncher.launch("image/*")
                            }
                            .padding(vertical = 12.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showImageSourceDialog = false }) {
                    Text("Batal", color = PrimaryRed)
                }
            }
        )
    }
}

/**
 * Membuat Uri sementara di cache folder (lewat FileProvider) untuk menampung hasil foto kamera.
 */
private fun createCameraImageUri(context: Context): Uri {
    val imagesDir = File(context.cacheDir, "images").apply { mkdirs() }
    val imageFile = File(imagesDir, "profile_${System.currentTimeMillis()}.jpg")
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        imageFile
    )
}

/**
 * Membaca bytes dari Uri (baik dari galeri maupun kamera) lalu memanggil callback upload.
 */
private fun uploadImageFromUri(
    context: Context,
    uri: Uri,
    onUploadImage: (imageBytes: ByteArray, fileExtension: String) -> Unit
) {
    try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = ByteArrayOutputStream()
        inputStream?.use { input ->
            input.copyTo(outputStream)
        }
        val bytes = outputStream.toByteArray()

        // Coba deteksi ekstensi dari mime type, default ke jpg
        val mimeType = context.contentResolver.getType(uri)
        val extension = when {
            mimeType?.contains("png") == true -> "png"
            mimeType?.contains("webp") == true -> "webp"
            else -> "jpg"
        }

        onUploadImage(bytes, extension)
    } catch (e: Exception) {
        Toast.makeText(context, "Gagal membaca gambar: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun EditField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    enabled: Boolean = true
) {
    Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = label, tint = if (enabled) PrimaryRed else Color.LightGray, modifier = Modifier.size(20.dp))
        },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryRed,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            disabledBorderColor = Color(0xFFEEEEEE),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color(0xFFF5F5F5)
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}