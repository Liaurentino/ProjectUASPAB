package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PesananFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);

        // Tombol KONFIRMASI TERIMA — tambahkan android:id="@+id/btnKonfirmasi" di XML-nya
        LinearLayout btnKonfirmasi = view.findViewById(R.id.btnKonfirmasi);
        btnKonfirmasi.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StatusPesanan2Confirm.class);
            startActivity(intent);
        });

        return view;
    }
}