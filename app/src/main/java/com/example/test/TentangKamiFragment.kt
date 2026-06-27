package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class TentangKamiFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_tentang_kami, container, false)

        val llKembali = view.findViewById<LinearLayout>(R.id.rsqkmm9f0fnb)
        llKembali.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager().popBackStack()
        })

        return view
    }
}