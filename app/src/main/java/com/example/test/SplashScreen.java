package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView img1 = findViewById(R.id.rbsdx7khb2f);
        ImageView img2 = findViewById(R.id.r5gl1n0ozn8y);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/yb6s4aow_expires_30_days.png").into(img1);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/szr8qrng_expires_30_days.png").into(img2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Berpindah ke LoginScreen setelah 2 detik
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
