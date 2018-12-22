package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static com.example.android.quakereport.R.id.date;

import static com.example.android.quakereport.R.id.default_activity_button;
import static com.example.android.quakereport.R.id.magnitude;
import static com.example.android.quakereport.R.id.primary_location;
import static com.example.android.quakereport.R.id.text;

public class DataAdapter extends ArrayAdapter<EarthquakeData> {
    public DataAdapter(@NonNull Context context, @NonNull ArrayList<EarthquakeData> objects) {
        super(context, 0, objects);

    }

    private static final String LOCATION_SEPARATOR = " of ";

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_item, parent, false);
        }

        final EarthquakeData currentData = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentData.getDate());


        String dateToDisplay = formatDate(dateObject);

        textView.setText(dateToDisplay);


        String originalLocation = currentData.getLocation();
        String offset_location, primary_location;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offset_location = parts[0] + LOCATION_SEPARATOR;
            primary_location = parts[1];
        } else {
            offset_location = getContext().getString(R.string.near_the);
            primary_location = originalLocation;
        }
        TextView textView1 = (TextView) listItemView.findViewById(R.id.offset_location);
        textView1.setText(offset_location);

        TextView textView4 = (TextView) listItemView.findViewById(R.id.primary_location);
        textView4.setText(primary_location);




        TextView textView2 = (TextView) listItemView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) textView2.getBackground();

        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String output = decimalFormat.format(currentData.getMagnitude());
        textView2.setText(output);

        TextView textView3 = (TextView) listItemView.findViewById(R.id.time);

        String timeToDisplay = formatTime(dateObject);

        textView3.setText(timeToDisplay);


        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);


    }

}
