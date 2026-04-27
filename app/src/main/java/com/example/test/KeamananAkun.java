package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class KeamananAkun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keamanan_akun);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/xc3i3o4a_expires_30_days.png").into((ImageView) findViewById(R.id.rqwnl0ztyei8));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/tuyckgs0_expires_30_days.png").into((ImageView) findViewById(R.id.r7l8m8f5ulli));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/2umqsajs_expires_30_days.png").into((ImageView) findViewById(R.id.r121h1fvziche));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/t3ka3swr_expires_30_days.png").into((ImageView) findViewById(R.id.rpww4neuzb8));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/tsazdp09_expires_30_days.png").into((ImageView) findViewById(R.id.r7gxc8ok5bkl));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/vngna102_expires_30_days.png").into((ImageView) findViewById(R.id.rwgb88lg82im));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/s2rm9cid_expires_30_days.png").into((ImageView) findViewById(R.id.rjh9ltskncv));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/tqmupx9q_expires_30_days.png").into((ImageView) findViewById(R.id.rqy55ct0785));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/jvg4nru7_expires_30_days.png").into((ImageView) findViewById(R.id.roajncehq21b));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/dw7d61au_expires_30_days.png").into((ImageView) findViewById(R.id.r7hrdcgpvyvx));
        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cHq5FEdV7N/5bakkhjo_expires_30_days.png").into((ImageView) findViewById(R.id.r4gk17fu3t7g));

        View button1 = findViewById(R.id.rrs5ht795ur);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed");
            }
        });
    }
}