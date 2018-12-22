package com.roguragain.music_player;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float x = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.family_father);

        Button buttonPlay = findViewById(R.id.playButton);
        Button buttonPause = findViewById(R.id.pauseButton);
        Button buttonIncVolume = findViewById(R.id.incVolume);
        Button buttonDecVolume = findViewById(R.id.decVolume);




        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override

            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,"I AM DONE",Toast.LENGTH_LONG).show();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        buttonIncVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                x = x+0.2f;
                mediaPlayer.setVolume(x,x);
                Log.i("VOLUME INC:",String.valueOf(x));
            }
        });
        buttonDecVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x -0.2f;
                mediaPlayer.setVolume(x,x);
                Log.i("VOLUME dec:",String.valueOf(x));
            }
        });
    }
}

