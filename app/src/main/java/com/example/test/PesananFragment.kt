package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class PesananFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesanan, container, false)

        // Tombol KONFIRMASI TERIMA — tambahkan android:id="@+id/btnKonfirmasi" di XML-nya
        val btnKonfirmasi = view.findViewById<LinearLayout>(R.id.btnKonfirmasi)
        btnKonfirmasi.setOnClickListener(View.OnClickListener { v: View? ->
            val intent = Intent(getActivity(), StatusPesanan2Confirm::class.java)
            startActivity(intent)
        })

        return view
    }
}