package com.sohan.venturenxt.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sohan.venturenxt.R;
import com.sohan.venturenxt.model.MeetingData;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MeetingData> meetingData;

    public CustomListAdapter(Activity activity, List<MeetingData> meetingData) {
        this.activity = activity;
        this.meetingData = meetingData;
    }

    @Override
    public int getCount() {
        return meetingData.size();
    }

    @Override
    public Object getItem(int location) {
        return meetingData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        TextView meeting_name = (TextView) convertView.findViewById(R.id.meeting_name);

        MeetingData m = meetingData.get(position);


        // branch name
        meeting_name.setText(m.getTitle());

        return convertView;
    }

}