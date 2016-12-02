package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Första menyn man kommer till, finns 3 knappar som tar spelaren vidare till olika aktiviteter.
public class MenuActivity extends AppCompatActivity {

    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //mp = MediaPlayer.create(this, R.raw.themesong);
        //mp.setLooping(true);
        //mp.start();


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
        Intent highScoreIntent = new Intent(this, HighscoreActivity.class);
        startActivity(highScoreIntent);

    }
}
