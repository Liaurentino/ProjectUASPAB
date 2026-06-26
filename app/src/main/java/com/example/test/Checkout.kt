package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class Checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/w7ciuh2w_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rnalmo6xi1h) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/koq66y2f_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rppnefqhm4bm) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/6ksidxqi_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.ryutlwkfjjfj) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/yls82jqs_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rqjylxhnfko) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/uetqmeo9_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rg1sa5kr9aff) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/d4g2g4cc_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.r9gvboerbbha) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/ybca7gfx_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.r7ld021qliw5) as android.widget.ImageView?)!!)

        val button1 = findViewById<View>(R.id.r1o4joq8ngun)
        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Checkout, Konfirmasi::class.java)
                startActivity(intent)
            }
        })
    }
}