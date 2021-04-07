package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PhraseMaker extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String sub_int = "";
    String verb_int = "";
    String obj_int = "";
    String subjnum_int = "singular";
    String objnum_int = "singular";
    String subjdet_int = "-";
    String objdet_int = "-";
    String tense_int = "present";
    String sentencetype_int = "";
    String negated_int="";

    //subject determiner (a, the , nothing)
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.nothingDeterminerSubject:
                if (checked){
                    subjdet_int = "-";
                }
                    break;

            case R.id.theDeterminerSubject:
                if (checked){
                    subjdet_int = "the";
                }
                    break;

            case R.id.aDeterminerSubject:
                if (checked){
                    subjdet_int = "a";
                }
                    break;
        }
    }

    //object determiner (a, the, nothing)
    public void onRadioButtonClickedOb(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.nothingDeterminerObject:
                if (checked){
                    objdet_int="-";
                }
                    break;

            case R.id.theDeterminerObject:
                if (checked){
                    objdet_int = "the";
                }
                    break;

            case R.id.aDeterminerObbject:
                if (checked){
                    objdet_int = "a";
                }
                    break;
        }
    }

    //Tense determiner (past, present, future)
    public void onRadioButtonClickedTense(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.futureTenseRadio:
                if (checked){
                    tense_int = "future";
                }
                    break;

            case R.id.presentTenseRadio:
                if (checked){
                    tense_int = "present";
                }
                    break;

            case R.id.pastTenseRadio:
                if (checked){
                    tense_int = "past";
                }
                    break;
        }
    }

    //Subject number (singular/plural)
    public void onRadioButtonClickedSubjectNum(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.SingleSubjectRadio:
                if (checked){
                    subjnum_int = "singular";
                }
                    break;

            case R.id.PluralSubjectRadio:
                if (checked){
                    subjnum_int = "plural";
                }
                    break;

        }
    }

    //Object number (singular/plural)
    public void onRadioButtonClickedObjectNum(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.SingleObjectRadio:
                if (checked){
                    objnum_int="singular";
                }
                    break;

            case R.id.PluralObjectRadio:
                if (checked){
                    objnum_int="plural";
                }
                    break;

        }
    }
    //negation determiner (yes/no)
    public void onRadioButtonClickedNegated(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {

            case R.id.YesNegateRadio:
                if (checked){
                    negated_int = "negated";
                }
                break;

            case R.id.NoNegateRadio:
                if (checked){
                    negated_int = "";
                }
                break;

        }
    }

    Spinner sentenceType;
    //String[] sentence_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_maker);


        sentenceType = (Spinner)findViewById(R.id.sentenceSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sentenceOptionsArray, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        sentenceType.setAdapter(adapter);

        //sentence_type = getResources().getStringArray(R.array.sentenceOptionsArray);
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sentence_type);
        //sentenceType.OnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        Spinner spinner = (Spinner) findViewById(R.id.sentenceSpinner);
        spinner.setOnItemSelectedListener(this);


        //taking input from user

        EditText subjectInput = findViewById(R.id.subjectInput);
        EditText verbInput = findViewById(R.id.VerbInput);
        EditText objectInput = findViewById(R.id.ObjectInput);
        Button generatebtn = findViewById(R.id.inputbtn);

        generatebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //taking input from user
                sub_int = subjectInput.getText().toString();
                verb_int = verbInput.getText().toString();
                obj_int = objectInput.getText().toString();


                //Api stuff
                AsyncHttpClient client = new AsyncHttpClient();
                client.addHeader("x-rapidapi-key", "cdd85fe9c4msh0221571fabec381p19a98bjsn16a1d4a2d848");
                client.addHeader("x-rapidapi-host", "linguatools-sentence-generating.p.rapidapi.com");

                final String OBJECT = "object=";
                final String SUBJECT = "&subject=";
                final String VERB = "&verb=";
                final String SUBJDET = "&subjdet=";
                final String OBJDET = "&objdet=";
                final String OBJNUM = "&objnum=";
                final String SUBJNUM = "&subjnum=";
                final String TENSE = "&tense=";
                final String SENTENCETYPE = "&sentencetype=";
                final String NEGATED = "&negated=";
                final String URL_PREFIX = "https://linguatools-sentence-generating.p.rapidapi.com/realise?";

                String url = URL_PREFIX + OBJECT + obj_int + SUBJECT + sub_int + VERB + verb_int + SUBJDET + subjdet_int + OBJDET + objdet_int + SUBJNUM + subjnum_int + OBJNUM + objnum_int + TENSE + tense_int + SENTENCETYPE + sentencetype_int + NEGATED + negated_int;

                client.get(url, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Context message = getApplicationContext();
                        String response = new String(responseBody); //disregard for a while

                        try {
                            JSONObject sentencejson = new JSONObject(response);
                            String sentence = sentencejson.getString("sentence");
                            //CharSequence text = new String("success" + sentence);
                            CharSequence text = new String("Sentence generated successfully!");

                            Intent generatesentence = new Intent (PhraseMaker.this, GeneratedSentence.class);
                            generatesentence.putExtra("sentence", sentence);
                            // generatesentence.putExtra("verb", verb_int);
                            // generatesentence.putExtra("object", obj_int);
                            startActivity(generatesentence);

                            int duration= Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(message, url + text, duration);
                            toast.show();

                        } catch (JSONException e) {
                            Log.d("Error", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Context message = getApplicationContext();
                        //String response = new String(responseBody);
                        CharSequence text = new String("Invalid Input");

                        int duration= Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(message, text, duration);
                        toast.show();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });

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



        //next steps:
        //once button clicked, generate url for the words.
        //get client stuff



        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set phraser selected for this page
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(PhraseMaker.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        String itemOnSentence = parent.getItemAtPosition(position).toString();
        if( itemOnSentence.equals("Declarative")){
            sentencetype_int = "";
        }
        else if(itemOnSentence.equals("Polar(yes-no) Question")){
            sentencetype_int = "yesno";
        }
        else if(itemOnSentence.equals("What? (object)")){
            sentencetype_int = "whatobj";
        }
        else if(itemOnSentence.equals("Who? (subject)")){
            sentencetype_int = "whosubj";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}