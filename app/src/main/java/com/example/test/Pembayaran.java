package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Pembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        ImageView img1 = findViewById(R.id.rjccj14873);
        ImageView img2 = findViewById(R.id.rmwpskrixvof);
        ImageView img3 = findViewById(R.id.r78tkmn6eupg);
        ImageView img4 = findViewById(R.id.rrvwyr63ho);
        ImageView img5 = findViewById(R.id.r041ted9f31le);
        ImageView img6 = findViewById(R.id.rrcrl2auwu1j);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/6ecnqppk_expires_30_days.png").into(img1);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/ttvw33ir_expires_30_days.png").into(img2);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/eij1r35j_expires_30_days.png").into(img3);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/a9xinn4p_expires_30_days.png").into(img4);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/8pn2m7ro_expires_30_days.png").into(img5);
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/6HZo2qNxTI/y5akinno_expires_30_days.png").into(img6);

        View button1 = findViewById(R.id.rv9f1jiwocja);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pembayaran.this, Login.class);
                startActivity(intent);
            }
        });
    }
}