package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);

        ImageView imgBackground = findViewById(R.id.rundefined);
        ImageView imgIconPassword = findViewById(R.id.rc1yr2xrh65);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/84bypqiw_expires_30_days.png").into(imgBackground);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/zfih8wdl_expires_30_days.png").into(imgIconPassword);

        View btnKembali = findViewById(R.id.r5ph8q4nanb);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView txtSignIn = findViewById(R.id.rwrrwtdqk3o);
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen2.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}