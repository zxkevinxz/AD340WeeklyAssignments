package com.example.ad340weeklyassignments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ad340weeklyassignments.databinding.ActivityUserHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class UserHome extends AppCompatActivity {

    ActivityUserHomeBinding binding;
    MatchesViewModel matchesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent = getIntent();
        Bundle userInfo = intent.getExtras();

        Adapter adapter = new Adapter(getSupportFragmentManager(), getLifecycle());

        ProfileFragment profileFragment = new ProfileFragment();
        MatchesFragment matchesFragment = new MatchesFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        profileFragment.setArguments(userInfo);
        settingsFragment.setArguments(userInfo);

        Bundle matches = new Bundle();
        matchesViewModel = new MatchesViewModel();
        matchesViewModel.getMatchItems(
                (ArrayList<MatchItem> matchItems) -> matches.putParcelableArrayList(Constants.KEY_MATCHES, matchItems)
        );
        matchesFragment.setArguments(matches);

        adapter.addFragment(profileFragment, "Profile");
        adapter.addFragment(matchesFragment, "Matches");
        adapter.addFragment(settingsFragment, "Settings");

        binding.viewpager.setAdapter(adapter);

        TabLayoutMediator.TabConfigurationStrategy tabConfig = (tab, position) -> {
            for (int i = 0; i <= position; i++) {
                tab.setText(adapter.getPageTitle(position));
            }
        };

        new TabLayoutMediator(binding.tablayout, binding.viewpager, tabConfig).attach();

    }

    public static class Adapter extends FragmentStateAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }
    }


}
