package com.roguragain.earthquakeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private static final int EARTHQUAKE_LOADER_ID = 1;
    String url = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    ListView listView;
    EarthQuakeAdapter earthQuakeAdapter;
    TextView noData;
    ProgressBar dataProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.earthquake_list);
        noData = findViewById(R.id.no_data);
        dataProgress = findViewById(R.id.data_progress);

        earthQuakeAdapter = new EarthQuakeAdapter(this, new ArrayList<Earthquake>());
        listView.setEmptyView(noData);

//        class asyncEarthquake extends AsyncTask<String,Void,List<Earthquake>>
//        {
//
//            @Override
//            protected List<Earthquake> doInBackground(String... url) {
//
//                String json = UrlConnection.getEarthquakeJson(url[0]);
//                Log.i("JSON DATA",json);
//                List<Earthquake> e = jsonParser.parseEarthquake(json);
//
//                return e;
//            }
//
//            @Override
//            protected void onPostExecute(List<Earthquake> e) {
//                EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(MainActivity.this,e);
//                listView.setAdapter(earthQuakeAdapter);
//            }
//
//
//        }
//        asyncEarthquake asyncEarthquake = new asyncEarthquake();
//        asyncEarthquake.execute(url);
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

       final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                swipeRefreshLayout.
               // earthQuakeAdapter.clear();
                //url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=json&minmag=5&limit=25";
                //loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,MainActivity.this);

                swipeRefreshLayout.setRefreshing(false);
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });

        if(!isConnected){
            noData.setText("No Internet Connection");
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = (Earthquake) listView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, earthquake_details.class);
                intent.putExtra("EARTHQUAKE", earthquake);
                startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i("LOADEREQ", "CREATED");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPreferences.getString(getString(R.string.settings_min_magnitude_key),getString(R.string.settings_min_magnitude_default));
        Uri baseUri = Uri.parse(url);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format","geojson");
        uriBuilder.appendQueryParameter("limit","10");
        uriBuilder.appendQueryParameter("minmag",minMagnitude);
        uriBuilder.appendQueryParameter("orderby","time");

        return new EarthquakeLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> data) {

        Log.i("LOADEREQ", "FINISHED");
        dataProgress.setVisibility(View.GONE);
        noData.setText("NO EARTHQUAKE FOUND");

        earthQuakeAdapter.clear();


        //earthQuakeAdapter = new EarthQuakeAdapter(MainActivity.this,data);
        if (data != null && !data.isEmpty()) {
            earthQuakeAdapter.addAll(data);
        }
        listView.setAdapter(earthQuakeAdapter);

//        Log.i("EARTHQUAKE from main", data.get(0).toString());


    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        Log.i("LOADEREQ", "RESET");
        earthQuakeAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
