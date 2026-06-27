package com.example.test

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class LoginScreen2 : AppCompatActivity() {
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen2)

        val imgBackground = findViewById<ImageView>(R.id.rundefined)
        val imgIconPassword = findViewById<ImageView>(R.id.rc1yr2xrh65)
        val etEmail = findViewById<EditText>(R.id.et_email_signup)
        val etName = findViewById<EditText>(R.id.et_name_signup)
        val etPassword = findViewById<EditText>(R.id.et_password_signup)

        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/84bypqiw_expires_30_days.png")
            .into(imgBackground)
        Glide.with(this)
            .load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/zfih8wdl_expires_30_days.png")
            .into(imgIconPassword)

        imgIconPassword.setOnClickListener(View.OnClickListener { v: View? ->
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                imgIconPassword.setAlpha(0.5f)
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                imgIconPassword.setAlpha(1.0f)
            }
            isPasswordVisible = !isPasswordVisible
            etPassword.setSelection(etPassword.getText().length)
        })

        val btnSignUp = findViewById<View>(R.id.r5ph8q4nanb)
        btnSignUp.setOnClickListener { v ->
            val email = etEmail.getText().toString().trim { it <= ' ' }
            val name = etName.getText().toString().trim { it <= ' ' }
            val password = etPassword.getText().toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password) || password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            getSharedPreferences("user_prefs", MODE_PRIVATE)
                .edit()
                .putString("user_name", name)
                .putString("user_email", email)
                .apply()

            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginScreen2, MainActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
            finish()
        }

        val txtSignIn = findViewById<TextView>(R.id.rwrrwtdqk3o)
        txtSignIn.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@LoginScreen2, LoginScreenIN::class.java))
            finish()
        })
    }
}