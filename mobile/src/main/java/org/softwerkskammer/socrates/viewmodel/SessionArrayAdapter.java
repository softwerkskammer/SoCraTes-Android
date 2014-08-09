package org.softwerkskammer.socrates.viewmodel;


import android.content.Context;
import android.text.method.DateTimeKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.softwerkskammer.socrates.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SessionArrayAdapter extends BaseAdapter {
    private final Context context;
    private final List values;
    private final LayoutInflater layoutInflater;

    public SessionArrayAdapter(Context context, ArrayList values, LayoutInflater inflater) {
        layoutInflater = inflater;
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (values.get(position) instanceof Session) {
            View rowView = inflater.inflate(R.layout.session_row, parent, false);
            Session currentSession = (Session) values.get(position);
            TextView titleView = (TextView) rowView.findViewById(R.id.titleView);
            titleView.setText(currentSession.title);

            TextView sessionOwnerView = (TextView) rowView.findViewById(R.id.ownerView);
            sessionOwnerView.setText(currentSession.owner);

            TextView durationView = (TextView) rowView.findViewById(R.id.durationView);
            durationView.setText(currentSession.duration);
            return rowView;
        } else {
            Date startingDate = (Date) values.get(position);
            View rowView = inflater.inflate(R.layout.session_header_row, parent, false);
            TextView startTimeView = (TextView) rowView.findViewById(R.id.startTimeView);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

            startTimeView.setText("Start time: " + simpleDateFormat.format(startingDate));
            return rowView;
        }
    }

    private List addHeaderSections(List<Session> values){
        List returnValue = new ArrayList();
        Date lastTimestamp = null;
        if (null == values){
            return Collections.emptyList();
        }
        for (Session session : values){
            if (!session.startTime.equals(lastTimestamp)){
                returnValue.add(session.startTime);
                lastTimestamp = session.startTime;
            }
            returnValue.add(session);
        }
        return returnValue;
    }

    public void setValues(List<Session> sessions) {
        values.clear();
        values.addAll(addHeaderSections(sessions));
    }
}