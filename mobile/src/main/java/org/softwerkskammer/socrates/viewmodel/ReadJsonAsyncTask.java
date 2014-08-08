package org.softwerkskammer.socrates.viewmodel;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.softwerkskammer.socrates.viewmodel.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadJsonAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
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

    private Session[] readJson() throws IOException {
        String url = "http://gist.githubusercontent.com/mknittig/1ed20cd664331049dbdd/raw/bee9682905c70e2c8745258ce337fae7b980c0f9/socrates-event-stream.json";
        BufferedReader in = new BufferedReader(new InputStreamReader(getInputStreamFromUrl(url)));

        JsonReader reader = new JsonReader(new InputStreamReader(getInputStreamFromUrl(url), "UTF-8"));
        reader.beginObject();
        String name = reader.nextName();
        Log.e("foo", name);

        return null;
    }
}