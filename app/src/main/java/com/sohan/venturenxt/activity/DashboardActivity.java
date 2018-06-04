package com.sohan.venturenxt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.sohan.venturenxt.controller.AppController;
import com.sohan.venturenxt.adapter.CustomListAdapter;
import com.sohan.venturenxt.model.MeetingData;
import com.sohan.venturenxt.R;
import com.sohan.venturenxt.api.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    public String Authorization;
    private List<MeetingData> meetingDataList = new ArrayList<MeetingData>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("Authorization");
        Authorization = token;

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        getMeetingList(year, month);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, meetingDataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MeetingData item = (MeetingData) adapter.getItem(position);
                // Toast.makeText(getApplicationContext(), "item Clicked: " + position, Toast.LENGTH_LONG).show();
                sendData(item);
            }
        });
    }

    public void getMeetingList(int year, int month) {

        String url = api.MEETING_INFO_API + "year=" + year + "&month=" + month;
        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        VolleyLog.v("Response:%n %s", response);
                        Log.e("response", "" + response.toString());

                        try {
                            JSONObject meetingInfo = new JSONObject(response);
                            boolean status = meetingInfo.getBoolean("status");
                            JSONObject meta = meetingInfo.getJSONObject("meta");
                            int count = meta.getInt("count");
                            JSONArray data = meetingInfo.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject meetingData = data.getJSONObject(i);
                                String date = data.getString(i);
                                JSONArray meeting = meetingData.getJSONArray("meetings");
                                for (int j = 0; j < meeting.length(); j++) {
                                    JSONObject meet = meeting.getJSONObject(j);
                                    Log.e("Meeting", meet.toString());
                                    JSONObject committee = meet.getJSONObject("committee");

                                    String name = committee.getString("name");
                                    String address = meet.getString("address");
                                    String event_time = meet.getString("event_time");
                                    String title = meet.getString("title");
                                    String summary = meet.getString("summary");
                                    JSONArray agendas = meet.getJSONArray("agendas");
                                    Integer agendas_count = agendas.length();

                                    Log.e("committee name", name);
                                    Log.e("address name", address);
                                    Log.e("title", title);
                                    Log.e("summary", summary);
                                    Log.e("event_time", event_time);
                                    Log.e("agendas_count", "" + agendas_count);


                                    MeetingData meetingDataModel = new MeetingData();
                                    meetingDataModel.setTitle(title);
                                    meetingDataModel.setAddress(address);
                                    meetingDataModel.setCommittee(name);
                                    meetingDataModel.setSummary(summary);
                                    meetingDataModel.setDate(event_time);
                                    meetingDataModel.setAgenda_count(agendas_count);
                                    meetingDataList.add(meetingDataModel);


                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                //params.put("CategoryID","1");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", Authorization);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(sr);
    }

    public void sendData(MeetingData item) {
        String title = item.getTitle();
        String committee = item.getCommittee();
        String address = item.getAddress();
        String summary = item.getSummary();
        String date = item.getDate();
        Integer agendas_count = item.getAgenda_count();


        Intent detailsIntent = (Intent) new Intent(DashboardActivity.this, DetailsActivity.class);
        detailsIntent.putExtra("title", title);
        detailsIntent.putExtra("committee", committee);
        detailsIntent.putExtra("address", address);
        detailsIntent.putExtra("summary", summary);
        detailsIntent.putExtra("date", date);
        detailsIntent.putExtra("agendas_count", agendas_count);
        startActivity(detailsIntent);


    }
}
