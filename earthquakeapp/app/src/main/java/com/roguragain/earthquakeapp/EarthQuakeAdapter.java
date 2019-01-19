package com.roguragain.earthquakeapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthQuakeAdapter(@NonNull Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {

                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake, parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView place = listItemView.findViewById(R.id.earthquake_place);
        place.setText(earthquake.place);

        TextView magnitude = listItemView.findViewById(R.id.earthquake_magnitude);
        magnitude.setText(earthquake.magnitude);

        return listItemView;
    }
}
