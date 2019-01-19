package com.roguragain.earthquakeapp;

import java.io.Serializable;

public class Earthquake implements Serializable {
    String place;
    String magnitude;
    String title;

    public String getPlace() {
        return place;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getTitle() {
        return title;
    }

    public String isSoonami() {
        return soonami;
    }

    public long getTime() {
        return time;
    }

    String soonami;
    long time;

    public Earthquake(String place, String magnitude, String title, String soonami, long time) {
        this.place = place;
        this.magnitude = magnitude;
        this.title = title;
        this.soonami = soonami;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "place='" + place + '\'' +
                ", magnitude='" + magnitude + '\'' +
                ", title='" + title + '\'' +
                ", soonami='" + soonami + '\'' +
                ", time=" + time +
                '}';
    }
}
