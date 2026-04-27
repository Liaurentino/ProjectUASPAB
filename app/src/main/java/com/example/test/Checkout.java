package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/w7ciuh2w_expires_30_days.png").into((ImageView) findViewById(R.id.rnalmo6xi1h));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/koq66y2f_expires_30_days.png").into((ImageView) findViewById(R.id.rppnefqhm4bm));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/6ksidxqi_expires_30_days.png").into((ImageView) findViewById(R.id.ryutlwkfjjfj));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/yls82jqs_expires_30_days.png").into((ImageView) findViewById(R.id.rqjylxhnfko));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/uetqmeo9_expires_30_days.png").into((ImageView) findViewById(R.id.rg1sa5kr9aff));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/d4g2g4cc_expires_30_days.png").into((ImageView) findViewById(R.id.r9gvboerbbha));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/ybca7gfx_expires_30_days.png").into((ImageView) findViewById(R.id.r7ld021qliw5));

        View button1 = findViewById(R.id.r1o4joq8ngun);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}