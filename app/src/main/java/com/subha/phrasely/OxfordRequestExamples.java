package com.subha.phrasely;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class OxfordRequestExamples extends AsyncTask<String, Void, String> {

    public interface AsyncResponseExample {
        void processFinishexample(String output);
    }
    public OxfordRequestExamples.AsyncResponseExample delegate = null;

    public OxfordRequestExamples(OxfordRequestExamples.AsyncResponseExample delegate){
        this.delegate = delegate;
    }



    @Override
    protected String doInBackground(String... params) {

        
        final String app_id = "78bcc445";
        final String app_key = "063b50a0e28c543abb15355ca15a8e84";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void onPostExecute(String result) {
        super.onPostExecute(result);

        String example;
        try {
            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");

            JSONObject iEntries = results.getJSONObject(0);
            JSONArray laArray = iEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject de = sensesArray.getJSONObject(0);
            JSONArray d = de.getJSONArray("examples");

            JSONObject exm = d.getJSONObject(0);
            example = exm.getString("text");
            //example = d.getString(0);
            result = example;
            Log.v("example", result);
            delegate.processFinishexample(result);

        } catch (JSONException e) {
            e.printStackTrace();
            delegate.processFinishexample(null);
        }
    }
}