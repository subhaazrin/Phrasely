package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class GeneratedSentence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_sentence);


        //getting inputs from previous activity

        TextView subjectInput = findViewById(R.id.textViewSubjectInput);
        TextView verbInput = findViewById(R.id.textViewVerbInput);
        TextView objectInput = findViewById(R.id.textViewObjectInput);

        String sub_int = getIntent().getStringExtra("subject");
        String verb_int = getIntent().getStringExtra("verb");
        String obj_int = getIntent().getStringExtra("object");

        //shows the input results (probably will have to modify this to be put in api url
        subjectInput.setText(sub_int);
        verbInput.setText(verb_int);
        objectInput.setText(obj_int);

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected for this page
        bottomNavigationView.setSelectedItemId(R.id.settings_nav);

        //perfom ItemSelectedListener Event
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.sentence_maker_nav:
                        startActivity(new Intent(getApplicationContext()
                                , PhraseMaker.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.lang_bot_nav:
                        startActivity(new Intent(getApplicationContext()
                                , LanguageBot.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.settings_nav:
                        return true;

                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext()
                                , HomePage.class));
                        overridePendingTransition(0, 0);
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