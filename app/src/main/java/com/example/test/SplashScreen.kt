package com.example.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val img1 = findViewById<ImageView>(R.id.rbsdx7khb2f)
        val img2 = findViewById<ImageView>(R.id.r5gl1n0ozn8y)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/yb6s4aow_expires_30_days.png")
            .into(img1)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/szr8qrng_expires_30_days.png")
            .into(img2)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                // Berpindah ke LoginScreen setelah 2 detik
                val intent = Intent(this@SplashScreen, Login::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}
