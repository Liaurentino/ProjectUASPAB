package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        // Tampilkan username
        if (getArguments() != null) {
            String username = getArguments().getString("USER_NAME");
            if (username != null && !username.isEmpty()) {
                TextView tvNama = view.findViewById(R.id.tv_profil_nama);
                if (tvNama != null) tvNama.setText(username);
            }
        }

        // Tampilkan email dari SharedPreferences
        String email = requireContext()
                .getSharedPreferences("user_prefs", requireContext().MODE_PRIVATE)
                .getString("user_email", "Email");
        TextView tvEmail = view.findViewById(R.id.tv_profil_email);
        if (tvEmail != null) tvEmail.setText(email);

        LinearLayout llPengaturan = view.findViewById(R.id.ll_pengaturan);
        llPengaturan.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new PengaturanFragment())
                        .addToBackStack(null).commit()
        );

        LinearLayout llKeamanan = view.findViewById(R.id.ll_keamanan);
        llKeamanan.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new KeamananFragment())
                        .addToBackStack(null).commit()
        );

        LinearLayout llPembayaran = view.findViewById(R.id.ll_pembayaran);
        llPembayaran.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MetodePembayaranFragment())
                        .addToBackStack(null).commit()
        );

        LinearLayout llLokasi = view.findViewById(R.id.ll_lokasi);
        llLokasi.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new LokasiFragment())
                        .addToBackStack(null).commit()
        );

        LinearLayout llTentang = view.findViewById(R.id.ll_tentang_kami);
        llTentang.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new TentangKamiFragment())
                        .addToBackStack(null).commit()
        );

        return view;
    }
}