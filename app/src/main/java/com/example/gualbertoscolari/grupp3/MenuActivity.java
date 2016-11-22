package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Första menyn man kommer till, finns 3 knappar som tar spelaren vidare till olika aktiviteter.
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void play_button(View view) {
        Intent playIntent = new Intent(this, GameSettingsActivity.class);
        startActivity(playIntent);
    }

    public void about_button(View view) {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    public void high_score_button(View view) {
        Intent highScoreIntent = new Intent(this, HighScoreActivity.class);
        startActivity(highScoreIntent);
    }
}
