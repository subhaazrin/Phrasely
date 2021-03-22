package com.subha.phrasely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //making title in top left corner
        setTitle("Launch Sign In Page");
    }

    //method for going to homepage
  public void goHomePage(View v){
        //launching Home page activity
        Intent mainPage = new Intent (this, HomePage.class);
        startActivity(mainPage);

}
}