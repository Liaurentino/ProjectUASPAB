package com.example.test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class OrderReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "OrderReceiver"
        const val ACTION_ORDER_STATUS_CHANGED = "com.example.test.ORDER_STATUS_CHANGED"
        const val EXTRA_ORDER_STATUS = "order_status"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_ORDER_STATUS_CHANGED) {
            val status = intent.getStringExtra(EXTRA_ORDER_STATUS) ?: "UNKNOWN"
            Log.d(TAG, "Broadcast received: status = $status")

            val (title, message) = when (status) {
                "PLACED" -> Pair(
                    "Pesanan Berhasil Dibuat",
                    "Pesanan Anda berhasil dibuat dan restoran sedang mulai memproses."
                )
                "PREPARING" -> Pair(
                    "Pesanan Sedang Diproses",
                    "Makanan lezat Anda sedang disiapkan oleh koki restoran."
                )
                "PICKED_UP" -> Pair(
                    "Pesanan Dijemput Driver",
                    "Driver telah mengambil pesanan Anda dan siap mengantarkan."
                )
                "ON_THE_WAY" -> Pair(
                    "Pesanan Sedang Dikirim",
                    "Driver sedang dalam perjalanan mengantarkan hidangan Anda."
                )
                "ARRIVED" -> Pair(
                    "Pesanan Telah Sampai!",
                    "Driver telah sampai di lokasi Anda. Silakan ambil pesanan Anda."
                )
                "DELIVERED" -> Pair(
                    "Pesanan Selesai",
                    "Terima kasih telah memesan di DineIn Resto!"
                )
                else -> Pair(
                    "Status Pesanan Diperbarui",
                    "Status pesanan terbaru: $status"
                )
            }

            // Tampilkan Toast
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()

            // Tampilkan Notifikasi
            showNotification(context, title, message)
        }
    }

    private fun showNotification(context: Context, title: String, message: String) {
        val channelId = "order_channel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Status Pesanan",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel untuk notifikasi status pesanan"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Gunakan icon default dari android dengan warna putih netral untuk notifikasi system card
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(android.graphics.Color.WHITE)
            .setColorized(false)
            .setAutoCancel(true)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}
