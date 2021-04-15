package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tyagiabhinav.dialogflowchatlibrary.Chatbot;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotActivity;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotSettings;
import com.tyagiabhinav.dialogflowchatlibrary.DialogflowCredentials;

import java.util.UUID;

public class LanguageBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_bot);

        //when start a chat button clicked
        Button startchatbtn = findViewById(R.id.btnStartChat);

        startchatbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openChatbot();
            }
        });

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set languagebot selected for this page
        bottomNavigationView.setSelectedItemId(R.id.lang_bot_nav);

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
        Intent langbottohome = new Intent(this, HomePage.class);
        startActivity(langbottohome);
    }
    public void openChatbot() {
// provide your Dialogflow's Google Credential JSON saved under RAW folder in resources
        DialogflowCredentials.getInstance().setInputStream(getResources().openRawResource(R.raw.credential_file));

        ChatbotSettings.getInstance().setChatbot( new Chatbot.ChatbotBuilder().build());
        Intent intent = new Intent(LanguageBot.this, ChatbotActivity.class);
        Bundle bundle = new Bundle();


// provide a UUID for your session with the Dialogflow agent
        bundle.putString(ChatbotActivity.SESSION_ID, UUID.randomUUID().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}