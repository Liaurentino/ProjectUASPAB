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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed
import com.example.test.ui.theme.SuccessGreen
import com.example.test.ui.viewmodel.OrderStatus

@Composable
fun PesananScreen(
    activeStatus: OrderStatus,
    onTrackOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Navigation / Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryRed)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Pesanan Saya",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Pantau status hidangan lezat Anda",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        if (activeStatus == OrderStatus.NONE) {
            // Empty State
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Receipt,
                        contentDescription = "No Orders",
                        tint = Color.LightGray,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Belum Ada Pesanan Aktif",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Yuk, pilih menu masakan terbaik kami di halaman Menu dan buat pesanan pertamamu!",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        } else {
            // Active Order list
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Pesanan Berlangsung",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(PrimaryOrange.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = when(activeStatus) {
                                        OrderStatus.PREPARING -> Icons.Default.Restaurant
                                        OrderStatus.PICKED_UP -> Icons.Default.DirectionsRun
                                        OrderStatus.ON_THE_WAY -> Icons.Default.DirectionsRun
                                        OrderStatus.ARRIVED -> Icons.Default.CheckCircle
                                        else -> Icons.Default.Info
                                    },
                                    contentDescription = "Status Icon",
                                    tint = PrimaryRed,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Dine In Resto - Pesanan #1042",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = when (activeStatus) {
                                        OrderStatus.PREPARING -> "Sedang Memasak \uD83C\uDF73"
                                        OrderStatus.PICKED_UP -> "Driver Menjemput \uD83C\uDFC3"
                                        OrderStatus.ON_THE_WAY -> "Dalam Perjalanan \uD83D\uDEF5"
                                        OrderStatus.ARRIVED -> "Makanan Sampai! \uD83C\uDF89"
                                        else -> ""
                                    },
                                    fontSize = 13.sp,
                                    color = if (activeStatus == OrderStatus.ARRIVED) SuccessGreen else PrimaryOrange,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Status Tracker description
                        Text(
                            text = when (activeStatus) {
                                OrderStatus.PREPARING -> "Koki kami sedang meracik bumbu dan memasak pesanan Anda secara higienis."
                                OrderStatus.PICKED_UP -> "Makanan selesai dimasak! Kurir sedang mengambil makanan untuk diantarkan."
                                OrderStatus.ON_THE_WAY -> "Driver sedang meluncur menuju ke alamat tujuan Anda. Mohon ditunggu ya."
                                OrderStatus.ARRIVED -> "Driver telah sampai di tujuan Anda. Nikmati sajian hangat kami!"
                                else -> ""
                            },
                            fontSize = 13.sp,
                            color = Color.Gray,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = onTrackOrder,
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                        ) {
                            Text(
                                text = if (activeStatus == OrderStatus.ARRIVED) "Konfirmasi Terima" else "Lacak Pesanan",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
