package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class Menu1 extends AppCompatActivity {
    
    private String editTextValue1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/poon99c4_expires_30_days.png").into((ImageView) findViewById(R.id.rmdr4vjmjhh));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/i4v9txuy_expires_30_days.png").into((ImageView) findViewById(R.id.rt4or5jpe0s));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/cjl1oedf_expires_30_days.png").into((ImageView) findViewById(R.id.r0es9egmmnif4));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/xa7ifknz_expires_30_days.png").into((ImageView) findViewById(R.id.rwmt2yq2mlfa));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/npslswa7_expires_30_days.png").into((ImageView) findViewById(R.id.rvay85n0us8l));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/rriakon0_expires_30_days.png").into((ImageView) findViewById(R.id.r0oqgb15dk8zk));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/kpv1lsx3_expires_30_days.png").into((ImageView) findViewById(R.id.r8riy5l1je33));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/3wq9eul4_expires_30_days.png").into((ImageView) findViewById(R.id.rd2jw5vnxyc7));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/i3f912ju_expires_30_days.png").into((ImageView) findViewById(R.id.rajckxqvw6zq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/qhlytd10_expires_30_days.png").into((ImageView) findViewById(R.id.r6e8dl0z5by));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/q427r8yo_expires_30_days.png").into((ImageView) findViewById(R.id.rzbzm849cdhi));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/pxiej1tr_expires_30_days.png").into((ImageView) findViewById(R.id.rdfgsno570tr));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/8u5bln3l_expires_30_days.png").into((ImageView) findViewById(R.id.rsft9xnwyhm));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/k7j30naa_expires_30_days.png").into((ImageView) findViewById(R.id.rd9yeyfq8f5c));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/nlh8xbde_expires_30_days.png").into((ImageView) findViewById(R.id.rkbr8ktr7smr));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vhl771q5_expires_30_days.png").into((ImageView) findViewById(R.id.rr2ry03zrd3n));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/gt7pr9eo_expires_30_days.png").into((ImageView) findViewById(R.id.rep2ae39kqy4));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/b3sfo0vx_expires_30_days.png").into((ImageView) findViewById(R.id.rxnqt3i53k4));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/lx96o84s_expires_30_days.png").into((ImageView) findViewById(R.id.ry8fmz48saa9));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/fwxpkclx_expires_30_days.png").into((ImageView) findViewById(R.id.rs3fxm8bvbq9));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/dvlfhnyp_expires_30_days.png").into((ImageView) findViewById(R.id.rh1dcgc7p4io));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/th4a51mx_expires_30_days.png").into((ImageView) findViewById(R.id.ru0mesv4usij));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/iu5ssknm_expires_30_days.png").into((ImageView) findViewById(R.id.ry6p2v1l4nyo));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vd9dfuxc_expires_30_days.png").into((ImageView) findViewById(R.id.r7msnfy6x3b7));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/x663rnc2_expires_30_days.png").into((ImageView) findViewById(R.id.rpc8iqk5o59));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/f15d55t0_expires_30_days.png").into((ImageView) findViewById(R.id.rmxh73hilwon));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/iy4uh9zr_expires_30_days.png").into((ImageView) findViewById(R.id.r1m1lv8f8j9n));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/8r9yxpiz_expires_30_days.png").into((ImageView) findViewById(R.id.rmdy2wf25ts));

        EditText editText1 = findViewById(R.id.r5w9dzyi51kl);
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

        View button1 = findViewById(R.id.r1vjvsiug9ey);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button2 = findViewById(R.id.rhb9yg356q2b);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button3 = findViewById(R.id.rw7fcyv51wt);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button4 = findViewById(R.id.racubuxol5xh);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button5 = findViewById(R.id.rc6ybxdfhyv);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button6 = findViewById(R.id.rt9scgfqsdc);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });

        View button7 = findViewById(R.id.r3i4w07gqk3);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}