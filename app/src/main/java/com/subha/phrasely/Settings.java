package com.subha.phrasely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initialize and assigning variable for nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set settings selected for this page
        bottomNavigationView.setSelectedItemId(R.id.settings_nav);

        onStart();

        findViewById(R.id.signOutBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }
                        });

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
        });
    }

            @Override
            protected void onStart() {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();
                mGoogleApiClient.connect();
                super.onStart();
            }


            //method for going to homepage from backbutton
            public void goback(View v) {
                //launching Home page activity
                Intent settingstohome = new Intent(this, HomePage.class);
                startActivity(settingstohome);
            }

            //method for going to help page from helpbtn
            public void gohelpsettings(View v) {
                //launching Home page activity
                Intent settingstohelpsettings = new Intent(this, HelpSettings.class);
                startActivity(settingstohelpsettings);
            }
        }


