package com.app.ardasatata.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class radioList extends AppCompatActivity {

    String[] RadioList = {"BBC 1","BBC 2","BBC 3","BBC World"};

    String[] RadioLink = {"http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p"
            ,"http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p"
            ,"http://open.live.bbc.co.uk/mediaselector/5/select/version/2.0/mediaset/http-icy-aac-lc-a/format/pls/vpid/bbc_radio_three.pls"
            ,"http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_list);

        RadioListAdapter radio = new RadioListAdapter(this,RadioList,RadioLink);

        listView = findViewById(R.id.radio_list);
        listView.setAdapter(radio);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(radioList.this, RadioStream.class);
                String stream = RadioLink[i];
                String station = RadioList[i];
                intent.putExtra("stream", stream);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });
    }
}
