package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class StatusPesanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/gwaulwl6_expires_30_days.png").into((ImageView) findViewById(R.id.rzghmdzapgo));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/1pchvj0a_expires_30_days.png").into((ImageView) findViewById(R.id.r597xnat44w5));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/pllj063j_expires_30_days.png").into((ImageView) findViewById(R.id.rdez5v652zc9));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/9h3fm2k2_expires_30_days.png").into((ImageView) findViewById(R.id.ru5xnx0g1t4m));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/72gv5zsp_expires_30_days.png").into((ImageView) findViewById(R.id.rvg7o84fek5));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/22lq23fn_expires_30_days.png").into((ImageView) findViewById(R.id.rjigabodj8ci));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/k8du0x31_expires_30_days.png").into((ImageView) findViewById(R.id.ryedc2yfthp9));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/taq630eg_expires_30_days.png").into((ImageView) findViewById(R.id.rvzgfbmwqzya));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/pa4vzam8_expires_30_days.png").into((ImageView) findViewById(R.id.r1drzxopde44));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/ewypca1r_expires_30_days.png").into((ImageView) findViewById(R.id.rfqqw3wucb04));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/92jek4so_expires_30_days.png").into((ImageView) findViewById(R.id.rchquzm8t6p));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vrc2vg2x_expires_30_days.png").into((ImageView) findViewById(R.id.rpzu3874irv));

        View button1 = findViewById(R.id.rpfkap59vcii);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button2 = findViewById(R.id.rdyw1nmmqih);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}