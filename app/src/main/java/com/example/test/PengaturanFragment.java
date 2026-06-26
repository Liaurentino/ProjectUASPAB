package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PengaturanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pengaturan, container, false);

        LinearLayout llKembali = view.findViewById(R.id.rsogk98b11p);
        llKembali.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }
}