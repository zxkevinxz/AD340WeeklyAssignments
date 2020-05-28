package com.example.ad340weeklyassignments;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings... settings);

    @Query("SELECT * FROM settings where email = :email")
    LiveData<List<Settings>> findSettingsByEmail(String[] email);

}
