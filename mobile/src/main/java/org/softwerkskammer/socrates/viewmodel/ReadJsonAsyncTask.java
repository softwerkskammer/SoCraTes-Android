package org.softwerkskammer.socrates.viewmodel;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadJsonAsyncTask extends AsyncTask<Void, Void, Void> {

    private SessionArrayAdapter adapter;
    private List<Session> sessions;

    public ReadJsonAsyncTask(SessionArrayAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            readJson();
        } catch (Exception e) {
            Log.e("", "Could not read JSON", e);
        }
        return null;
    }

    public static InputStream getInputStreamFromUrl(String url) {
        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            content = response.getEntity().getContent();
        } catch (Exception e) {
            Log.e("[GET REQUEST]", "Network exception", e);
        }
        return content;
    }

    private void readJson() throws Exception {
        String url = "http://jsonblob.com/api/jsonBlob/53e547ffe4b0bab7bd401bd6";

        JsonReader reader = new JsonReader(new InputStreamReader(getInputStreamFromUrl(url), "UTF-8"));
        reader.beginObject();
        reader.nextName();
        reader.beginArray();

        List<Session> sessions = new ArrayList<Session>();

        while (reader.hasNext()) {
            reader.beginObject();
            Session session = new Session();

            reader.nextName();
            SimpleDateFormat someFormat = new SimpleDateFormat("HH:mm", Locale.GERMANY);
            session.startTime = someFormat.parse(reader.nextString());

            reader.nextName();
            session.duration = reader.nextString();

            reader.nextName();
            session.title = reader.nextString();

            reader.nextName();
            session.owner = reader.nextString();

            reader.nextName();
            session.room = reader.nextString();

            reader.endObject();

            sessions.add(session);
        }
        reader.endArray();
        reader.endObject();

        Log.d("sessions", sessions.toString());
        this.sessions = sessions;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter.setValues(sessions);
        adapter.notifyDataSetChanged();
    }
}