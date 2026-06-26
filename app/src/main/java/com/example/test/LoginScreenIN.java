package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreenIN extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Background & Password Eye Icon (Glide)
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/j1d82ihw_expires_30_days.png").into((ImageView) findViewById(R.id.rundefined));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/d46ssyrc_expires_30_days.png").into((ImageView) findViewById(R.id.iv_password_visibility_signin));

        EditText etName = findViewById(R.id.et_name_signin);
        EditText etPassword = findViewById(R.id.et_password_signin);
        ImageView ivPasswordVisibility = findViewById(R.id.iv_password_visibility_signin);

        // Toggle Password Visibility Logic
        ivPasswordVisibility.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivPasswordVisibility.setAlpha(0.5f);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivPasswordVisibility.setAlpha(1.0f);
            }
            isPasswordVisible = !isPasswordVisible;
            etPassword.setSelection(etPassword.getText().length());
        });

        // Logika Tombol Sign In dengan Validasi
        findViewById(R.id.ryh616iwhif).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // 1. Validasi: Cek Nama
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2. Validasi: Cek Password
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan nama ke SharedPreferences agar konsisten
            getSharedPreferences("user_prefs", MODE_PRIVATE)
                    .edit()
                    .putString("user_name", name)
                    .putString("user_location", "Jl. Thamrin, Jakarta Pusat") // lokasi default
                    .apply();

            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginScreenIN.this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.r99bt2s9f0nm).setOnClickListener(v -> {
            startActivity(new Intent(LoginScreenIN.this, LoginScreen2.class));
        });
    }
}
