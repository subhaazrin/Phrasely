package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TermsAndConditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView privacyy =(TextView)findViewById(R.id.PolicyLink);
        privacyy.setClickable(true);
        privacyy.setMovementMethod(LinkMovementMethod.getInstance());
        String privacytextt = "<a href='https://phrasely.flycricket.io/privacy.html'>Privacy Policy</a>";
        privacyy.setText(Html.fromHtml(privacytextt));

        TextView termss =(TextView)findViewById(R.id.TermsLink);
        termss.setClickable(true);
        termss.setMovementMethod(LinkMovementMethod.getInstance());
        String termstextt = "<a href='https://phrasely.flycricket.io/terms.html'>Terms And Conditions</a>";
        termss.setText(Html.fromHtml(termstextt));

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set settings selected for this page
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
                        startActivity(new Intent(getApplicationContext()
                                ,Settings.class));
                        overridePendingTransition(0,0);
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

    //method for going back from backbutton
    public void goback(View v) {
        //launching Settngs activity
        Intent termstosettings = new Intent(this, Settings.class);
        startActivity(termstosettings);
    }
}