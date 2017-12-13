package com.app.ardasatata.project;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;

public class RadioStream extends AppCompatActivity {

    ImageButton play;
    ImageButton pause;

    String stream ;

    MediaPlayer player;

    boolean prepared=false;
    boolean started=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_stream);

        String RadioLink = getIntent().getStringExtra("stream");

        stream = RadioLink;

        play = findViewById(R.id.radio_play);
        pause = findViewById(R.id.radio_pause);

        play.setEnabled(false);
        pause.setEnabled(false);

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);


        new Thread();

        new PlayerTask().execute(stream);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                started = true;
                player.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                started = false;
                player.pause();
            }
        });
    }



    class PlayerTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                player.setDataSource(stream);
                player.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            play.setEnabled(true);
            pause.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (prepared){
            player.release();
        }
    }
}
