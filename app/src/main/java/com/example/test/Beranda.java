package com.example.test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        String username = getIntent().getStringExtra("USER_NAME");
        if (username != null && !username.isEmpty()) {
            TextView tvUser = findViewById(R.id.rinl6pw47y3m);
            tvUser.setText(username);
        }

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/z0m77uow_expires_30_days.png").into((ImageView) findViewById(R.id.rozhjjxom35a));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/8z66uoxw_expires_30_days.png").into((ImageView) findViewById(R.id.r1gs888lu61));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/6df8i9gl_expires_30_days.png").into((ImageView) findViewById(R.id.ryt48bc8uthn));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/4idsr6er_expires_30_days.png").into((ImageView) findViewById(R.id.rqbcr5wbcayk));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/fphpolgv_expires_30_days.png").into((ImageView) findViewById(R.id.r18zyhj3yeeb));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/vdpwnnuk_expires_30_days.png").into((ImageView) findViewById(R.id.rur2p4ibaugc));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/oao8glgf_expires_30_days.png").into((ImageView) findViewById(R.id.re1gk1otxzec));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/wyn9cwwj_expires_30_days.png").into((ImageView) findViewById(R.id.rvpg4r7nxnv));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/9mwxopa7_expires_30_days.png").into((ImageView) findViewById(R.id.rab7xkxt74u));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/j0nzqy7r_expires_30_days.png").into((ImageView) findViewById(R.id.rdzr6dg8if0t));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/ptjn50oe_expires_30_days.png").into((ImageView) findViewById(R.id.r18z2jc0hfdq));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/gd59re2s_expires_30_days.png").into((ImageView) findViewById(R.id.r3rh15zkb0u));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/zsfocxit_expires_30_days.png").into((ImageView) findViewById(R.id.r5jrlpgqx33h));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/n8ykt3xe_expires_30_days.png").into((ImageView) findViewById(R.id.rmvou7rwndfa));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/icw3lm4p_expires_30_days.png").into((ImageView) findViewById(R.id.ryse3oc95qmr));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/mkjnaeo3_expires_30_days.png").into((ImageView) findViewById(R.id.r0pqsgpzvztx));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/hlyayjli_expires_30_days.png").into((ImageView) findViewById(R.id.r6ctgolsuysj));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/h0h0pndv_expires_30_days.png").into((ImageView) findViewById(R.id.rvmknfx6666r));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/wunv60r4_expires_30_days.png").into((ImageView) findViewById(R.id.rtalertrjh3));
    }
}