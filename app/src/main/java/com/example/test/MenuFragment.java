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
import android.widget.TextView;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Tampilkan username di greeting
        TextView tvHello = view.findViewById(R.id.r3tii9i8wowm);
        String username = getArguments() != null ? getArguments().getString("USER_NAME") : "User";
        tvHello.setText("Hello, " + username);

        // Tambahkan import TextView di atas
        LinearLayout btnTambahSateAyam = view.findViewById(R.id.btnTambahSateAyam);
        btnTambahSateAyam.setOnClickListener(v -> startActivity(new Intent(getActivity(), Checkout.class)));

        LinearLayout btnTambahNasiGoreng = view.findViewById(R.id.btnTambahNasiGoreng);
        btnTambahNasiGoreng.setOnClickListener(v -> startActivity(new Intent(getActivity(), Checkout.class)));

        LinearLayout btnTambahSopBuntut = view.findViewById(R.id.btnTambahSopBuntut);
        btnTambahSopBuntut.setOnClickListener(v -> startActivity(new Intent(getActivity(), Checkout.class)));

        LinearLayout btnTambahNasiGorengDewa = view.findViewById(R.id.btnTambahNasiGorengDewa);
        btnTambahNasiGorengDewa.setOnClickListener(v -> startActivity(new Intent(getActivity(), Checkout.class)));

        return view;
    }
}