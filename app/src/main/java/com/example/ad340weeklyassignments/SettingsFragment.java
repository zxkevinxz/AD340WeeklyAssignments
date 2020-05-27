package com.example.ad340weeklyassignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ad340weeklyassignments.databinding.SettingsFragmentBinding;

public class SettingsFragment extends Fragment {

    private SettingsFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle userInfo = getArguments();
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        assert userInfo != null;
        String email = userInfo.getString(Constants.KEY_EMAIL);

        Settings settings;
        try {
            settings = settingsViewModel.loadSettingsByEmail(view.getContext(), email);
        } catch (Exception e) {
            settings = new Settings(email);
        }

        EditText gender = view.findViewById(R.id.sGenderInput);
        EditText ageRange = view.findViewById(R.id.sAgeRangeInput);
        EditText distance = view.findViewById(R.id.sDistanceInput);
        EditText reminder = view.findViewById(R.id.sReminderTimeInput);
        EditText privacy = view.findViewById(R.id.sPrivacyInput);
        Button save = view.findViewById(R.id.save_settings);

        gender.setHint(settings.getGender());
        ageRange.setHint(settings.getAgeRange());
        distance.setHint(settings.getDistance());
        reminder.setHint(settings.getReminder());
        privacy.setHint(settings.getPrivacy());

        Settings finalSettings = settings;
        save.setOnClickListener(v -> {
            finalSettings.setAgeRange(Integer.parseInt((String) ageRange.getHint()));
            finalSettings.setGender((String) gender.getHint());
            finalSettings.setDistance(Integer.parseInt((String) distance.getHint()));
            finalSettings.setReminder(Integer.parseInt((String) reminder.getHint()));
            finalSettings.setPrivacy((String) privacy.getHint());
            settingsViewModel.updateSettings(v.getContext(), finalSettings);
        });

        return view;
    }
}
