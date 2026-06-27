package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class Pembayaran : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metodepembayaran)

        val img1 = findViewById<ImageView>(R.id.rjccj14873)
        val img2 = findViewById<ImageView>(R.id.rmwpskrixvof)
        val img3 = findViewById<ImageView>(R.id.r78tkmn6eupg)
        val img4 = findViewById<ImageView>(R.id.rrvwyr63ho)
        val img5 = findViewById<ImageView>(R.id.r041ted9f31le)
        val img6 = findViewById<ImageView>(R.id.rrcrl2auwu1j)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/6ecnqppk_expires_30_days.png")
            .into(img1)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/ttvw33ir_expires_30_days.png")
            .into(img2)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/eij1r35j_expires_30_days.png")
            .into(img3)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/a9xinn4p_expires_30_days.png")
            .into(img4)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/8pn2m7ro_expires_30_days.png")
            .into(img5)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/y5akinno_expires_30_days.png")
            .into(img6)

        val button1 = findViewById<View>(R.id.rv9f1jiwocja)
        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Pembayaran, Login::class.java)
                startActivity(intent)
            }
        })
    }
}