package com.example.ad340weeklyassignments;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Settings.class}, version = 1)
public abstract class SettingsRoomDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}
