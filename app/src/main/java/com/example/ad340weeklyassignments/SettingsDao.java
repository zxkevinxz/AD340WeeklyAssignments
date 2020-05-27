package com.example.ad340weeklyassignments;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface SettingsDao {

    @Insert
    void insert(Settings settings);

    @Query("SELECT * FROM settings_table where email = :email")
    Settings findSettingsByEmail(String email);

    @Update
    void updateSettings(Settings settings);

}
