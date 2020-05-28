package com.example.ad340weeklyassignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class SettingsFragment extends Fragment {


    EditText gender;
    EditText ageRange;
    EditText distance;
    EditText reminder;
    EditText privacy;
    Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle userInfo = getArguments();
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        assert userInfo != null;
        String email = userInfo.getString(Constants.KEY_EMAIL);

        gender = view.findViewById(R.id.sGenderInput);
        ageRange = view.findViewById(R.id.sAgeRangeInput);
        distance = view.findViewById(R.id.sDistanceInput);
        reminder = view.findViewById(R.id.sReminderTimeInput);
        privacy = view.findViewById(R.id.sPrivacyInput);
        save = view.findViewById(R.id.save_settings);

        final Observer<List<Settings>> getSettingsObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }

            Settings settings = newSettings.get(0);

            if (settings == null) {
                return;
            }

            gender.setHint(settings.getGender());
            ageRange.setHint(Integer.toString(settings.getAgeRange()));
            distance.setHint(Integer.toString(settings.getDistance()));
            reminder.setHint(Integer.toString(settings.getReminder()));
            privacy.setHint(settings.getPrivacy());

        };

        save.setOnClickListener(v -> {
            Settings settings = new Settings();
            settings.setEmail(email);
            settings.setAgeRange(Integer.parseInt(ageRange.getText().toString()));
            settings.setGender(gender.getText().toString());
            settings.setDistance(Integer.parseInt(distance.getText().toString()));
            settings.setReminder(Integer.parseInt(reminder.getText().toString()));
            settings.setPrivacy(privacy.getText().toString());
            settingsViewModel.insertSettings(view.getContext(), settings);
        });

        String[] emails = { email };
        settingsViewModel.loadSettingsByEmail(view.getContext(), emails).observe((LifecycleOwner) view.getContext(), getSettingsObserver);

        return view;
    }
}
