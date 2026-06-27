package com.example.test.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed

@Composable
fun ProfilScreen(
    userName: String,
    userEmail: String,
    onNavigateToSettings: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Profile Header Section with elegant gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryRed)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Initials Circle
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Pic",
                        tint = PrimaryRed,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userName.ifEmpty { "User" },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = userEmail.ifEmpty { "user@example.com" },
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Options List Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column {
                ProfileOptionRow(
                    title = "Pengaturan",
                    icon = Icons.Default.Settings,
                    onClick = { onNavigateToSettings("pengaturan") }
                )
                Divider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 16.dp))
                
                ProfileOptionRow(
                    title = "Keamanan",
                    icon = Icons.Default.Security,
                    onClick = { onNavigateToSettings("keamanan") }
                )
                Divider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 16.dp))
                
                ProfileOptionRow(
                    title = "Metode Pembayaran",
                    icon = Icons.Default.Payment,
                    onClick = { onNavigateToSettings("metode_pembayaran") }
                )
                Divider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 16.dp))
                
                ProfileOptionRow(
                    title = "Lokasi Restoran",
                    icon = Icons.Default.LocationOn,
                    onClick = { onNavigateToSettings("lokasi_restoran") }
                )
                Divider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 16.dp))
                
                ProfileOptionRow(
                    title = "Tentang Kami",
                    icon = Icons.Default.Info,
                    onClick = { onNavigateToSettings("tentang_kami") }
                )
            }
        }
    }
}

@Composable
fun ProfileOptionRow(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = PrimaryRed,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "Forward Arrow",
            tint = Color.LightGray,
            modifier = Modifier.size(12.dp)
        )
    }
}
