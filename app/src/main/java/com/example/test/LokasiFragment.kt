package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class LokasiFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_lokasi_restoran, container, false)

        val llKembali = view.findViewById<LinearLayout>(R.id.r3ii0ynr89ye)
        llKembali.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager().popBackStack()
        })

        return view
    }
}