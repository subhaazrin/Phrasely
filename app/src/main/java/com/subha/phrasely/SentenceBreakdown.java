package com.subha.phrasely;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

//add dependencies to your class


public class SentenceBreakdown extends AppCompatActivity implements OxfordRequest.AsyncResponse, OxfordRequestExamples.AsyncResponseExample, OxfordRequestLexical.AsyncResponseLex   {

    //ImageButton b1;
    ImageView play;
    String output2;
    String sentencePlay;
    TextToSpeech tts;
    ImageView backbtn;
    String output1;

    String urlDefinition;
    String urlExample;
    String urlLexical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_breakdown);
        sentencePlay = getIntent().getStringExtra(("sentence"));


        tts = new TextToSpeech(SentenceBreakdown.this, new TextToSpeech.OnInitListener() {


            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);

                    if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(SentenceBreakdown.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(SentenceBreakdown.this, "Initialization failed", Toast.LENGTH_SHORT).show();
            }

        });
        ImageView play = (ImageView) findViewById(R.id.playICON);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = sentencePlay;
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null, "hola");
            }
        });

            //displays words
        displayWords();

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set sentence maker selected for this page
        bottomNavigationView.setSelectedItemId(R.id.sentence_maker_nav);

        //perfom ItemSelectedListener Event
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext()
                                , HomePage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.lang_bot_nav:
                        startActivity(new Intent(getApplicationContext()
                                , LanguageBot.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.sentence_maker_nav:
                        startActivity(new Intent(getApplicationContext()
                                , PhraseMaker.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.settings_nav:
                        startActivity(new Intent(getApplicationContext()
                                , Settings.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }


        });

    }




//


    public String[] getWords() {
        String sentence = getIntent().getStringExtra(("sentence"));
        String[] words = sentence.split("\\W+");
        return words;
    }

    public int numWords() {
        String[] words = getWords();
        int numWords = words.length;
        return numWords;
    }

    public void displayWords() {
        String[] words = getWords();

      // RelativeLayout wordBackground = (RelativeLayout) findViewById(R.id.SentencebreakdownBackgrnd);

        LinearLayout wordBackground = findViewById(R.id.SentencebreakdownBackgrnd);

        for (String word : words) {
            TextView sentenceWord = new TextView(this);
            sentenceWord.setText(word);
            sentenceWord.setClickable(true);
            sentenceWord.setBackgroundColor(getResources().getColor(R.color.radioColorPurple));
            sentenceWord.setPadding(30, 10, 30, 10 );

            Space space = new Space(this);

            wordBackground.addView(sentenceWord);
            wordBackground.addView(space);

            sentenceWord.setOnClickListener(new View.OnClickListener() {

                String defDef = word;
                String exmExm = word;
                String result;

                @Override
                public void onClick(View v) {

                    OxfordRequest dR = new OxfordRequest(SentenceBreakdown.this);
                    urlDefinition = dictionaryEntriesDefinition(word);
                    dR.execute(urlDefinition);


                    OxfordRequestExamples dRR = new OxfordRequestExamples(SentenceBreakdown.this);
                    urlExample = dictionaryEntriesExample(word);
                    dRR.execute(urlExample);

                    OxfordRequestLexical dRRR = new OxfordRequestLexical(SentenceBreakdown.this);
                    urlLexical= dictionaryEntriesExample(word);
                    dRRR.execute(urlLexical);

                    String WordName = word;
                    SharedPreferences sharedPref = getSharedPreferences("key", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", WordName);
                    editor.apply();


                    //result = dR.getvalue(result);

//i dot think we can d anything from here. we prolly have to research how to return string from async task and prolly have to mostlikely change things in OCfordRequet rather than here. brb

                }
            });



/*
            //test
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(wordBackground);

            List<String> wordsArrayList = new ArrayList<String>();
            wordsArrayList = Arrays.asList(words);

            View previousItem = null;
            for( sentenceWord : wordsArrayList) {
                boolean lastItem = wordsArrayList.indexOf(sentenceWord) == wordsArrayList.size() - 1;
                if(previousItem == null) {
                    constraintSet.connect(sentenceWord.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                } else {
                    constraintSet.connect(sentenceWord.getId(), ConstraintSet.LEFT, previousItem.getId(), ConstraintSet.RIGHT);
                    if(lastItem) {
                        constraintSet.connect(sentenceWord.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                    }
                }
                previousItem = sentenceWord;
            }

            int[] viewIds = ByteUtils.toIntArray(new ArrayList<>(Collections2.transform(wordsArrayList, View::getId)));
            constraintSet.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIds, null, ConstraintSet.CHAIN_SPREAD);
            constraintSet.applyTo(wordBackground); */


        }
    }

    /*  count words, loop for every word, create a textview/button , background
        onclick word , get the string from the text, and index
        make two api calls

     */

    private String dictionaryEntriesDefinition(String wordz) {
        final String language = "en-gb";
        final String word = wordz;
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    private String dictionaryEntriesExample(String wordz) {
        final String language = "en-gb";
        final String word = wordz;
        final String fields = "examples";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
/*
    public void sendRequestOnClick(View v){
        OxfordRequest dR = new OxfordRequest();
        url = dictionaryEntries();
        dR.execute(url);
    }*/


    //method for going to Generated Sentence Activity from backbutton
    public void goback(View v) {
        //launching  activity
       // Intent breakdowntogenerated = new Intent(this, GeneratedSentence.class);
        //startActivity(breakdowntogenerated);
        this.finish();

    }


   /* public void openWordBreakdown() {
        Intent intent = new Intent(this, WordBreakdown.class);
       // intent.putExtra("definition", definition);
        startActivity(intent);
    }*/


    public void gohelp(View v) {
        //go to help activity
        Intent breakdowntohelp = new Intent(this, HelpSentenceBreakdown.class);
        startActivity(breakdowntohelp);
    }

   @Override
    public void processFinish(String output) {
        if(output == null){
            Toast.makeText(getApplicationContext(), "Information not available for this word, try another word.", Toast.LENGTH_LONG).show();
       } else {
            output1 = output;
        }
    }

    @Override
    public void processFinish2(String output) {
        if(output == null){
            Toast.makeText(getApplicationContext(), "Information not available for this word, try another word.", Toast.LENGTH_LONG).show();
        } else {
            output2 = output;

            Log.v("lexical", output2);
        }
    }

    @Override
    public void processFinishexample(String output) {
        if(output == null){
            Toast.makeText(getApplicationContext(), "Information not available for this word, try another word.", Toast.LENGTH_LONG).show();
        } else {
            Intent openWordBreakdown = new Intent(this, WordBreakdown.class);
            openWordBreakdown.putExtra("definition", output1);
            openWordBreakdown.putExtra("example", output);
            openWordBreakdown.putExtra("lexical", output2);
            startActivity(openWordBreakdown);
        }
    }


}



