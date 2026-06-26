package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreen2 extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);

        ImageView imgBackground = findViewById(R.id.rundefined);
        ImageView imgIconPassword = findViewById(R.id.rc1yr2xrh65);
        EditText etEmail = findViewById(R.id.et_email_signup);
        EditText etName = findViewById(R.id.et_name_signup);
        EditText etPassword = findViewById(R.id.et_password_signup);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/84bypqiw_expires_30_days.png").into(imgBackground);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/zfih8wdl_expires_30_days.png").into(imgIconPassword);

        imgIconPassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgIconPassword.setAlpha(0.5f);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imgIconPassword.setAlpha(1.0f);
            }
            isPasswordVisible = !isPasswordVisible;
            etPassword.setSelection(etPassword.getText().length());
        });

        View btnSignUp = findViewById(R.id.r5ph8q4nanb);
        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password) || password.length() < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                return;
            }

            getSharedPreferences("user_prefs", MODE_PRIVATE)
                    .edit()
                    .putString("user_name", name)
                    .putString("user_email", email)
                    .apply();

            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginScreen2.this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
            finish();
        });

        TextView txtSignIn = findViewById(R.id.rwrrwtdqk3o);
        txtSignIn.setOnClickListener(v -> {
            startActivity(new Intent(LoginScreen2.this, LoginScreenIN.class));
            finish();
        });
    }
}