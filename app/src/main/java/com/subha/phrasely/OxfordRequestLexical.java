package com.subha.phrasely;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class OxfordRequestLexical extends AsyncTask<String,Void, String> {

        public interface AsyncResponseLex {
            void processFinish2(String output);
        }
        public com.subha.phrasely.OxfordRequestLexical.AsyncResponseLex delegate = null;

        public OxfordRequestLexical(com.subha.phrasely.OxfordRequestLexical.AsyncResponseLex delegate){
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

            String lexical;

            try {
                JSONObject js = new JSONObject(result);
                JSONArray results = js.getJSONArray("results");

                JSONObject iEntries = results.getJSONObject(0);
                JSONArray laArray = iEntries.getJSONArray("lexicalEntries");

                JSONObject jsonObject = laArray.getJSONObject(0);
                JSONObject jsonObject1 = jsonObject.getJSONObject("lexicalCategory");

                lexical = jsonObject1.getString("id");

                result = lexical;
                Log.v("lexical", result);
                delegate.processFinish2(result);

            } catch (JSONException e) {
                e.printStackTrace();
                delegate.processFinish2(null);
            }

        }

    }


