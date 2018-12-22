package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthquakeData {
    private double magnitude;
    private String location;
    private long timeInMilliseconds;
    String url;


    public EarthquakeData(String location, double magnitude, long date, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = date;
        this.url = url;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getLocation() {
        return location;
    }
    public String getUrl(){
        return url;
    }

    public long getDate() {



        return timeInMilliseconds;
    }
}
