package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class LokasiResotran extends AppCompatActivity {
    
    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_resotran);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/kskckrmg_expires_30_days.png").into((ImageView) findViewById(R.id.rd4uam1he776));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/a902pqrq_expires_30_days.png").into((ImageView) findViewById(R.id.r4hrhuoi34aq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/6il7eh7j_expires_30_days.png").into((ImageView) findViewById(R.id.r11v2yywiaydq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/ot52117a_expires_30_days.png").into((ImageView) findViewById(R.id.rvbzzthgpu2q));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/old66hqj_expires_30_days.png").into((ImageView) findViewById(R.id.rxmepcl4jm8g));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/lt7r2wih_expires_30_days.png").into((ImageView) findViewById(R.id.rd6km2wrofxm));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/m1db9z1a_expires_30_days.png").into((ImageView) findViewById(R.id.r96s6fyob67w));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/cpf3tfgu_expires_30_days.png").into((ImageView) findViewById(R.id.rdgy6ptchjer));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/kmgtztqg_expires_30_days.png").into((ImageView) findViewById(R.id.ru5pqsayx3xm));

        EditText editText1 = findViewById(R.id.rgy97utuzzss);
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // before Text Changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    editTextValue1 = s.toString();  // on Text Changed
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // after Text Changed
            }
        });

        View button1 = findViewById(R.id.rtejns6je4v);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button2 = findViewById(R.id.r3ii0ynr89ye);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}