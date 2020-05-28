package com.example.ad340weeklyassignments;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    public LiveData<List<Settings>> loadSettingsByEmail(Context context, String[] email) {
        SettingsRoomDatabase db = SettingsSingleton.getDatabase(context);
        return db.settingsDao().findSettingsByEmail(email);
    }

    public void insertSettings(Context context, Settings... settings) {
        SettingsRoomDatabase db = SettingsSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().insert(settings);
        });
    }

}
