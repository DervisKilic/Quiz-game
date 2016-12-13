package com.example.gualbertoscolari.grupp3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gualbertoscolari.grupp3.R;
//Skriver ut info om utvecklarna och en företagslogga.
//Är klar men väntar på slutgiltiga designen.


public class AboutActivity extends AppCompatActivity {

    /**
     * This method is used called when user enters this activity
     * Sets the corresponding layout for this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


    /**
     * Method that overides the phones backbutton.
     * Creates an Intent and starts MenuActivity. Finishes this activity.
     *
     * @param
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
