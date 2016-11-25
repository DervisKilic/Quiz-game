package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;

//Metoden skall skapa upp ett gamelogic objekt som innehåller 10 frågor.
//Skall visa upp 1 fråga och 4 svar. Skall visa en timer från gamelogic.
//
public class MainGameActivity extends AppCompatActivity {

    public final static String CATEGORY = "chosen category";
    public final static String PLAYERS = "number of players";
    public final static String FIRSTPROFILE = "name of the player 1";
    public final static String SECONDPROFILE = "name of the player 2";

    private int numberOfPlayers;
    private String p1Name;
    private String p2Name;

    private Profile p1;
    private Profile p2;
    private GameLogic g1;
    private Profile currentPlayer;


    private String answer;
    private TextView playerName;
    private TextView questiontv;
    private String chosenCat;
    private Button optABtn;
    private Button optBBtn;
    private Button optCBtn;
    private Button optDBtn;
    private TextView cat;
    private TextView timerTV;
    private CountDownTimer timer;
    private ProgressBar progressbar;
    private int scoreValue;

    private static final String TAG = "MAINGAME_ACTIVITY";

    private int numberOfAnsweredQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Bundle extras = getIntent().getExtras();
        chosenCat = extras.getString(CATEGORY);
        p1Name = extras.getString(FIRSTPROFILE);
        p2Name = extras.getString(SECONDPROFILE);
        numberOfPlayers = extras.getInt(String.valueOf(PLAYERS));
        p1 = new Profile(p1Name, 0);
        p2 = new Profile(p2Name, 0);
        cat = (TextView) findViewById(R.id.chosen_category);
        timerTV = (TextView) findViewById(R.id.timer_tv);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);


        g1 = new GameLogic(chosenCat, this);
        currentPlayer = p1;

        playerName = (TextView) findViewById(R.id.profile_name);
        questiontv = (TextView) findViewById(R.id.question_tv);
        optABtn = (Button) findViewById(R.id.answer_btn_a);
        optBBtn = (Button) findViewById(R.id.answer_btn_b);
        optCBtn = (Button) findViewById(R.id.answer_btn_c);
        optDBtn = (Button) findViewById(R.id.answer_btn_d);
        displayQuestion();
        resetTimer();

    }

    public void displayQuestion() {
        //Hämtar fråga från GameLogic och skriver ut den i TextView:n
        //och skriver ut svaren på knapparna.
        playerName.setText(currentPlayer.getName());
        questiontv.setText(g1.getQuestions().get(numberOfAnsweredQ).getQUESTION());
        cat.setText(g1.getQuestions().get(numberOfAnsweredQ).getCATEGORY());
        optABtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTA());
        optBBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTB());
        optCBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTC());
        optDBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTD());

    }

    public void onButtonGuess(String optString) {
        // Ska användas OnClick på alla knappar när användaren gissar.
        // Ska kolla om den intrykta knappens text är lika med frågans correctAnswer.
        timer.cancel();

        Log.d("aaaaaaa", numberOfAnsweredQ + "");
        Log.d("aaaaaaa", numberOfPlayers + "");

        if (g1.checkCorrectAnswer(optString, g1.getQuestions().get(numberOfAnsweredQ).getANSWER())) {
            //Ifall man svarar rätt händer detta
            Log.d(TAG, "Answer gotten from database:  " + answer + " The string on the button :  " + optString + " The Question was answered correctly ");
            currentPlayer.setScore(currentPlayer.getScore() + scoreValue);

        } else {
            //Ifall man svarar fel händer detta
            Log.d(TAG, "Answer: " + answer + "optstring:  " + optString + " The Question was answered wrongly");
        }

        if (numberOfPlayers == 2 && currentPlayer == p2){
            numberOfAnsweredQ++;
        }

        if(numberOfPlayers == 1) {
            numberOfAnsweredQ++;
        }

        if(currentPlayer == p1 && numberOfPlayers == 2){
            currentPlayer = p2;
        }else{
            currentPlayer = p1;
        }

        if (numberOfAnsweredQ == 10) {
            //Du har svarat på alla frågor , du tas till resultskärmen.
            goToResult();
            finish();

        }else {

            displayQuestion();
            resetTimer();
        }
    }

    public void resetTimer() {
        timer = new CountDownTimer(10000, 10) {
            public void onTick(long millisUntilFinished) {

                timerTV.setText("Points " + (millisUntilFinished/100 ));
                scoreValue = (int) (millisUntilFinished/100);
                int progress = (int) (millisUntilFinished / 100);
                progressbar.setProgress(progress);
            }



            public void onFinish() {
                progressbar.setProgress(0);
                timerTV.setText("Done!");
            }
        }.start();
        Log.d(TAG, "resetTimer: Timer started");


    }

    public void goToResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

    public void btn_a_pressed(View view) {
        onButtonGuess(optABtn.getText().toString());

    }

    public void btn_b_pressed(View view) {
        onButtonGuess(optBBtn.getText().toString());

    }

    public void btn_c_pressed(View view) {
        onButtonGuess(optCBtn.getText().toString());

    }

    public void btn_d_pressed(View view) {
        onButtonGuess(optDBtn.getText().toString());

    }

    public int getScoreValue(){
           return scoreValue;
    }
}