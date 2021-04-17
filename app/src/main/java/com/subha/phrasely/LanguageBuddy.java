package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LanguageBuddy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_buddy);

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
}