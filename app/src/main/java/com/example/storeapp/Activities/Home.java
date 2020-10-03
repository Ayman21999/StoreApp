package com.example.storeapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.storeapp.Fragments.CartFragment;
import com.example.storeapp.Fragments.CategoryFragment;
import com.example.storeapp.Fragments.HomeFragment;
import com.example.storeapp.Fragments.ProfileFragment;
import com.example.storeapp.Fragments.SettingsFragment;
import com.example.storeapp.Model.LocalHelper;
import com.example.storeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
        BottomNavigationView bottomNavigationViewl;

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LocalHelper.onAttach(newBase,"en"));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    bottomNavigationViewl = findViewById(R.id.nav_btom);
    bottomNavigationViewl.setOnNavigationItemSelectedListener(bottomNavMethoed);
    getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethoed = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null ;
            switch (item.getItemId()){
                case R.id.home:
                  fragment= new  HomeFragment();
                break;
                case R.id.cate :
                    fragment = new CategoryFragment();
                    break;
                case R.id.cart :
            fragment = new CartFragment();
                    break;
                case R.id.settings:
                    fragment = new SettingsFragment();
                    break;
                case R.id.profile :
                    fragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

            return true;
        }
    };
}