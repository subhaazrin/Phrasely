package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WordBreakdown extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_breakdown);

        String DEFINITION = getDefinition();
        TextView definition =  findViewById(R.id.textView4);
        definition.setText(DEFINITION);

        TextView word = findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        String WordName = sharedPreferences.getString("value", "");
        word.setText(WordName);

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

    //method for going to sentence breakdown Activity from backbutton
    public void goback(View v) {
        //launching  activity
        //Intent wordbreakdowntosentencebreakdown = new Intent(this, GeneratedSentence.class);
        //startActivity(wordbreakdowntosentencebreakdown);
        this.finish();
    }

    public void gohelp(View v) {
        //go to help activity
        Intent wordbreakdowntohelp = new Intent(this, HelpSentenceBreakdown.class);
        startActivity(wordbreakdowntohelp);
    }

    public String getDefinition() {
        String definition = getIntent().getStringExtra(("definition"));
        return definition;
    }


}


