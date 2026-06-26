package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("USER_NAME");

        // Load fragment pertama (Beranda)
        if (savedInstanceState == null) {
            loadFragment(new BerandaFragment());
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.nav_beranda) {
                fragment = new BerandaFragment();
            } else if (id == R.id.nav_menu) {
                fragment = new MenuFragment();
            } else if (id == R.id.nav_pesanan) {
                fragment = new PesananFragment();
            } else if (id == R.id.nav_profil) {
                fragment = new ProfilFragment();
            } else {
                return false;
            }

            // Kirim username ke fragment via Bundle
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", username);
            fragment.setArguments(bundle);

            loadFragment(fragment);
            return true;
        });

        // Set username ke BerandaFragment
        Bundle bundle = new Bundle();
        bundle.putString("USER_NAME", username);
        BerandaFragment berandaFragment = new BerandaFragment();
        berandaFragment.setArguments(bundle);
        loadFragment(berandaFragment);
        bottomNav.setSelectedItemId(R.id.nav_beranda);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}