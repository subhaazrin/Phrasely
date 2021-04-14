package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

//add dependencies to your class

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Object;
import java.lang.Number;
import java.lang.Byte;



public class SentenceBreakdown extends AppCompatActivity {

    // TextToSpeech t1;
    //ImageButton b1;
    String urlDefinition;
    String urlExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_breakdown);


        //displays words
        displayWords();

        Button button = (Button) findViewById(R.id.btnGoToWordBreakdown);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWordBreakdown();
            }
        });

     /*   b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = sentence;
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
*/
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
        RelativeLayout wordBackground = (RelativeLayout) findViewById(R.id.SentencebreakdownBackgrnd);
        for (String word : words) {
            TextView sentenceWord = new TextView(this);

            sentenceWord.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            sentenceWord.setText(word);
            sentenceWord.setClickable(true);
            //sentenceWord.setGravity(Gravity.CENTER);
            sentenceWord.setBackgroundColor(getResources().getColor(R.color.radioColorPurple));
            wordBackground.addView(sentenceWord);
            sentenceWord.setOnClickListener(new View.OnClickListener() {

                String defDef = word;
                String exmExm = word;
                String result;

                @Override
                public void onClick(View v) {

                    OxfordRequest dR = new OxfordRequest(defDef);
                    OxfordRequestExamples dRR = new OxfordRequestExamples(exmExm);

                    urlDefinition = dictionaryEntriesDefinition(word);
                    dR.execute(urlDefinition);

                    urlExample = dictionaryEntriesExample(word);
                    dRR.execute(urlExample);


                    //result = dR.getvalue(result);

//i dot think we can d anything from here. we prolly have to research how to return string from async task and prolly have to mostlikely change things in OCfordRequet rather than here. brb

                    openWordBreakdown();
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
        Intent breakdowntogenerated = new Intent(this, GeneratedSentence.class);
        startActivity(breakdowntogenerated);
    }

    public void openWordBreakdown(/*String definition*/) {
        Intent intent = new Intent(this, WordBreakdown.class);
       // intent.putExtra("definition", definition);
        startActivity(intent);
    }


    public void gohelp(View v) {
        //go to help activity
        Intent breakdowntohelp = new Intent(this, HelpSentenceBreakdown.class);
        startActivity(breakdowntohelp);
    }

    }


