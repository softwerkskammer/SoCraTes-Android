package org.softwerkskammer.socrates;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import org.softwerkskammer.socrates.viewmodel.Session;
import org.softwerkskammer.socrates.viewmodel.SessionArrayAdapter;

import java.util.Calendar;


public class MyConferenceScheduleActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Session session = new Session();
        session.title = "Marketplace";
        session.duration = "1.5h";
        session.owner = "Pierluigi";
        session.shortDescription = "Decide what to talk about";
        session.room = "Böhmesaal";
        Calendar instance = Calendar.getInstance();
        instance.set(2014, Calendar.AUGUST, 9, 9, 0);
        session.startTime = instance.getTime();

        Session[] values = new Session[] { session, session, session, session };
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setListAdapter(new SessionArrayAdapter(getApplicationContext(), values, inflater));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
