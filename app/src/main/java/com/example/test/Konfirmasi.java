package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Konfirmasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/nydrv4o9_expires_30_days.png").into((ImageView) findViewById(R.id.ruujtneij4rr));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/lmpiaf65_expires_30_days.png").into((ImageView) findViewById(R.id.rddy491pwd1p));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/exunqjsi_expires_30_days.png").into((ImageView) findViewById(R.id.ru9fu5p009jf));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/mqv3c3kc_expires_30_days.png").into((ImageView) findViewById(R.id.rvg6uoz7klzl));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/u677ku9f_expires_30_days.png").into((ImageView) findViewById(R.id.rnca2cynscwh));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/vxjs3r7o_expires_30_days.png").into((ImageView) findViewById(R.id.rk7gyoz68adj));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/934wxzwg_expires_30_days.png").into((ImageView) findViewById(R.id.r1y1k56anw6t));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/86bcdw8n_expires_30_days.png").into((ImageView) findViewById(R.id.rcoiwzj8p7x));

        View button1 = findViewById(R.id.ra1a2kvn81qh);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}