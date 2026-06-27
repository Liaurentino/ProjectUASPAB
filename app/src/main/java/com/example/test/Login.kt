package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_screen)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/fpisukz2_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rundefined) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/lqflpnnv_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.r7t1cmkza0w6) as android.widget.ImageView?)!!)

        val btnSignIn = findViewById<View>(R.id.rxigxg1euy9i)
        btnSignIn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Login, LoginScreenIN::class.java)
                startActivity(intent)
            }
        })

        val btnSignUp = findViewById<View>(R.id.rsewrkiggy3)
        btnSignUp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@Login, LoginScreen2::class.java)
                startActivity(intent)
            }
        })
    }
}
