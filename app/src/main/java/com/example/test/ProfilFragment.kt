package com.example.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfilFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        // Tampilkan username
        if (getArguments() != null) {
            val username = getArguments()!!.getString("USER_NAME")
            if (username != null && !username.isEmpty()) {
                val tvNama = view.findViewById<TextView?>(R.id.tv_profil_nama)
                if (tvNama != null) tvNama.setText(username)
            }
        }

        // Tampilkan email dari SharedPreferences
        val email: String = requireContext()
            .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            .getString("user_email", "Email")!!
        val tvEmail = view.findViewById<TextView?>(R.id.tv_profil_email)
        if (tvEmail != null) tvEmail.setText(email)

        val llPengaturan = view.findViewById<LinearLayout>(R.id.ll_pengaturan)
        llPengaturan.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, PengaturanFragment())
                .addToBackStack(null).commit()
        }
        )

        val llKeamanan = view.findViewById<LinearLayout>(R.id.ll_keamanan)
        llKeamanan.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, KeamananFragment())
                .addToBackStack(null).commit()
        }
        )

        val llPembayaran = view.findViewById<LinearLayout>(R.id.ll_pembayaran)
        llPembayaran.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, MetodePembayaranFragment())
                .addToBackStack(null).commit()
        }
        )

        val llLokasi = view.findViewById<LinearLayout>(R.id.ll_lokasi)
        llLokasi.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LokasiFragment())
                .addToBackStack(null).commit()
        }
        )

        val llTentang = view.findViewById<LinearLayout>(R.id.ll_tentang_kami)
        llTentang.setOnClickListener(View.OnClickListener { v: View? ->
            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, TentangKamiFragment())
                .addToBackStack(null).commit()
        }
        )

        return view
    }
}