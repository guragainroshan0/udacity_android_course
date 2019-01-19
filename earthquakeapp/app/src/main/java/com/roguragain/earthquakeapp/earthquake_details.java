package com.roguragain.earthquakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class earthquake_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_details);

        Intent intent = getIntent();
        Earthquake earthquake = (Earthquake) intent.getSerializableExtra("EARTHQUAKE");

        updateUi(earthquake);


    }

    private void updateUi(Earthquake earthquake) {
        TextView place = findViewById(R.id.place);
        TextView time = findViewById(R.id.time);
        TextView magnitude = findViewById(R.id.magnitude);
        TextView soonami = findViewById(R.id.soonami);

        long timeString = earthquake.getTime();

        place.setText(earthquake.getPlace());
        time.setText(Long.toString(timeString));
        magnitude.setText(earthquake.getMagnitude());
        soonami.setText(earthquake.isSoonami());
        Log.i("DETAILS JSON DATA", earthquake.toString());
    }

}
