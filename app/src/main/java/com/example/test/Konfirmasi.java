package com.example.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class Konfirmasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);

        Glide.with(this).load("https://storage.googleapis.com/tagjs-prod.appspot.com/v1/nKZicTJByZ/nydrv4o9_expires_30_days.png").into((ImageView) findViewById(R.id.ruujtneij4rr));

        // Tombol KEMBALI → ke MainActivity yang menampilkan MenuFragment
        LinearLayout btnKonfirmasi = findViewById(R.id.btnKonfirmasi);
        btnKonfirmasi.setOnClickListener(v -> {
            Intent intent = new Intent(Konfirmasi.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        // Pop up langsung muncul saat halaman dibuka
        Dialog dialog = new Dialog(Konfirmasi.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_popup);

        ImageView imgPopup = dialog.findViewById(R.id.imgPopup);
        imgPopup.setImageResource(R.drawable.popupkonfirmasi);

        View btnOk = dialog.findViewById(R.id.btnOkDialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}