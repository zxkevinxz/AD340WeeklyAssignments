package com.example.ad340weeklyassignments;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity
public class Settings {

    @NonNull
    @PrimaryKey
    private String email = "";
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    @ColumnInfo(name = "reminder")
    private int reminder = 0;
    public int getReminder() { return reminder; }
    public void setReminder(int reminder) { this.reminder = reminder; }

    @NonNull
    @ColumnInfo(name = "distance")
    private int distance = 0;
    @NonNull
    public int getDistance() { return distance; };
    public void setDistance(@NonNull Integer distance) { this.distance = distance; }

    @Nullable
    @ColumnInfo(name = "gender")
    private String gender = "";
    public String getGender() { return gender; }
    public void setGender(@Nullable String gender) { this.gender = gender; }

    @ColumnInfo(name = "privacy")
    private String privacy = "";
    public String getPrivacy() { return privacy; }
    public void setPrivacy(String privacy) { this.privacy = privacy; }

    @ColumnInfo(name = "ageRange")
    private int ageRange = 0;
    public int getAgeRange() { return ageRange; }
    public void setAgeRange(int ageRange) { this.ageRange = ageRange; }

}
