package com.app.ardasatata.project;

import android.app.NotificationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class RadioStream extends AppCompatActivity {

    ImageButton play;
    ImageButton pause;

    String stream ;

    MediaPlayer player;

    TextView status;

    boolean prepared=false;
    boolean started=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_stream);

        String RadioLink = getIntent().getStringExtra("stream");
        final String RadioStation = getIntent().getStringExtra("station");

        stream = RadioLink;

        play = findViewById(R.id.radio_play);
        pause = findViewById(R.id.radio_pause);
        status = findViewById(R.id.radio_status);

        play.setEnabled(false);
        pause.setEnabled(false);

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notificon)
                        .setContentTitle("Now Playing")
                        .setContentText(RadioStation);

        // Sets an ID for the notification
        final int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        final NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        new PlayerTask().execute(stream);

        new Thread(new Runnable() {
            @Override
            public void run() {
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        status.setText("Now Listening to "+RadioStation);
                        started = true;
                        player.start();
                        // Builds the notification and issues it.
                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
                    }
                });
            }
        }).start();


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setText("Paused");
                started = false;
                player.pause();

                mNotifyMgr.cancelAll();
            }
        });
    }



    class PlayerTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                status.setText("Connecting");
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
            status.setText("Radio Connected");
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
