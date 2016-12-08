package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.media.MediaPlayer;
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

    public void about_button(View view) {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
        finish();

    }

    public void high_score_button(View view) {
        Intent highScoreIntent = new Intent(this, HighscoreActivity.class);
        startActivity(highScoreIntent);
        finish();

    }

    public void goToGameSettings(View view) {
        Intent gameSettingsIntent = new Intent(this, GameSettingsActivity.class);
        startActivity(gameSettingsIntent);
        finish();
    }

    public void gameSp(View view){
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Sport");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();

    }

    public void gameHi(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Historia");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    public void gameKn(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Kultur/Nöje");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    public void gameNa(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Natur");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    public void gameSk(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Samhälle");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }
}