package com.example.ad340weeklyassignments;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchItem implements Parcelable {

    private String imageUrl;
    private String name;
    private boolean liked;
    private String uid;
    private String age;
    private String occupation;
    private String description;
    private String lat;
    private String lon;

    public MatchItem() { }

    public String getImageUrl() { return imageUrl; }
    public String getName() {
        return name;
    }
    public boolean isLiked() {
        return liked;
    }
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public String getUid() {
        return uid;
    }
    public String getAge() {
        return age;
    }
    public String getOccupation() {
        return occupation;
    }
    public String getDescription() {
        return description;
    }
    public String getLat() { return lat; }
    public String getLon() { return lon; }
    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) { dest.writeString(name); }
}
