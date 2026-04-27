package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/fpisukz2_expires_30_days.png").into((ImageView) findViewById(R.id.rundefined));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/i2z1n29u_expires_30_days.png").into((ImageView) findViewById(R.id.rtzm95aptlzk));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/lqflpnnv_expires_30_days.png").into((ImageView) findViewById(R.id.r7t1cmkza0w6));

        View btnSignIn = findViewById(R.id.rxigxg1euy9i);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginScreenIN.class);
                startActivity(intent);
            }
        });

        View btnSignUp = findViewById(R.id.rsewrkiggy3);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginScreen2.class);
                startActivity(intent);
            }
        });
    }
}