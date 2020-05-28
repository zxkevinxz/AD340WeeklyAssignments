package com.example.ad340weeklyassignments;
import android.content.Context;
import androidx.room.Room;

public class SettingsSingleton {

    private static SettingsRoomDatabase db;

    public static SettingsRoomDatabase getDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context,
                    SettingsRoomDatabase.class, "settings_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}
