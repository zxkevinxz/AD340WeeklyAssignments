package com.example.ad340weeklyassignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SettingsFragment extends Fragment {


    Spinner gender;
    EditText ageRange;
    EditText distance;
    Spinner reminder;
    Spinner privacy;
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

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        ArrayAdapter<CharSequence> privacyAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.privacy_array, android.R.layout.simple_spinner_item);
        privacyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        privacy.setAdapter(privacyAdapter);

        ArrayAdapter<CharSequence> reminderAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.reminder_array, android.R.layout.simple_spinner_item);
        reminderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reminder.setAdapter(reminderAdapter);


        final Observer<List<Settings>> getSettingsObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }

            Settings settings = newSettings.get(0);

            if (settings == null) {
                return;
            }

            gender.setSelection(genderAdapter.getPosition(settings.getGender()));
            privacy.setSelection(privacyAdapter.getPosition(settings.getPrivacy()));
            reminder.setSelection(reminderAdapter.getPosition(settings.getReminder()));
            ageRange.setHint(Integer.toString(settings.getAgeRange()));
            distance.setHint(Integer.toString(settings.getDistance()));

        };

        save.setOnClickListener(v -> {
            Settings settings = new Settings();
            settings.setEmail(email);

            int age;
            if (ageRange.getText().toString().equals(""))
                age = Integer.parseInt(ageRange.getHint().toString());
            else
                age = Integer.parseInt(ageRange.getText().toString());

            int newDist;
            if (distance.getText().toString().equals(""))
                newDist = Integer.parseInt(distance.getHint().toString());
            else
                newDist = Integer.parseInt(distance.getText().toString());

            ArrayList<String> errors = new ArrayList<>();

            if (gender.getSelectedItem().equals(""))
                errors.add(getString(R.string.gender_update_error));
            else
                settings.setGender((String) gender.getSelectedItem());
            if (age < 0 || age > 99)
                errors.add(getString(R.string.age_update_error));
            else
                settings.setAgeRange(age);
            if (newDist < 0 || newDist > 9999)
                errors.add(getString(R.string.distance_update_error));
            else
                settings.setDistance(newDist);

            settings.setPrivacy((String) privacy.getSelectedItem());
            settings.setReminder((String) reminder.getSelectedItem());

            if (errors.size() > 0) {
                String errorToast = String.join("", errors);
                Toast.makeText(v.getContext(), errorToast, Toast.LENGTH_LONG).show();
            } else {
                settingsViewModel.insertSettings(view.getContext(), settings);
                Toast.makeText(v.getContext(), R.string.settings_saved_toast, Toast.LENGTH_SHORT).show();
            }
        });

        String[] emails = { email };
        settingsViewModel.loadSettingsByEmail(view.getContext(), emails).observe((LifecycleOwner) view.getContext(), getSettingsObserver);

        return view;
    }

}
