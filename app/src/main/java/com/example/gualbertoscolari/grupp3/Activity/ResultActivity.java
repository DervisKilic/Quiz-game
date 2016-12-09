package com.example.gualbertoscolari.grupp3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gualbertoscolari.grupp3.Activity.AboutActivity;
import com.example.gualbertoscolari.grupp3.Activity.GameSettingsActivity;
import com.example.gualbertoscolari.grupp3.Activity.HighscoreActivity;
import com.example.gualbertoscolari.grupp3.Activity.MenuActivity;
import com.example.gualbertoscolari.grupp3.R;

//Skriver ut resultatet. Har 3 knappar.
//Uppdaterar highscore listan.
public class ResultActivity extends AppCompatActivity {

    public static final String CATEGORY = "chosen category";
    public static final String SCORE_PLAYER1 = "score of player 1";
    public static final String PLAYERS = "number of players";
    public static final String FIRST_PROFILE = "name of the player 1";
    public static final String SECOND_PROFILE = "name of the player 2";
    public static final String SCORE_PLAYER2 = "score of player 2";
    public static final String TIME_PLAYED_PLAYER1 = "time played of player 1";
    public static final String TIME_PLAYED_PLAYER2 = "time played of player 2";
    public static final String CORRECT_ANS_P1= "correct answers player 1";
    public static final String CORRECT_ANS_P2= "correct answers player 2";

    private String p1Name;
    private String p2Name;
    private String P1Score;
    private String players;
    private String P2Score;
    private String chosenCategory;

    private int currentTime;
    private int currentTime2;
    private int correctAnsP1;
    private int correctAnsP2;

    private TextView timePlayed;
    private TextView timePlayed2;
    private TextView category;
    private TextView player1;
    private TextView scorep1;
    private TextView player2;
    private TextView scorep2;
    private TextView correctAnsP1Tv;
    private TextView correctAnsP2Tv;
    private ImageView img1;
    private ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        player2 = (TextView) findViewById(R.id.player2_name);
        scorep2 = (TextView) findViewById(R.id.player2_score);
        category = (TextView) findViewById(R.id.category);
        player1 = (TextView) findViewById(R.id.player1_name);
        scorep1 = (TextView) findViewById(R.id.player1_score);
        img1 = (ImageView) findViewById(R.id.profile_img1);
        img2 = (ImageView) findViewById(R.id.profile_img2);
        timePlayed = (TextView) findViewById(R.id.time_played);
        timePlayed2 = (TextView) findViewById(R.id.time_played2);
        correctAnsP1Tv = (TextView) findViewById(R.id.correct_answers_P1);
        correctAnsP2Tv = (TextView) findViewById(R.id.correct_answers_P2);


        Bundle extras = getIntent().getExtras();
        chosenCategory = extras.getString(CATEGORY);
        P1Score = extras.getString(SCORE_PLAYER1);
        p1Name = extras.getString(FIRST_PROFILE);
        players = extras.getString(PLAYERS);
        p2Name = extras.getString(SECOND_PROFILE);
        P2Score = extras.getString(SCORE_PLAYER2);
        currentTime = extras.getInt(TIME_PLAYED_PLAYER1);
        currentTime2 = extras.getInt(TIME_PLAYED_PLAYER2);
        correctAnsP1 = extras.getInt(CORRECT_ANS_P1);
        correctAnsP2 = extras.getInt(CORRECT_ANS_P2);
        Log.d("Result time played", ""+currentTime);
       // playedTime = playedTime / 100;

        if(players.equals("1")){
            scorep2.setVisibility(View.GONE);
            player2.setVisibility(View.GONE);
            timePlayed2.setVisibility(View.GONE);
            correctAnsP2Tv.setVisibility(View.GONE);
        }
        player1.setText(p1Name);
        scorep1.setText("Poäng: " + P1Score);
        player2.setText(p2Name);
        scorep2.setText("Poäng: " + P2Score);
        category.setText("Kategori: " + chosenCategory);
        correctAnsP1Tv.setText("Rätta svar " + correctAnsP1);
        correctAnsP2Tv.setText("Rätta svar " + correctAnsP2);

        timePlayed.setText(String.valueOf("Tid: " + currentTime + " sekunder"));
        timePlayed2.setText(String.valueOf("Tid: " +currentTime2 + " sekunder"));




        if (players.equals("1")) {

            if (p1Name.equals("Dervis")) {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
            } else if (p1Name.equals("Fredrik")) {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
            } else if (p1Name.equals("Gualberto")) {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
            } else if (p1Name.equals("Simon")) {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
            } else {
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
            }
        }

        if(players.equals("2")) {

            if(p1Name.equals("Dervis")){
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
            }
            else if(p1Name.equals("Fredrik")){
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
            }
            else if(p1Name.equals("Gualberto")){
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
            }
            else if(p1Name.equals("Simon")){
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
            }else{
                img1.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
            }

            if (p2Name.equals("Dervis")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
            }
           else if (p2Name.equals("Fredrik")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
            }
            else if (p2Name.equals("Gualberto")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
            }
            else if (p2Name.equals("Simon")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
            }else{
                img2.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.start_about_activity_ic:
            Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param view takes the player to main menu
     */
    public void goToMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
        finish();
    }

    /**
     *
     * @param view takes the player to high score
     */
    public void goToHighScore(View view){
        Intent HighScore = new Intent(this, HighscoreActivity.class);
        startActivity(HighScore);
        finish();
    }

    /**
     *
     * @param view takes the player to game settings
     */
    public void goToMainGame(View view){
        Intent gameSettings = new Intent(this, GameSettingsActivity.class);
        startActivity(gameSettings);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
        finish();
    }
}
