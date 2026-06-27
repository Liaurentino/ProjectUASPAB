package com.example.test

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class Konfirmasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/nydrv4o9_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.ruujtneij4rr) as android.widget.ImageView?)!!)

        // Tombol KEMBALI → ke MainActivity yang menampilkan MenuFragment
        val btnKonfirmasi = findViewById<LinearLayout>(R.id.btnKonfirmasi)
        btnKonfirmasi.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(this@Konfirmasi, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        })

        // Pop up langsung muncul saat halaman dibuka
        val dialog = Dialog(this@Konfirmasi)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_popup)

        val imgPopup = dialog.findViewById<ImageView>(R.id.imgPopup)
        imgPopup.setImageResource(R.drawable.popupkonfirmasi)

        val btnOk = dialog.findViewById<View>(R.id.btnOkDialog)
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
            }
        })

        dialog.show()
    }
}