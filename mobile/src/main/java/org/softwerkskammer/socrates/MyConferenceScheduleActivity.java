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

        new ReadJsonAsyncTask(listAdapter).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            new ReadJsonAsyncTask(listAdapter).execute();
        }
        return super.onOptionsItemSelected(item);
    }
}
