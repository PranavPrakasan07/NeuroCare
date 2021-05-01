package com.example.neurocare;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chipNavigationBar = findViewById(R.id.bottom_nav_bar);

        chipNavigationBar.setItemSelected(R.id.chatFragment, true);


//        try {
//            Bundle extras = getIntent().getExtras();
//            String toOpen = extras.getString("toOpen");
//
//            if(toOpen.equals("Test")){
//                chipNavigationBar.setItemSelected(R.id.nav_test, true);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TestFragment()).commit();
//            }
//
//            if(toOpen.equals("Maps")){
//                chipNavigationBar.setItemSelected(R.id.nav_find_doctor, true);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MapsFragment()).commit();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Log.d("TAG : i", String.valueOf(chipNavigationBar.getSelectedItemId()));

        chipNavigationBar.setOnItemSelectedListener(i -> {

            Fragment selectedFragment = null;

            if (chipNavigationBar.getSelectedItemId() == R.id.homeFragment) {
                selectedFragment = new HomeFragment();
            }

            if (chipNavigationBar.getSelectedItemId() == R.id.chatFragment) {
                selectedFragment = new ChatFragment();
            }

            if (chipNavigationBar.getSelectedItemId() == R.id.profileFragment) {
                selectedFragment = new ProfileFragment();
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
        });

    }
}