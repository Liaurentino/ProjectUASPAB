package com.example.test.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed
import com.example.test.ui.theme.SuccessGreen
import com.example.test.ui.viewmodel.OrderStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusPesananScreen(
    currentStatus: OrderStatus,
    onConfirmDelivery: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lacak Pesanan", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
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
            // Driver Card Info
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(PrimaryOrange.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Driver Pic",
                            tint = PrimaryRed,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Rian Hidayat",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Dine In Delivery Driver",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp)
                            .background(SuccessGreen.copy(alpha = 0.15f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Hubungi",
                            tint = SuccessGreen,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Order Tracking Steps
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Status Pengantaran",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TrackingStepRow(
                        title = "Pesanan Dibuat",
                        description = "Pesanan Anda berhasil dikirim ke restoran.",
                        isCompleted = true,
                        isCurrent = false
                    )

                    TrackingStepRow(
                        title = "Sedang Memasak",
                        description = "Koki kami sedang menyajikan makanan terbaik.",
                        isCompleted = currentStatus >= OrderStatus.PREPARING,
                        isCurrent = currentStatus == OrderStatus.PREPARING
                    )

                    TrackingStepRow(
                        title = "Makanan Diambil Driver",
                        description = "Driver sedang menjemput makanan Anda.",
                        isCompleted = currentStatus >= OrderStatus.PICKED_UP,
                        isCurrent = currentStatus == OrderStatus.PICKED_UP
                    )

                    TrackingStepRow(
                        title = "Dalam Perjalanan",
                        description = "Driver menuju ke tempat Anda.",
                        isCompleted = currentStatus >= OrderStatus.ON_THE_WAY,
                        isCurrent = currentStatus == OrderStatus.ON_THE_WAY
                    )

                    TrackingStepRow(
                        title = "Sampai Tujuan",
                        description = "Makanan hangat siap Anda santap!",
                        isCompleted = currentStatus == OrderStatus.ARRIVED,
                        isCurrent = currentStatus == OrderStatus.ARRIVED
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action button to confirm food delivery receipt
            Button(
                onClick = onConfirmDelivery,
                enabled = currentStatus == OrderStatus.ARRIVED,
                colors = ButtonDefaults.buttonColors(
                    containerColor = SuccessGreen,
                    disabledContainerColor = Color.LightGray
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = if (currentStatus == OrderStatus.ARRIVED) "Konfirmasi Terima Makanan" else "Menunggu Pengantaran...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TrackingStepRow(
    title: String,
    description: String,
    isCompleted: Boolean,
    isCurrent: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = when {
                            isCurrent -> PrimaryOrange
                            isCompleted -> SuccessGreen
                            else -> Color(0xFFE0E0E0)
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted && !isCurrent) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Done",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = when {
                    isCurrent -> PrimaryOrange
                    isCompleted -> SuccessGreen
                    else -> Color.Gray
                }
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
