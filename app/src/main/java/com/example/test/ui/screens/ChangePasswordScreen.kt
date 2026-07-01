package com.example.test.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.components.ResourceHelper
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed
import com.example.test.ui.viewmodel.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    viewModel: OrderViewModel,
    isUserLoggedIn: Boolean,
    onSuccessChanged: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val bgResId = ResourceHelper.getDrawableResId(context, "tees")
    val logoResId = ResourceHelper.getDrawableResId(context, "dineinlogo")

    @Composable
    fun ContentBody(isDark: Boolean) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isDark) {
                Spacer(modifier = Modifier.weight(0.2f))

                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "Logo",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Ubah Password",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Masukkan password baru Anda menggunakan koneksi aman Supabase.",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.weight(0.15f))
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Ubah Password Akun Anda",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "Gunakan kombinasi minimal 6 karakter dengan huruf dan angka untuk keamanan.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start).padding(top = 4.dp, bottom = 24.dp)
                )
            }

            // New Password input
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("Password Baru", color = if (isDark) Color.White.copy(alpha = 0.6f) else Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon", tint = if (isDark) Color.White else PrimaryRed) },
                trailingIcon = {
                    IconButton(onClick = { isNewPasswordVisible = !isNewPasswordVisible }, enabled = !isLoading) {
                        Icon(
                            imageVector = if (isNewPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = if (isDark) Color.White else Color.Gray
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = if (isDark) Color.White else Color.Black,
                    unfocusedTextColor = if (isDark) Color.White else Color.Black,
                    focusedBorderColor = PrimaryOrange,
                    unfocusedBorderColor = if (isDark) Color.White.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.5f),
                    cursorColor = PrimaryOrange
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password input
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Konfirmasi Password Baru", color = if (isDark) Color.White.copy(alpha = 0.6f) else Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon", tint = if (isDark) Color.White else PrimaryRed) },
                trailingIcon = {
                    IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }, enabled = !isLoading) {
                        Icon(
                            imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = if (isDark) Color.White else Color.Gray
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = if (isDark) Color.White else Color.Black,
                    unfocusedTextColor = if (isDark) Color.White else Color.Black,
                    focusedBorderColor = PrimaryOrange,
                    unfocusedBorderColor = if (isDark) Color.White.copy(alpha = 0.5f) else Color.Gray.copy(alpha = 0.5f),
                    cursorColor = PrimaryOrange
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Submit Button
            Button(
                onClick = {
                    val trimmedPass = newPassword.trim()
                    val trimmedConfirm = confirmPassword.trim()

                    if (trimmedPass.isEmpty()) {
                        Toast.makeText(context, "Password baru tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (trimmedPass.length < 6) {
                        Toast.makeText(context, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (trimmedPass != trimmedConfirm) {
                        Toast.makeText(context, "Konfirmasi password tidak cocok!", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true
                    viewModel.updatePassword(
                        newPassword = trimmedPass,
                        onSuccess = {
                            isLoading = false
                            Toast.makeText(context, "Password berhasil diubah!", Toast.LENGTH_SHORT).show()
                            onSuccessChanged()
                        },
                        onError = { errorMsg ->
                            isLoading = false
                            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryRed,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(text = "Simpan Password", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            if (isDark) {
                Spacer(modifier = Modifier.weight(0.4f))
            }
        }
    }

    if (isUserLoggedIn) {
        // Logged-in Settings Style
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Ubah Password", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBackClicked) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryRed,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            },
            modifier = modifier
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(paddingValues)
            ) {
                ContentBody(isDark = false)
            }
        }
    } else {
        // Logged-out Auth Style
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = bgResId),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )
                    )
            )
            IconButton(
                onClick = onBackClicked,
                modifier = Modifier
                    .padding(start = 16.dp, top = 40.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color.White
                )
            }
            ContentBody(isDark = true)
        }
    }
}
