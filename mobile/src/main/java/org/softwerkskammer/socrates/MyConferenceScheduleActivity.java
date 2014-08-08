package org.softwerkskammer.socrates;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import org.softwerkskammer.socrates.viewmodel.ReadJsonAsyncTask;
import org.softwerkskammer.socrates.viewmodel.Session;
import org.softwerkskammer.socrates.viewmodel.SessionArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MyConferenceScheduleActivity extends ListActivity {

    private SessionArrayAdapter listAdapter;
    private ArrayList<Session> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        values = new ArrayList<Session>();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listAdapter = new SessionArrayAdapter(getApplicationContext(), values, inflater);
        setListAdapter(listAdapter);
    }

    private Session createASession(int hourOfDay, int minute) {
        Session session = new Session();
        session.title = "Marketplace";
        session.duration = "1.5h";
        session.owner = "Pierluigi";
        session.shortDescription = "Decide what to talk about";
        session.room = "Böhmesaal";
        Calendar instance = Calendar.getInstance();
        instance.set(2014, Calendar.AUGUST, 9, hourOfDay, minute);
        session.startTime = instance.getTime();
        return session;
    }


    public List addHeaderSections(List<Session> values){
        List returnValue = new ArrayList();
        Date lastTimestamp = null;
        for (Session session : values){
            if (!session.startTime.equals(lastTimestamp)){
                returnValue.add(session.startTime);
                lastTimestamp = session.startTime;
            }
            returnValue.add(session);
        }
        return returnValue;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh){

            Session session = createASession(9, 0);
            Session session1 = createASession(9, 30);
            Session session2 = createASession(10, 0);
            Session session3 = createASession(10, 0);
            Session session4 = createASession(12, 0);

            values.clear();
            values.addAll(addHeaderSections(Arrays.asList(session, session1, session2, session3, session4)));

            listAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
