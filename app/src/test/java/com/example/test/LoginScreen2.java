package com.example.test;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class LoginScreen2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/84bypqiw_expires_30_days.png").into(findViewById(R.id.rundefined));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/zfih8wdl_expires_30_days.png").into(findViewById(R.id.rc1yr2xrh65));

        View button1 = findViewById(R.id.r5ph8q4nanb);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}