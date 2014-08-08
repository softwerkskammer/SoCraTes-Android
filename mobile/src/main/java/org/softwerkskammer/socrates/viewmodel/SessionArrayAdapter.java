package org.softwerkskammer.socrates.viewmodel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.softwerkskammer.socrates.R;

public class SessionArrayAdapter extends ArrayAdapter<Session> {
    private final Context context;
    private final Session[] values;

    public SessionArrayAdapter(Context context, Session[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        Session currentSession = values[position];

        TextView titleView = (TextView) rowView.findViewById(R.id.titleView);
        titleView.setText(currentSession.title);

        TextView sessionOwnerView = (TextView) rowView.findViewById(R.id.ownerView);
        sessionOwnerView.setText(currentSession.owner);

        TextView durationView = (TextView) rowView.findViewById(R.id.durationView);
        durationView.setText(currentSession.duration);


        return rowView;
    }
}