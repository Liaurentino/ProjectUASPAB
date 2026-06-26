package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LokasiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lokasi_restoran, container, false);

        LinearLayout llKembali = view.findViewById(R.id.r3ii0ynr89ye);
        llKembali.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }
}