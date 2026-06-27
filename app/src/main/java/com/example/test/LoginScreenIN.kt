package com.example.test

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class LoginScreenIN : AppCompatActivity() {
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        // Background & Password Eye Icon (Glide)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/j1d82ihw_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.rundefined) as android.widget.ImageView?)!!)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/d46ssyrc_expires_30_days.png")
            .into((findViewById<android.view.View?>(R.id.iv_password_visibility_signin) as android.widget.ImageView?)!!)

        val etName = findViewById<EditText>(R.id.et_name_signin)
        val etPassword = findViewById<EditText>(R.id.et_password_signin)
        val ivPasswordVisibility = findViewById<ImageView>(R.id.iv_password_visibility_signin)

        // Toggle Password Visibility Logic
        ivPasswordVisibility.setOnClickListener(View.OnClickListener { v: View? ->
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                ivPasswordVisibility.setAlpha(0.5f)
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                ivPasswordVisibility.setAlpha(1.0f)
            }
            isPasswordVisible = !isPasswordVisible
            etPassword.setSelection(etPassword.getText().length)
        })

        // Logika Tombol Sign In dengan Validasi
        findViewById<View?>(R.id.ryh616iwhif).setOnClickListener { v ->
            val name = etName.getText().toString().trim { it <= ' ' }
            val password = etPassword.getText().toString().trim { it <= ' ' }

            // 1. Validasi: Cek Nama
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Validasi: Cek Password
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan nama ke SharedPreferences agar konsisten
            getSharedPreferences("user_prefs", MODE_PRIVATE)
                .edit()
                .putString("user_name", name)
                .putString("user_location", "Jl. Thamrin, Jakarta Pusat") // lokasi default
                .apply()

            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@LoginScreenIN, MainActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
            finish()
        }

        findViewById<View?>(R.id.r99bt2s9f0nm).setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(this@LoginScreenIN, LoginScreen2::class.java)
            )
        })
    }
}
