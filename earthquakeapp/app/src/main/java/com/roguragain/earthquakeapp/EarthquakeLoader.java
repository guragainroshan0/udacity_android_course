package com.roguragain.earthquakeapp;

import android.content.Context;
import android.util.Log;

import java.util.List;

import android.content.AsyncTaskLoader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(@NonNull Context context, String url) {

        super(context);
        mUrl = url;
        Log.i("LOADEREQ", "intialized0");
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        String json = UrlConnection.getEarthquakeJson(mUrl);
        Log.i("JSON DATA", json);
        List<Earthquake> e = jsonParser.parseEarthquake(json);
        Log.i("LOADEREQ", "LOAD IN BACKGROUND");
        return e;
    }

    @Override
    protected void onStartLoading() {
        Log.i("LOADEREQ", "START LOADING");
        forceLoad();
    }
}
