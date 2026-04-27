package com.example.test;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/8m0qhz6b_expires_30_days.png").into(findViewById(R.id.rundefined));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/0yvr7h68_expires_30_days.png").into(findViewById(R.id.rz7jz03y7cjj));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/mk8x22dw_expires_30_days.png").into(findViewById(R.id.r6ij4cmxk9w8));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/r152v61q_expires_30_days.png").into(findViewById(R.id.r35breqfe0nz));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/mbdlqtxz_expires_30_days.png").into(findViewById(R.id.r8582tddd2h2));

        View button1 = findViewById(R.id.rk4i2fevfnk);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}