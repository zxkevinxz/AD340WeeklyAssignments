package com.example.ad340weeklyassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.ad340weeklyassignments.databinding.ActivityUserHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class UserHome extends AppCompatActivity {

    ActivityUserHomeBinding binding;

    public static final String TAG = UserHome.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Adapter adapter = new Adapter(this);

        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new MatchesFragment(), "Matches");
        adapter.addFragment(new SettingsFragment(), "Settings");

        binding.viewpager.setAdapter(adapter);

        TabLayoutMediator.TabConfigurationStrategy tabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                for (int i = 0; i <= position; i++) {
                    tab.setText(adapter.getPageTitle(position));
                }
            }
        };

        new TabLayoutMediator(binding.tablayout, binding.viewpager, tabConfig).attach();

    }




}
