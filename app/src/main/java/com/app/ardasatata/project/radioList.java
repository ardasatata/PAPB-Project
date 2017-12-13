package com.app.ardasatata.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class radioList extends AppCompatActivity {

    String[] RadioList = {"BBC 1","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_list);

        RadioListAdapter radio = new RadioListAdapter(this,RadioList);

        listView = findViewById(R.id.radio_list);
        listView.setAdapter(radio);
    }
}
