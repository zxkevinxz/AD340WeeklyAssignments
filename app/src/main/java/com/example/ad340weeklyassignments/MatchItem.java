package com.example.ad340weeklyassignments;

public class MatchItem {

    private String imageUrl;
    private String lat;
    private String lon;
    private String name;
    private boolean liked;
    private String uid;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String occupation;
    private String description;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setUid(String uid) {
        this.uid = uid;
    }



    public MatchItem(String imageUrl, String lat, String lon, String name, boolean liked, String uid) {
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.liked = liked;
        this.uid = uid;
    }

    public MatchItem() { }
}
