package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class GeneratedSentence extends AppCompatActivity {

    // private TextView subject, verb, object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_sentence);

        //subject = findViewById(R.id.textViewSubjectInput);
        //object = findViewById(R.id.textViewObjectInput);
        //verb = findViewById(R.id.textViewVerbInput);

        Button sentencebtn = findViewById(R.id.sentenceBtn);

        // String Subject = getIntent().getStringExtra("subject");
        //String Verb = getIntent().getStringExtra("verb");
        //String Object = getIntent().getStringExtra("object");

        String sentence = getIntent().getStringExtra(("sentence"));

        sentencebtn.setText(sentence);
        //subject.setText(Subject);
        // verb.setText(Verb);
        //object.setText(Object);


        sentencebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //launch Sentence Breakdown activity
                Intent showbreakdown = new Intent (GeneratedSentence.this, SentenceBreakdown.class);
                showbreakdown.putExtra("sentence", sentence);
                startActivity(showbreakdown);
            }
        });

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
                                ,HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.lang_bot_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,LanguageBot.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.sentence_maker_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,PhraseMaker.class));
                        overridePendingTransition(0,0);
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
        //launching phraser main activity
        Intent generatedtophraser = new Intent(this, PhraseMaker.class);
        startActivity(generatedtophraser);
    }
}