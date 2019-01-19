package com.roguragain.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jsonParser {

    public static List<Earthquake> parseEarthquake(String json) {
        List<Earthquake> equakelist = new ArrayList<Earthquake>();

        if (json.isEmpty()) {
            Log.v("JSON PARSING", "NULL");
            return null;
        }
        try {
            Log.v("JSON PARSING", "ENTERED TRY BLOCK");
            JSONObject baseJsonResponse = new JSONObject(json);

            JSONArray featureArray = baseJsonResponse.getJSONArray("features");

            if (featureArray.length() > 0) {
                for (int i = 0; i < featureArray.length(); i++) {
                    JSONObject properties = featureArray.getJSONObject(i).getJSONObject("properties");
                    String place = properties.getString("place");
                    Double mag = properties.getDouble("mag");
                    long time = properties.getLong("time");
                    String title = properties.getString("title");

                    String soonami = Integer.toString(properties.getInt("tsunami"));
                    //String soonami = "dont know";

                    Earthquake e = new Earthquake(place, mag.toString(), title, soonami, time);

                    equakelist.add(e);
                    Log.i("JSON PARSING", e.toString());
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return equakelist;
    }


}
