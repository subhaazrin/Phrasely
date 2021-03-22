package com.subha.phrasely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    //method for going to PhraseMaker
    public void goPhraseMaker(View v){
        //launching Phrase Maker activity
        Intent phrasemaker = new Intent (this, PhraseMaker.class);
        startActivity(phrasemaker);

    }

    //method for going to LanguageBot
    public void goLanguageBot(View v){
        //launching Language Bot activity
        Intent langbott = new Intent (this, LanguageBot.class);
        startActivity(langbott);

    }
}