package com.example.ad340weeklyassignments;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity(tableName = "settings_table")
public class Settings {

    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @ColumnInfo(name = "reminder")
    private int reminder;
    public int getReminder() { return reminder; }
    public void setReminder(int reminder) { this.reminder = reminder; }

    @ColumnInfo(name = "distance")
    private Integer distance;
    public Integer getDistance() { return (int) distance; };
    public void setDistance(int distance) { this.distance = distance; }

    @Nullable
    @ColumnInfo(name = "gender")
    private String gender;
    public String getGender() {
        if (gender != null)
            return gender;
        else
            return "";
    }
    public void setGender(@Nullable String gender) { this.gender = gender; }

    @ColumnInfo(name = "privacy")
    private String privacy;
    public String getPrivacy() { return privacy; }
    public void setPrivacy(String privacy) { this.privacy = privacy; }

    @ColumnInfo(name = "ageRange")
    private int ageRange;
    public int getAgeRange() { return ageRange; }
    public void setAgeRange(int ageRange) { this.ageRange = ageRange; }

    public Settings(String email) {
        this.email = email;
        gender = null;
        distance = 0;
        privacy = "private";
        ageRange = 0;
        reminder = 0;
    }
    
}
