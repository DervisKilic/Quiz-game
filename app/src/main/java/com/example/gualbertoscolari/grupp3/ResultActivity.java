package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//Skriver ut resultatet. Har 3 knappar.
//Uppdaterar highscore listan.
public class ResultActivity extends AppCompatActivity {

    private final String CATEGORY = "chosen category";
    private final String SCOREPLAYER1 = "score of player 1";
    private final String PLAYERS = "number of players";
    private final String FIRSTPROFILE = "name of the player 1";
    private final String SECONDPROFILE = "name of the player 2";
    private final String SCOREPLAYER2 = "score of player 2";

    private String p1Name;
    private String p2Name;
    private String P1Score;
    private String players;
    private String P2Score;
    private String chosenCategory;
    //private int correctNrAnsweredP1;
    //private int correctNrAnsweredP2;

    private TextView category;
    private TextView player1;
    private TextView scorep1;
    private TextView player2;
    private TextView scorep2;
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

        Bundle extras = getIntent().getExtras();
        chosenCategory = extras.getString(CATEGORY);
        P1Score = extras.getString(SCOREPLAYER1);
        p1Name = extras.getString(FIRSTPROFILE);
        players = extras.getString(PLAYERS);
        p2Name = extras.getString(SECONDPROFILE);
        P2Score = extras.getString(SCOREPLAYER2);


        player1.setText(p1Name);
        scorep1.setText(P1Score);
        player2.setText(p2Name);
        scorep2.setText(P2Score);
        category.setText(chosenCategory);

        if(p1Name.equals("Dervis")){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.playerdervis));
        }
        else if(p1Name.equals("Fredrik")){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.playerfredrik));
        }
        else if(p1Name.equals("Gualberto")){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.playergual));
        }
        else if(p1Name.equals("Simon")){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.playersimon));
        }else{
            img1.setImageDrawable(getResources().getDrawable(R.drawable.playerdervis));
        }

        if(players.equals("2")) {

            if (p2Name.equals("Dervis")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.playerdervis));
            }
           else if (p2Name.equals("Fredrik")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.playerfredrik));
            }
            else if (p2Name.equals("Gualberto")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.playergual));
            }
            else if (p2Name.equals("Simon")) {
                img2.setImageDrawable(getResources().getDrawable(R.drawable.playersimon));
            }else{
                img1.setImageDrawable(getResources().getDrawable(R.drawable.playerdervis));
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

    public void goToMenu(View view) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
    }

    public void goToHighScore(View view){
        Intent HighScore = new Intent(this, HighscoreActivity.class);
        startActivity(HighScore);
    }

    public void goToMainGame(View view){
        Intent gameSettings = new Intent(this, GameSettingsActivity.class);
        startActivity(gameSettings);
    }
}
