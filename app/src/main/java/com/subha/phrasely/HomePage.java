package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
        ImageView langbot = (ImageView) findViewById(R.id.langBotIcon);

        langbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent langbott = new Intent (getApplicationContext(), LanguageBot.class);
                startActivity(langbott);
            }
        });
        ImageView phrasegen = (ImageView) findViewById(R.id.sentenceMakericon);

        phrasegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sentence = new Intent (getApplicationContext(), PhraseMaker.class);
                startActivity(sentence);
            }
        });
        ImageView langbuddy = (ImageView) findViewById(R.id.langbuddyicon);

        langbuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent langbud = new Intent (getApplicationContext(), LanguageBuddy.class);
                startActivity(langbud);
            }
        });


        getName();
    }

    public void getName() {
        Intent intent = getIntent();
        String personGivenName = intent.getExtras().getString("name");

        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(personGivenName + "!");
    }



}