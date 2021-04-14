package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //test
        Button button = (Button) findViewById(R.id.testbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        //test

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected for this page
        bottomNavigationView.setSelectedItemId(R.id.home_nav);

        //perfom ItemSelectedListener Event
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.sentence_maker_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,PhraseMaker.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.lang_bot_nav:
                        startActivity(new Intent(getApplicationContext()
                                ,LanguageBot.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home_nav:
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

    //method for going to PhraseMaker
    public void goPhraseMaker(View v){
        //launching Phrase Maker activity
        Intent phrasemaker = new Intent (this, PhraseMaker.class);
        startActivity(phrasemaker);

    }


    //test
    public void test(){Intent intent = new Intent(this, WordBreakdown.class);
        startActivity(intent);
    }
    //test


    //method for going to LanguageBot
    public void goLanguageBot(View v){
        //launching Language Bot activity
        Intent langbott = new Intent (this, LanguageBot.class);
        startActivity(langbott);

    }

}