package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class Menu4 extends AppCompatActivity {
    
    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu4);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/3saa05g5_expires_30_days.png").into((ImageView) findViewById(R.id.r1q7wr4m0m4l));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/jntvqahe_expires_30_days.png").into((ImageView) findViewById(R.id.rztt6uczlsup));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/tkhehjzj_expires_30_days.png").into((ImageView) findViewById(R.id.rv8damok2bo));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/dohcdz26_expires_30_days.png").into((ImageView) findViewById(R.id.rj1o4sbq14hg));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/1x90booo_expires_30_days.png").into((ImageView) findViewById(R.id.rjgg3ff7kt6));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/4pf9jxs3_expires_30_days.png").into((ImageView) findViewById(R.id.ru695zb88eb7));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/xg6a8rch_expires_30_days.png").into((ImageView) findViewById(R.id.ro696vbi1nxh));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/g6fsb7l6_expires_30_days.png").into((ImageView) findViewById(R.id.rgmzj4til4ic));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/i0z8ekq3_expires_30_days.png").into((ImageView) findViewById(R.id.rs8dr5lwi1i));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/dh9ukopt_expires_30_days.png").into((ImageView) findViewById(R.id.r7q2in75qcy));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/6knwkumd_expires_30_days.png").into((ImageView) findViewById(R.id.r3z4dem8md2));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/9n3s5c53_expires_30_days.png").into((ImageView) findViewById(R.id.rlv1cpmvd7z));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/hdcm6tbp_expires_30_days.png").into((ImageView) findViewById(R.id.reoitxzp20k5));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/c5fqm38e_expires_30_days.png").into((ImageView) findViewById(R.id.rotegnwoda9q));

        EditText editText1 = findViewById(R.id.rofpiyggb52);
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
    }
}