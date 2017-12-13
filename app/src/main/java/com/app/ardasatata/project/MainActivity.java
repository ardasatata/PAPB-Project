package com.app.ardasatata.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void radioStream(View view) {
        Intent intent = new Intent(this,radioList.class );
        startActivity(intent);
    }

    public void musicPlayer(View view) {
        Intent intent = new Intent(this,MusicPlayer.class );
        startActivity(intent);
    }
}
