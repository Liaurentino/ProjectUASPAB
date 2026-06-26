package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        // Tampilkan username di greeting
        val tvHello = view.findViewById<TextView>(R.id.r3tii9i8wowm)
        val username =
            if (getArguments() != null) getArguments()!!.getString("USER_NAME") else "User"
        tvHello.setText("Hello, " + username)

        // Tambahkan import TextView di atas
        val btnTambahSateAyam = view.findViewById<LinearLayout>(R.id.btnTambahSateAyam)
        btnTambahSateAyam.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    getActivity(),
                    Checkout::class.java
                )
            )
        })

        val btnTambahNasiGoreng = view.findViewById<LinearLayout>(R.id.btnTambahNasiGoreng)
        btnTambahNasiGoreng.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(getActivity(), Checkout::class.java)
            )
        })

        val btnTambahSopBuntut = view.findViewById<LinearLayout>(R.id.btnTambahSopBuntut)
        btnTambahSopBuntut.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(getActivity(), Checkout::class.java)
            )
        })

        val btnTambahNasiGorengDewa = view.findViewById<LinearLayout>(R.id.btnTambahNasiGorengDewa)
        btnTambahNasiGorengDewa.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(getActivity(), Checkout::class.java)
            )
        })

        return view
    }
}