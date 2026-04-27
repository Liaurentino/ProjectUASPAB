package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreenIN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/j1d82ihw_expires_30_days.png").into((ImageView) findViewById(R.id.rundefined));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/d46ssyrc_expires_30_days.png").into((ImageView) findViewById(R.id.r78afo2jhjj3));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/qhd1ijba_expires_30_days.png").into((ImageView) findViewById(R.id.raa63fl0jv2b));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/9tw4qaa8_expires_30_days.png").into((ImageView) findViewById(R.id.r03dkvm67d108));

        View button1 = findViewById(R.id.ryh616iwhif);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUser = findViewById(R.id.rzko26oxnhr8);
                String dataUsername = inputUser.getText().toString();

                Intent intent = new Intent(LoginScreenIN.this, Beranda.class);
                intent.putExtra("USER_NAME", dataUsername);
                startActivity(intent);
            }
        });
    }
}