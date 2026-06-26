package com.example.test

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = getIntent().getStringExtra("USER_NAME")

        // Load fragment pertama (Beranda)
        if (savedInstanceState == null) {
            loadFragment(BerandaFragment())
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment?
            val id = item.getItemId()
            if (id == R.id.nav_beranda) {
                fragment = BerandaFragment()
            } else if (id == R.id.nav_menu) {
                fragment = MenuFragment()
            } else if (id == R.id.nav_pesanan) {
                fragment = PesananFragment()
            } else if (id == R.id.nav_profil) {
                fragment = ProfilFragment()
            } else {
                return@setOnItemSelectedListener false
            }

            // Kirim username ke fragment via Bundle
            val bundle = Bundle()
            bundle.putString("USER_NAME", username)
            fragment.setArguments(bundle)

            loadFragment(fragment)
            true
        }

        // Set username ke BerandaFragment
        val bundle = Bundle()
        bundle.putString("USER_NAME", username)
        val berandaFragment = BerandaFragment()
        berandaFragment.setArguments(bundle)
        loadFragment(berandaFragment)
        bottomNav.setSelectedItemId(R.id.nav_beranda)
    }

    private fun loadFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}