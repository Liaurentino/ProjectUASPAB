package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TentangKamiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tentang_kami, container, false);

        LinearLayout llKembali = view.findViewById(R.id.rsqkmm9f0fnb);
        llKembali.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }
}