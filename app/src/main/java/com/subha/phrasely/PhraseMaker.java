package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class PhraseMaker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_maker);

        //taking input from user

        EditText subjectInput = findViewById(R.id.subjectInput);
        EditText verbInput = findViewById(R.id.VerbInput);
        EditText objectInput = findViewById(R.id.ObjectInput);
        Button generatebtn = findViewById(R.id.inputbtn);

        generatebtn.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                String sub_int = subjectInput.getText().toString();
                String verb_int = verbInput.getText().toString();
                String obj_int = objectInput.getText().toString();

                Intent generatesentence = new Intent (PhraseMaker.this, GeneratedSentence.class);
                generatesentence.putExtra("subject", sub_int);
                generatesentence.putExtra("verb", verb_int);
                generatesentence.putExtra("object", obj_int);
                startActivity(generatesentence);

            }
        });

       /*
       **not needed anymore
        //method for going to Generated Sentence
        public void goGeneratedSentence(View v){
            //launching Generated Sentence activity
            Intent generatesentence = new Intent (this, GeneratedSentence.class);
            startActivity(generatesentence);
        } */

        //Api stuff
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-rapidapi-key", "cdd85fe9c4msh0221571fabec381p19a98bjsn16a1d4a2d848");
        client.addHeader("x-rapidapi-host", "linguatools-sentence-generating.p.rapidapi.com");

         /*   final String OBJECT = "realise?object=";
            final String SUBJECT = "&subject=";
            final String VERB = "&verb=";
            final String URL_PREFIX = "https://linguatools-sentence-generating.p.rapidapi.com/";

            String url = URL_PREFIX + OBJECT + obj_int + SUBJECT + sub_int + VERB + verb_int ;
*/
        client.get("https://linguatools-sentence-generating.p.rapidapi.com/realise?object=thief&subject=police&verb=arrest", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Context message = getApplicationContext();
                CharSequence text = new String(responseBody);

                int duration= Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(message, text, duration);
                toast.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

        //next steps:
        //once button clicked, generate url for the words.
        //get client stuff



        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected for this page
        bottomNavigationView.setSelectedItemId(R.id.sentence_maker_nav);

        //perfom ItemSelectedListener Event
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.lang_bot_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,LanguageBot.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.sentence_maker_nav:
                        return true;

                    case R.id.settings_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }


        });
    }
    //method for going to homepage from backbutton
    public void goback(View v) {
        //launching Home page activity
        Intent phrasertohome = new Intent(this, HomePage.class);
        startActivity(phrasertohome);
    }

}