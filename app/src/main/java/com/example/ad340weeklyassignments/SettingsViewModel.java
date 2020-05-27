package com.example.ad340weeklyassignments;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SettingsViewModel extends AndroidViewModel {

    public SettingsViewModel(@NonNull Application application) {
        super(application);
    }

    public Settings loadSettingsByEmail(Context context, String email) {
        SettingsRoomDatabase db = SettingsDatabaseSingleton.getDatabase(context);
        return db.settingsDao().findSettingsByEmail(email);
    }

    public void insertSettings(Context context, Settings settings) {
        SettingsRoomDatabase db = SettingsDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().insert(settings);
        });
    }

    public void updateSettings(Context context, Settings settings) {
        SettingsRoomDatabase db = SettingsDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().updateSettings(settings);
        });
    }
}
