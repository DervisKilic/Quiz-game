package com.example.gualbertoscolari.grupp3.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gualbertoscolari.grupp3.R;

//Första menyn man kommer till, finns 3 knappar som tar spelaren vidare till olika aktiviteter.
public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    /**
     * @param view takes the user to about screen
     */
    public void about_button(View view) {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
        finish();

    }

    /**
     * @param view takes the user to high score screen
     */
    public void high_score_button(View view) {
        Intent highScoreIntent = new Intent(this, HighscoreActivity.class);
        startActivity(highScoreIntent);
        finish();

    }

    /**
     * @param view takes the user to game settings screen
     */
    public void goToGameSettings(View view) {
        Intent gameSettingsIntent = new Intent(this, GameSettingsActivity.class);
        startActivity(gameSettingsIntent);
        finish();
    }

    /**
     * @param view starts a random game in sports category
     */
    public void gameSp(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Sport");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();

    }

    /**
     * @param view starts a random game in history category
     */
    public void gameHi(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Historia");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    /**
     * @param view starts a random game in culture category
     */
    public void gameKn(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Kultur/Nöje");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    /**
     * @param view starts a random game in nature category
     */
    public void gameNa(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Natur");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    /**
     * @param view starts a random game in society category
     */
    public void gameSk(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        playIntent.putExtra(MainGameActivity.CATEGORY, "Samhälle");
        playIntent.putExtra(MainGameActivity.PLAYERS, 1);
        playIntent.putExtra(MainGameActivity.FIRSTPROFILE, "anonymous");
        startActivity(playIntent);
        finish();
    }

    /**
     * @param view nothing to see here
     */
    public void easter(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=EShUeudtaFg"));
        startActivity(intent);
    }
}