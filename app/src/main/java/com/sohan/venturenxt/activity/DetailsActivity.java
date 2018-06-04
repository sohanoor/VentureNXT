package com.sohan.venturenxt.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.sohan.venturenxt.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {

    TextView mName, mAddress, mDate, mSummary, mAgendaCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String committee = bundle.getString("committee");
        String address = bundle.getString("address");
        String summary = bundle.getString("summary");

        String date = bundle.getString("date");
        Integer agendas_count = bundle.getInt("agendas_count");

        Log.e("committee name", committee);
        Log.e("address name", address);
        Log.e("title", title);
        Log.e("summary", summary);
        Log.e("event_time", date);
        Log.e("agendas_count", "" + agendas_count);


        mName = (TextView) findViewById(R.id.name);
        mAddress = (TextView) findViewById(R.id.address);
        mDate = (TextView) findViewById(R.id.date);
        mSummary = (TextView) findViewById(R.id.summary);
        mAgendaCount = (TextView) findViewById(R.id.agenda_count);

        mName.setText(committee);
        mAddress.setText(address);
        mSummary.setText(summary);
        mDate.setText(date);
        mAgendaCount.setText(""+agendas_count);

    }
}
