package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class Menu2 extends AppCompatActivity {
    
    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/e3ogaqyq_expires_30_days.png").into((ImageView) findViewById(R.id.rokoaxgrq8b8));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/ruhmv4k0_expires_30_days.png").into((ImageView) findViewById(R.id.rfgxtxnuwcn));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/cjrfwn27_expires_30_days.png").into((ImageView) findViewById(R.id.r4ynpounshs6));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/hhjjnkzb_expires_30_days.png").into((ImageView) findViewById(R.id.r16te3u26feb));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/r1jlboml_expires_30_days.png").into((ImageView) findViewById(R.id.rjl30nloeph));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/7mblel21_expires_30_days.png").into((ImageView) findViewById(R.id.rw8h7oj1a3hn));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/tgtm1tva_expires_30_days.png").into((ImageView) findViewById(R.id.rb8wy21a3nzo));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vwljcd7r_expires_30_days.png").into((ImageView) findViewById(R.id.rekh4tlmkpdc));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/an1gzmms_expires_30_days.png").into((ImageView) findViewById(R.id.r83qb524x2dw));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/xv6bt750_expires_30_days.png").into((ImageView) findViewById(R.id.r92ezagq0rfs));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/5qoc32wg_expires_30_days.png").into((ImageView) findViewById(R.id.rd9f9c5gnm));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/m0bdlu15_expires_30_days.png").into((ImageView) findViewById(R.id.r1rbvmjipy7g));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/4uhhz0vx_expires_30_days.png").into((ImageView) findViewById(R.id.rpa47mz0uzqo));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/appi56gb_expires_30_days.png").into((ImageView) findViewById(R.id.rddymw7wz51l));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/gg1loxxu_expires_30_days.png").into((ImageView) findViewById(R.id.rt36l6c3wl0r));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/6x39b9l3_expires_30_days.png").into((ImageView) findViewById(R.id.rhoota0zv2d));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/kitc882x_expires_30_days.png").into((ImageView) findViewById(R.id.r5siyeidaldt));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vose96mf_expires_30_days.png").into((ImageView) findViewById(R.id.rn0y9p1plwaa));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/i0zwrmaf_expires_30_days.png").into((ImageView) findViewById(R.id.rd8evabmlggm));

        EditText editText1 = findViewById(R.id.rayso7uozphc);
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

        View button1 = findViewById(R.id.rkkaahvp6l68);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}